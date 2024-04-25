package com.miguelrosa.practicas_signlab.albunes.view;

import com.miguelrosa.practicas_signlab.api.Models.Album;
import com.miguelrosa.practicas_signlab.api.Models.User;

import java.util.ArrayList;

public interface AlbunesFragment {
    void showAlbums(ArrayList<Album> albums);
    void showUser(ArrayList<User> users);
}
