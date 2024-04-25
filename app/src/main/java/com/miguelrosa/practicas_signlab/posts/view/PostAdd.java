package com.miguelrosa.practicas_signlab.posts.view;

import com.miguelrosa.practicas_signlab.api.Models.Post;

public interface PostAdd {
    void showAnswer(String s);

    void onShowSuccessData(Post response);
}
