package com.miguelrosa.practicas_signlab.usuarios.interactor;

import com.miguelrosa.practicas_signlab.api.Models.User;

import java.util.ArrayList;

public interface UsuariosInteractor {

    void getUsersFromApi(OnGetUsersCallBacks callBacks, OnErrorServer errorServer);

    interface OnGetUsersCallBacks {
        void onSuccessCallBacks(ArrayList<User> users);
        void onErrorCallBacks(int code);
    }

    interface OnErrorServer {
        void errorServerMessage(String message);
    }
}
