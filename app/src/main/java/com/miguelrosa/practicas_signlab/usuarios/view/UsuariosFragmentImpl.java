package com.miguelrosa.practicas_signlab.usuarios.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miguelrosa.practicas_signlab.api.Models.User;
import com.miguelrosa.practicas_signlab.databinding.FragmentUsuariosBinding;
import com.miguelrosa.practicas_signlab.di.appComponent.AppComponent;
import com.miguelrosa.practicas_signlab.di.appComponent.DaggerAppComponent;
import com.miguelrosa.practicas_signlab.di.appModule.AppModule;
import com.miguelrosa.practicas_signlab.di.appModule.ConnectionModule;
import com.miguelrosa.practicas_signlab.di.appModule.SharedPreferencesModule;
import com.miguelrosa.practicas_signlab.usuarios.adapter.UsuariosAdapter;
import com.miguelrosa.practicas_signlab.usuarios.presenter.UsuariosPresenter;

import java.util.ArrayList;

import javax.inject.Inject;


public class UsuariosFragmentImpl extends Fragment implements UsuariosFragment, UsuariosAdapter.OnItemClickListener {

    public UsuariosFragmentImpl() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private UsuariosAdapter adapter;
    private FragmentUsuariosBinding binding;
    @Inject
    UsuariosPresenter usuariospresenter;

    public static UsuariosFragmentImpl newInstance(String param1, String param2) {
        UsuariosFragmentImpl fragment = new UsuariosFragmentImpl();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Usuarios");

        binding = FragmentUsuariosBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initInjection();
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        showLoading();
        usuariospresenter.onUsersFetched();
        adapter = new UsuariosAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);


        return view;
    }
    
    private void initInjection() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, requireContext()))
                .connectionModule(new ConnectionModule())
                .sharedPreferencesModule(new SharedPreferencesModule(requireContext()))
                .build();
        appComponent.inject(this);
    }
    @Override
    public void showUsers(ArrayList<User> users) {
        hideLoading();
        adapter.setUsers(users);
        adapter.notifyDataSetChanged();

    }


    private void showLoading() {
        binding.textViewLoading.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        binding.textViewLoading.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(User user) {
        Intent intent = new Intent(requireContext(), PerfilUsuarioImpl.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}