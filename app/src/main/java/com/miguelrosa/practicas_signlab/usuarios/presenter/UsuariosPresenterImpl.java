package com.miguelrosa.practicas_signlab.usuarios.presenter;

import android.util.Log;

import androidx.annotation.Nullable;

import com.miguelrosa.practicas_signlab.api.Models.User;
import com.miguelrosa.practicas_signlab.usuarios.interactor.UsuariosInteractor;
import com.miguelrosa.practicas_signlab.usuarios.view.UsuariosFragment;

import java.util.ArrayList;

import javax.inject.Inject;

public class UsuariosPresenterImpl implements UsuariosPresenter, UsuariosInteractor.OnGetUsersCallBacks, UsuariosInteractor.OnErrorServer {
    @Nullable
    @Inject
    UsuariosFragment usuariosview;
    @Inject
    UsuariosInteractor usuariosinteractor;

    @Inject
    public UsuariosPresenterImpl(){}

    @Override
    public void onUsersFetched() {
        usuariosinteractor.getUsersFromApi(this, this);
    }

    @Override
    public void onSuccessCallBacks(ArrayList<User> users) {
        Log.i("respuesta", String.valueOf(users));
        usuariosview.showUsers(users);
    }

    @Override
    public void onErrorCallBacks(int code) {
        Log.e("respuesta erronea", ""+code);

    }

    @Override
    public void errorServerMessage(String message) {

    }
}
