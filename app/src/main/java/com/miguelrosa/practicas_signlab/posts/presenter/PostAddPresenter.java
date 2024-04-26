package com.miguelrosa.practicas_signlab.posts.presenter;

public interface PostAddPresenter {
    void onAddPost(int userId, String title, String body);
    void onEditPost(int postId, int id, String title, String body, int userId);
}
