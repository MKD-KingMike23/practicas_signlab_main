package com.miguelrosa.practicas_signlab.usuarios.interactor;

import com.miguelrosa.practicas_signlab.api.Models.User;
import com.miguelrosa.practicas_signlab.api.wsApi.WsApi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuariosInteractorImpl implements UsuariosInteractor {

    @Inject
    WsApi wsApi;

    @Inject
    public UsuariosInteractorImpl(){
    }

    @Override
    public void getUsersFromApi(OnGetUsersCallBacks callBack, OnErrorServer errorServer) {
        wsApi.getAllUsers().enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    callBack.onSuccessCallBacks(new ArrayList<User>(response.body()));
                }else{
                    callBack.onErrorCallBacks(response.code());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                errorServer.errorServerMessage(Arrays.toString(t.getStackTrace()));
            }
        });
    }
}
