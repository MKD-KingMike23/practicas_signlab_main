package com.miguelrosa.practicas_signlab.albunes.interactor;


import com.miguelrosa.practicas_signlab.api.Models.Album;
import com.miguelrosa.practicas_signlab.api.Models.User;

import java.util.ArrayList;

public interface AlbunesInteractor {

    void getAlbumsFromApi(OnGetAlbumsCallBacks callBacks, OnErrorServer errorServer);
    void getUsersFromApi(OnGetUsersCallBacks callBacks, OnErrorServer errorServer);

    interface OnGetAlbumsCallBacks {
        void onAlbumsSuccessCallBacks(ArrayList<Album> albums);
        void onErrorCallBacks(int code);
    }

    interface OnGetUsersCallBacks {
        void onUsersSuccessCallBacks(ArrayList<User> users);
        void onErrorCallBacks(int code);
    }

    interface OnErrorServer {
        void errorServerMessage(String message);
    }
}
