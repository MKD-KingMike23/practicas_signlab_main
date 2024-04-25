package com.miguelrosa.practicas_signlab.posts.interactor;

import com.miguelrosa.practicas_signlab.api.Models.Post;
import com.miguelrosa.practicas_signlab.api.wsApi.WsApi;
import com.miguelrosa.practicas_signlab.posts.presenter.PostAddPresenter;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostAddInteractorImpl implements PostAddInteractor {
    @Inject
    WsApi wsApi;
    @Inject
    public PostAddInteractorImpl(){}

    @Override
    public void onSetDataToApi(int userId, String title, String body, PostAddInteractor.onSetDataToApiCallbacks callBacks, PostAddInteractor.OnErrorServer errorServer) {
        Call<Post> call = wsApi.createPost(userId, title, body);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
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
