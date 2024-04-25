package com.miguelrosa.practicas_signlab.usuarios.view;

import com.miguelrosa.practicas_signlab.api.Models.User;

import java.util.ArrayList;

public interface UsuariosFragment {
    void onItemClick(User user);

    void showUsers(ArrayList<User> users);
}
