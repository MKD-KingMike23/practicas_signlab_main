package com.miguelrosa.practicas_signlab.main.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import com.miguelrosa.practicas_signlab.databinding.ActivityMainBinding;
import com.miguelrosa.practicas_signlab.di.appComponent.AppComponent;
import com.miguelrosa.practicas_signlab.di.appComponent.DaggerAppComponent;
import com.miguelrosa.practicas_signlab.di.appModule.AppModule;
import com.miguelrosa.practicas_signlab.di.appModule.SharedPreferencesModule;
import com.miguelrosa.practicas_signlab.login.presenter.LoginPresenter;
import com.miguelrosa.practicas_signlab.home.view.HomeActivity;
import com.miguelrosa.practicas_signlab.login.view.LoginActivity;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainView {
    private ActivityMainBinding binding;

    @Inject
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initInjection();
        presenter.obtenerCredenciales();
    }

    private void initInjection() {
       AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, this))
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .build();
        appComponent.inject(this);
    }


    @Override
    public void onReedirigiraLoginActivity() {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void onReedirigiraHomeActivity() {
        startActivity(new Intent(MainActivity.this, HomeActivity.class));
        finish();
    }

}