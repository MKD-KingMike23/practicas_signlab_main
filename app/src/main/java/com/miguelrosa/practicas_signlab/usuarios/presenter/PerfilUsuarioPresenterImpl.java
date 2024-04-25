package com.miguelrosa.practicas_signlab.usuarios.presenter;

import androidx.annotation.Nullable;

import com.miguelrosa.practicas_signlab.api.Models.Post;
import com.miguelrosa.practicas_signlab.api.Models.Todo;
import com.miguelrosa.practicas_signlab.usuarios.interactor.PerfilUsuarioInteractor;

import com.miguelrosa.practicas_signlab.usuarios.view.PerfilUsuario;


import java.util.ArrayList;

import javax.inject.Inject;

public class PerfilUsuarioPresenterImpl implements PerfilUsuarioPresenter, PerfilUsuarioInteractor.OnGetPostsCallBacks, PerfilUsuarioInteractor.OnGetTodosCallBacks, PerfilUsuarioInteractor.OnErrorServer{

    @Nullable
    @Inject
    PerfilUsuario view;

    @Inject
    PerfilUsuarioInteractor interactor;

    @Inject
    public PerfilUsuarioPresenterImpl(){}


    @Override
    public void onPostsFetched(int userId) {
        interactor.getPostsFromApi(userId, this, this);
    }

    @Override
    public void onTodosFetched(int userId) {
        interactor.getTodosFromApi(userId, this, this);

    }

    @Override
    public void onPostsSuccessCallBacks(ArrayList<Post> posts) { view.showPosts(posts); }

    @Override
    public void onErrorCallBacks(int code) {

    }

    @Override
    public void onTodosSuccessCallBacks(ArrayList<Todo> todos) {
        view.showTodos(todos);
    }


    @Override
    public void errorServerMessage(String message) {

    }
}
