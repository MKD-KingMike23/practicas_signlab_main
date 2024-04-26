package com.miguelrosa.practicas_signlab.posts.presenter;

import androidx.annotation.Nullable;

import com.miguelrosa.practicas_signlab.api.Models.Post;
import com.miguelrosa.practicas_signlab.posts.interactor.PostAddInteractor;
import com.miguelrosa.practicas_signlab.posts.view.PostAdd;

import javax.inject.Inject;

public class PostAddPresenterImpl implements PostAddPresenter, PostAddInteractor.onSetDataToApiCallbacks, PostAddInteractor.OnErrorServer{

    @Nullable
    @Inject
    PostAdd view;
    @Inject
    PostAddInteractor interactor;

    @Inject
    public PostAddPresenterImpl(){}

    @Override
    public void onAddPost(int userId, String title, String body)  {
        interactor.onAddDataToApi(userId, title, body, this, this);
    }

    @Override
    public void onEditPost(int postId, int id, String title, String body, int userId)  {
        interactor.onEditDataToApi(postId, id, title, body, userId, this, this);
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
