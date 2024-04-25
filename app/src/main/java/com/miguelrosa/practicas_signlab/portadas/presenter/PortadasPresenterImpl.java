package com.miguelrosa.practicas_signlab.portadas.presenter;

import android.util.Log;

import androidx.annotation.Nullable;

import com.miguelrosa.practicas_signlab.api.Models.Photo;
import com.miguelrosa.practicas_signlab.portadas.interactor.PortadasInteractor;
import com.miguelrosa.practicas_signlab.portadas.view.PortadasFragment;

import java.util.ArrayList;

import javax.inject.Inject;

public class PortadasPresenterImpl implements PortadasPresenter, PortadasInteractor.OnGetPhotosCallBacks, PortadasInteractor.OnErrorServer {

    @Nullable
    @Inject
    PortadasFragment portadasview;

    @Inject
    PortadasInteractor portadasinteractor;

    @Inject
    public PortadasPresenterImpl(){}

    @Override
    public void onPhotosFetched() {
        portadasinteractor.getPhotosFromApi(this, this);
    }

    @Override
    public void onSuccessCallBacks(ArrayList<Photo> photos) {
        Log.i("respuesta", String.valueOf(photos));
        portadasview.showPhotos(photos);

    }

    @Override
    public void onErrorCallBacks(int code) {
        Log.e("respuesta erronea", ""+code);

    }

    @Override
    public void errorServerMessage(String message) {

    }
}
