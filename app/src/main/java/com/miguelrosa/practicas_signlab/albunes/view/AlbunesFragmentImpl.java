package com.miguelrosa.practicas_signlab.albunes.view;

import com.miguelrosa.practicas_signlab.api.Models.User;
import com.miguelrosa.practicas_signlab.di.appComponent.DaggerAppComponent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import com.miguelrosa.practicas_signlab.api.Models.Album;
import com.miguelrosa.practicas_signlab.databinding.FragmentAlbunesBinding;
import com.miguelrosa.practicas_signlab.di.appComponent.AppComponent;
import com.miguelrosa.practicas_signlab.di.appModule.AppModule;
import com.miguelrosa.practicas_signlab.di.appModule.ConnectionModule;
import com.miguelrosa.practicas_signlab.di.appModule.SharedPreferencesModule;
import com.miguelrosa.practicas_signlab.albunes.presenter.AlbunesPresenter;
import com.miguelrosa.practicas_signlab.usuarios.presenter.UsuariosPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

public class AlbunesFragmentImpl extends Fragment implements AlbunesFragment {
    public AlbunesFragmentImpl() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private AlbunesAdapter adapter;
    private FragmentAlbunesBinding binding;
    private ArrayList<User> users;
    private ArrayList<Album> albums;

    @Inject
    AlbunesPresenter albunespresenter;
    @Inject
    UsuariosPresenter usuariospresenter;

    public static AlbunesFragmentImpl newInstance(String param1, String param2) {
        AlbunesFragmentImpl fragment = new AlbunesFragmentImpl();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Álbunes");
        binding = FragmentAlbunesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initInjection();
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        showLoading();
        albunespresenter.onAlbumsFetched();

        adapter = new AlbunesAdapter();
        recyclerView.setAdapter(adapter);

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedUserName = (String) parentView.getItemAtPosition(position);

                if ("Todos los usuarios".equals(selectedUserName)) {
                    adapter.setAlbums(albums);
                } else {
                    int userId = getUserIdByName(selectedUserName);
                    ArrayList<Album> filteredAlbums = filterAlbumsByUser(userId);
                    adapter.setAlbums(filteredAlbums);
                }
                adapter.getFilter().filter(binding.searchView.getQuery().toString().toLowerCase());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

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

    private int getUserIdByName(String userName) {
        for (User user : users) {
            if (userName.equals(user.getName())) {
                return user.getId();
            }
        }
        return 0;
    }

    private ArrayList<Album> filterAlbumsByUser(int userId) {
        ArrayList<Album> filteredAlbums = new ArrayList<>();
        for (Album album : albums) {
            if (Integer.parseInt(album.getUserId()) == userId) {
                filteredAlbums.add(album);
            }
        }
        return filteredAlbums;
    }


    @Override
    public void showAlbums(ArrayList<Album> albums) {
        hideLoading();
        this.albums = albums;
        adapter.setAlbums(albums);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showUser(ArrayList<User> users) {
        this.users = users;
        adapter.setUsers(users);
        adapter.notifyDataSetChanged();

        ArrayList<String> nombresUsuarios = new ArrayList<>();
        nombresUsuarios.add("Todos los usuarios");
        for (User usuario : users) {
            nombresUsuarios.add(usuario.getName());
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, nombresUsuarios);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.spinner.setAdapter(spinnerAdapter);

    }


    private void showLoading() {
        binding.LinearLayoutLoading.setVisibility(View.VISIBLE);
        binding.LinearLayoutAlbum.setVisibility(View.GONE);

    }

    private void hideLoading() {
        binding.LinearLayoutLoading.setVisibility(View.GONE);
        binding.LinearLayoutAlbum.setVisibility(View.VISIBLE);

    }


}