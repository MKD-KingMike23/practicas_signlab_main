package com.miguelrosa.practicas_signlab.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.miguelrosa.practicas_signlab.databinding.ActivityLoginBinding;
import com.miguelrosa.practicas_signlab.di.appComponent.AppComponent;
import com.miguelrosa.practicas_signlab.di.appComponent.DaggerAppComponent;
import com.miguelrosa.practicas_signlab.di.appModule.AppModule;
import com.miguelrosa.practicas_signlab.di.appModule.SharedPreferencesModule;
import com.miguelrosa.practicas_signlab.home.view.HomeActivity;
import com.miguelrosa.practicas_signlab.login.presenter.LoginPresenter;

import java.util.Objects;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private ActivityLoginBinding binding;
    @Inject
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initInjection();
        binding.buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String username = Objects.requireNonNull(binding.editTextUsername.getText()).toString();
                String password = Objects.requireNonNull(binding.editTextPassword.getText()).toString();
                presenter.checkCredentials(username, password);
            }
        });
    }
    private void initInjection() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, this))
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .build();
        appComponent.inject(this);
    }
    @Override
    public void onLoginCheck(String mensaje, boolean IsLoggedIn) {
        if (IsLoggedIn) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        } else {
            // Mostrar un mensaje de error al usuario o realizar alguna otra acción
            Toast.makeText(this, "Error: credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }

    }
}