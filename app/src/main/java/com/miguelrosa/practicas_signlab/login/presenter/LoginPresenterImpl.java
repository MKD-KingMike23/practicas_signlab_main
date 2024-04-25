package com.miguelrosa.practicas_signlab.login.presenter;

import android.content.SharedPreferences;
import androidx.annotation.Nullable;

import com.miguelrosa.practicas_signlab.main.view.MainView;
import com.miguelrosa.practicas_signlab.login.interactor.LoginInteractor;
import com.miguelrosa.practicas_signlab.login.view.LoginView;

import javax.inject.Inject;
public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnGetLoginCallBacks{
    @Nullable
    @Inject
    LoginView loginview;

    @Nullable
    @Inject
    MainView mainview;

    @Inject
    LoginInteractor interactor;

    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    public LoginPresenterImpl() {}

    @Override
    public void checkCredentials(String username, String password) {
        if (!username.isEmpty() && !password.isEmpty()) {
            interactor.checkCredentials(username, password, this);
        } else {
            mainview.onReedirigiraLoginActivity();
        }
    }

    public void guardarDatosSesion(String username, String password) {
        sharedPreferences.edit().putString("username", username).apply();
        sharedPreferences.edit().putString("password", password).apply();
    }

    public void obtenerCredenciales() {
        // Obtener el nombre de usuario y contraseña (con valores por defecto vacíos)
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");
        checkCredentials(username, password);
    }


    @Override
    public void checkCredentials(String username, String password, LoginInteractor.OnGetLoginCallBacks callBacks) {
        if (!username.isEmpty() && !password.isEmpty()) {
            interactor.checkCredentials(username, password, this);
        } else {
            mainview.onReedirigiraLoginActivity();
        }
    }

    @Override
    public void onSuccessCredentials(String username, String password) {
        guardarDatosSesion(username, password);
        if (loginview != null){
            loginview.onLoginCheck("Loggeado correctamente", true);
        } else {
            mainview.onReedirigiraHomeActivity();
        }
    }

    @Override
    public void onErrorCredentials() {
        if (loginview !=null) {
            loginview.onLoginCheck("Error al loggearse", false);
        }
    }
}