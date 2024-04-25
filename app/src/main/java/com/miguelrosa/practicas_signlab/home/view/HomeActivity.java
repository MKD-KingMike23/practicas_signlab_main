package com.miguelrosa.practicas_signlab.home.view;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.navigation.NavigationBarView;
import com.miguelrosa.practicas_signlab.R;
import com.miguelrosa.practicas_signlab.albunes.view.AlbunesFragmentImpl;
import com.miguelrosa.practicas_signlab.databinding.ActivityHomeBinding;
import com.miguelrosa.practicas_signlab.di.appComponent.AppComponent;
import com.miguelrosa.practicas_signlab.di.appComponent.DaggerAppComponent;
import com.miguelrosa.practicas_signlab.di.appModule.AppModule;
import com.miguelrosa.practicas_signlab.di.appModule.SharedPreferencesModule;
import com.miguelrosa.practicas_signlab.portadas.view.PortadasFragmentImpl;
import com.miguelrosa.practicas_signlab.usuarios.view.UsuariosFragmentImpl;

public class HomeActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new UsuariosFragmentImpl());
        binding.bottomNavigation.setOnItemSelectedListener(this);
        initInjection();
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container,fragment);
        fragmentTransaction.commit();
    }
    private void initInjection() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, this))
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .build();
        appComponent.inject(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int IdBotonPulsado = menuItem.getItemId();

        int[] optionMenu = new int[binding.bottomNavigation.getMenu().size()];
        for (int i=0; i<binding.bottomNavigation.getMenu().size(); i++) {
            optionMenu[i]=binding.bottomNavigation.getMenu().getItem(i).getItemId();
        }

        if(optionMenu[0]==IdBotonPulsado){
            replaceFragment(new UsuariosFragmentImpl());
        }

        if(optionMenu[1]==IdBotonPulsado) {
            replaceFragment(new AlbunesFragmentImpl());
        }

        if (optionMenu[2]==IdBotonPulsado) {
            replaceFragment(new PortadasFragmentImpl());
        }

        return true;
    }
}