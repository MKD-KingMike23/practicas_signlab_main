package com.miguelrosa.practicas_signlab.usuarios.interactor;

import com.miguelrosa.practicas_signlab.api.Models.Post;
import com.miguelrosa.practicas_signlab.api.Models.Todo;

import java.util.ArrayList;

public interface PerfilUsuarioInteractor {
    void getPostsFromApi(int userId, OnGetPostsCallBacks callBacks, OnErrorServer errorServer);
    void getTodosFromApi(int userId, OnGetTodosCallBacks callBacks, OnErrorServer errorServer);



    interface OnGetPostsCallBacks {
        void onPostsSuccessCallBacks(ArrayList<Post> posts);
        void onErrorCallBacks(int code);
    }

    interface OnGetTodosCallBacks {
        void onTodosSuccessCallBacks(ArrayList<Todo> todos);
        void onErrorCallBacks(int code);
    }

    interface OnErrorServer {
        void errorServerMessage(String message);
    }
}
