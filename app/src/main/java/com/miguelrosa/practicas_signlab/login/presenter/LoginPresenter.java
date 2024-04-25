package com.miguelrosa.practicas_signlab.login.presenter;

public interface LoginPresenter {
    void checkCredentials(String username, String password);
    void obtenerCredenciales();
}
