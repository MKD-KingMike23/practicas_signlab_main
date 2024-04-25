package com.miguelrosa.practicas_signlab.usuarios.interactor;

import com.miguelrosa.practicas_signlab.api.Models.Post;
import com.miguelrosa.practicas_signlab.api.Models.Todo;
import com.miguelrosa.practicas_signlab.api.wsApi.WsApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilUsuarioInteractorImpl implements PerfilUsuarioInteractor {
    @Inject
    WsApi wsApi;

    @Inject
    public PerfilUsuarioInteractorImpl(){}

    @Override
    public void getPostsFromApi(int userId, OnGetPostsCallBacks callBacks, OnErrorServer errorServer) {
        wsApi.getUserPosts(userId).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful()){
                    callBacks.onPostsSuccessCallBacks(new ArrayList<Post>(response.body()));
                }else{
                    callBacks.onErrorCallBacks(response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                errorServer.errorServerMessage(Arrays.toString(t.getStackTrace()));
            }
        });

    }

    @Override
    public void getTodosFromApi(int userId, OnGetTodosCallBacks callBacks, OnErrorServer errorServer) {
        wsApi.getUserTodos(userId).enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                if(response.isSuccessful()){
                    callBacks.onTodosSuccessCallBacks(new ArrayList<Todo>(response.body()));
                }else{
                    callBacks.onErrorCallBacks(response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                errorServer.errorServerMessage(Arrays.toString(t.getStackTrace()));
            }
        });

    }
}

