package com.miguelrosa.practicas_signlab.posts.presenter;

import com.miguelrosa.practicas_signlab.api.Models.Comment;

import java.util.ArrayList;

public interface PostPresenter {
    void onCommentsFetched(int userId);

    void onCommentsSuccessCallBacks(ArrayList<Comment> comments);

    void onErrorCallBacks(int code);
}
