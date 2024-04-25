package com.miguelrosa.practicas_signlab.portadas.interactor;

import com.miguelrosa.practicas_signlab.api.Models.Photo;
import com.miguelrosa.practicas_signlab.api.wsApi.WsApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PortadasInteractorImpl implements PortadasInteractor {
    @Inject
    WsApi wsApi;

    @Inject
    public PortadasInteractorImpl(){}

    @Override
    public void getPhotosFromApi(OnGetPhotosCallBacks callBacks, OnErrorServer errorServer) {
        wsApi.getAllPhotos().enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if(response.isSuccessful()){
                    callBacks.onSuccessCallBacks(new ArrayList<Photo>(response.body()));
                }else{
                    callBacks.onErrorCallBacks(response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                errorServer.errorServerMessage(Arrays.toString(t.getStackTrace()));
            }
        });
    }
}
