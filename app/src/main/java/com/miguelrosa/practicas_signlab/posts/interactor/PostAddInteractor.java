package com.miguelrosa.practicas_signlab.posts.interactor;

import com.miguelrosa.practicas_signlab.api.Models.Post;

public interface PostAddInteractor {
    void onAddDataToApi(int userId, String title, String body, PostAddInteractor.onSetDataToApiCallbacks callBacks, PostAddInteractor.OnErrorServer errorServer);
    void onEditDataToApi(int postId, int id, String title, String body, int userId, PostAddInteractor.onSetDataToApiCallbacks callBacks, PostAddInteractor.OnErrorServer errorServer);

    interface onSetDataToApiCallbacks {
        void onSetDataToApiSuccessCallbacks(Post response);
        void onErrorCallBacks(String s);
    }

    interface OnErrorServer {
        void errorServerMessage(String message);
    }
}
