package com.miguelrosa.practicas_signlab.usuarios.view;

import com.miguelrosa.practicas_signlab.api.Models.Post;
import com.miguelrosa.practicas_signlab.api.Models.Todo;

import java.util.ArrayList;

public interface PerfilUsuario {
    void showTodos(ArrayList<Todo> comments);

    void showPosts(ArrayList<Post> posts);
}
