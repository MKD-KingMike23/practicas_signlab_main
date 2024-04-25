package com.miguelrosa.practicas_signlab.posts.interactor;

import android.util.Log;

import com.miguelrosa.practicas_signlab.api.wsApi.WsApi;
import com.miguelrosa.practicas_signlab.api.Models.Post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostEditInteractorImpl implements PostEditInteractor{
    @Inject
    WsApi wsApi;

    @Inject
    public PostEditInteractorImpl(){}

    @Override
    public void onSetDataToApi(int postId, int id, String title, String body, int userId, PostEditInteractor.onSetDataToApiCallbacks callBacks, PostEditInteractor.OnErrorServer errorServer) {
        Call<Post> call = wsApi.updatePost(postId, id, title, body, userId);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    Log.i("m", response.body().getTitle()+"           "+response.body().getBody());
                    callBacks.onSetDataToApiSuccessCallbacks(response.body());

                } else {
                    callBacks.onErrorCallBacks(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                errorServer.errorServerMessage(t.getLocalizedMessage());
            }
        });

    }

}
