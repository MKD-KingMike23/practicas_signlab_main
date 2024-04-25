package com.miguelrosa.practicas_signlab.albunes.presenter;

import android.util.Log;

import androidx.annotation.Nullable;

import com.miguelrosa.practicas_signlab.api.Models.Album;
import com.miguelrosa.practicas_signlab.albunes.interactor.AlbunesInteractor;
import com.miguelrosa.practicas_signlab.albunes.view.AlbunesFragment;
import com.miguelrosa.practicas_signlab.api.Models.User;

import java.util.ArrayList;

import javax.inject.Inject;

public class AlbunesPresenterImpl implements AlbunesPresenter, AlbunesInteractor.OnGetAlbumsCallBacks,AlbunesInteractor.OnGetUsersCallBacks , AlbunesInteractor.OnErrorServer {
    @Nullable
    @Inject
    AlbunesFragment view;

    @Inject
    AlbunesInteractor albunesinteractor;

    @Inject
    public AlbunesPresenterImpl(){}

    @Override
    public void onAlbumsFetched() {
        albunesinteractor.getAlbumsFromApi(this, this);
        albunesinteractor.getUsersFromApi((AlbunesInteractor.OnGetUsersCallBacks) this, this);
    }

    public void onUsersFetched() {
        albunesinteractor.getUsersFromApi((AlbunesInteractor.OnGetUsersCallBacks) this, this);
    }


    @Override
    public void onAlbumsSuccessCallBacks(ArrayList<Album> albums) {
        view.showAlbums(albums);
    }

    @Override
    public void onUsersSuccessCallBacks(ArrayList<User> users) {
        view.showUser(users);
    }

    @Override
    public void onErrorCallBacks(int code) {
        Log.e("respuesta erronea", ""+code);

    }

    @Override
    public void errorServerMessage(String message) {

    }
}
