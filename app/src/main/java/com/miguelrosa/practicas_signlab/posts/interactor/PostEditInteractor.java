package com.miguelrosa.practicas_signlab.posts.interactor;

import com.miguelrosa.practicas_signlab.api.Models.Post;
import com.miguelrosa.practicas_signlab.posts.interactor.PostEditInteractor;

public interface PostEditInteractor {
    void onSetDataToApi(int postId, int id, String title, String body, int userId, PostEditInteractor.onSetDataToApiCallbacks callBacks, PostEditInteractor.OnErrorServer errorServer);

    interface onSetDataToApiCallbacks {
        void onSetDataToApiSuccessCallbacks(Post response);
        void onErrorCallBacks(String s);
    }

    interface OnErrorServer {
        void errorServerMessage(String message);
    }
}
