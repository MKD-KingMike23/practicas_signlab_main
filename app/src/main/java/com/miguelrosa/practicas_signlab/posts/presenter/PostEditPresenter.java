package com.miguelrosa.practicas_signlab.posts.presenter;

public interface PostEditPresenter {
    void onGuardar(int postId, int id, String title, String body, int userId);
}
