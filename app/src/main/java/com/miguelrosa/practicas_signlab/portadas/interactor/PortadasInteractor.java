package com.miguelrosa.practicas_signlab.portadas.interactor;

import com.miguelrosa.practicas_signlab.api.Models.Photo;
import java.util.ArrayList;

public interface PortadasInteractor {

    void getPhotosFromApi(OnGetPhotosCallBacks callBacks, OnErrorServer errorServer);

    interface OnGetPhotosCallBacks {
        void onSuccessCallBacks(ArrayList<Photo> photos);
        void onErrorCallBacks(int code);
    }

    interface OnErrorServer {
        void errorServerMessage(String message);
    }
}
