package com.miguelrosa.practicas_signlab.posts.presenter;

import androidx.annotation.Nullable;

import com.miguelrosa.practicas_signlab.posts.interactor.PostEditInteractor;
import com.miguelrosa.practicas_signlab.posts.view.PostEdit;
import com.miguelrosa.practicas_signlab.api.Models.Post;

import java.util.ArrayList;

import javax.inject.Inject;

public class PostEditPresenterImpl implements PostEditPresenter, PostEditInteractor.onSetDataToApiCallbacks, PostEditInteractor.OnErrorServer{
    @Nullable
    @Inject
    PostEdit view;

    @Inject
    PostEditInteractor interactor;

    @Inject
    public PostEditPresenterImpl(){}

    @Override
    public void onGuardar(int postId, int id, String title, String body, int userId)  {
        interactor.onSetDataToApi(postId, id, title, body, userId, this, this);
    }

    @Override
    public void onSetDataToApiSuccessCallbacks(Post response) {
        view.onShowSuccessData(response);

    }

    @Override
    public void onErrorCallBacks(String s) {
        view.showAnswer(s);

    }

    @Override
    public void errorServerMessage(String message) {
        view.showAnswer(message);

    }
}
