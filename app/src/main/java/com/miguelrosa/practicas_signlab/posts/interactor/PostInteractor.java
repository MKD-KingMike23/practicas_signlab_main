package com.miguelrosa.practicas_signlab.posts.interactor;

import com.miguelrosa.practicas_signlab.api.Models.Comment;

import java.util.ArrayList;

public interface PostInteractor {
    void getCommentsFromApi(int postId, PostInteractor.OnGetCommentsCallBacks callBacks, PostInteractor.OnErrorServer errorServer);

    interface OnGetCommentsCallBacks {
        void onCommentsSuccessCallBacks(ArrayList<Comment> comments);
        void onErrorCallBacks(int code);
    }

    interface OnErrorServer {
        void errorServerMessage(String message);
    }
}
