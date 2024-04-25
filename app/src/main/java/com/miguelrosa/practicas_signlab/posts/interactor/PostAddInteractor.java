package com.miguelrosa.practicas_signlab.posts.interactor;

import com.miguelrosa.practicas_signlab.api.Models.Post;

public interface PostAddInteractor {
    void onSetDataToApi(int userId, String title, String body, PostAddInteractor.onSetDataToApiCallbacks callBacks, PostAddInteractor.OnErrorServer errorServer);

    interface onSetDataToApiCallbacks {
        void onSetDataToApiSuccessCallbacks(Post response);
        void onErrorCallBacks(String s);
    }

    interface OnErrorServer {
        void errorServerMessage(String message);
    }
}
