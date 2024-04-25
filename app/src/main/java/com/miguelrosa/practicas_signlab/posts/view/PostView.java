package com.miguelrosa.practicas_signlab.posts.view;

import com.miguelrosa.practicas_signlab.api.Models.Comment;

import java.util.ArrayList;

public interface PostView {
    void showComments(ArrayList<Comment> comment);
}
