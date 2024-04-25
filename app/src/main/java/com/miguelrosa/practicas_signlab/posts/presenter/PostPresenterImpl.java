package com.miguelrosa.practicas_signlab.posts.presenter;

import androidx.annotation.Nullable;

import com.miguelrosa.practicas_signlab.api.Models.Comment;
import com.miguelrosa.practicas_signlab.posts.interactor.PostInteractor;
import com.miguelrosa.practicas_signlab.posts.view.PostView;

import java.util.ArrayList;

import javax.inject.Inject;

public class PostPresenterImpl implements PostPresenter, PostInteractor.OnGetCommentsCallBacks, PostInteractor.OnErrorServer{
    @Nullable
    @Inject
    PostView view;

    @Inject
    PostInteractor interactor;

    @Inject
    public PostPresenterImpl(){}


    @Override
    public void onCommentsFetched(int postId) {
        interactor.getCommentsFromApi(postId, this, this);
    }

    @Override
    public void onCommentsSuccessCallBacks(ArrayList<Comment> comments) { view.showComments(comments); }

    @Override
    public void onErrorCallBacks(int code) {}

    @Override
    public void errorServerMessage(String message) {}
}
