package com.miguelrosa.practicas_signlab.posts.interactor;

import com.miguelrosa.practicas_signlab.api.Models.Comment;
import com.miguelrosa.practicas_signlab.api.wsApi.WsApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostInteractorImpl implements PostInteractor{
    @Inject
    WsApi wsApi;

    @Inject
    public PostInteractorImpl(){}

    @Override
    public void getCommentsFromApi(int postId, PostInteractor.OnGetCommentsCallBacks callBacks, PostInteractor.OnErrorServer errorServer) {
        wsApi.getPostComments(postId).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(response.isSuccessful()){
                    callBacks.onCommentsSuccessCallBacks(new ArrayList<Comment>(response.body()));
                }else{
                    callBacks.onErrorCallBacks(response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                errorServer.errorServerMessage(Arrays.toString(t.getStackTrace()));
            }
        });

    }
}
