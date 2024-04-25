package com.miguelrosa.practicas_signlab.posts.view;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miguelrosa.practicas_signlab.api.Models.Post;
import com.miguelrosa.practicas_signlab.api.Models.Comment;
import com.miguelrosa.practicas_signlab.databinding.ActivityPostBinding;
import com.miguelrosa.practicas_signlab.di.appComponent.AppComponent;
import com.miguelrosa.practicas_signlab.di.appComponent.DaggerAppComponent;
import com.miguelrosa.practicas_signlab.di.appModule.AppModule;
import com.miguelrosa.practicas_signlab.di.appModule.SharedPreferencesModule;
import com.miguelrosa.practicas_signlab.posts.adapter.CommentsAdapter;
import com.miguelrosa.practicas_signlab.posts.presenter.PostPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

public class PostViewImpl extends AppCompatActivity implements PostView {

    private ActivityPostBinding binding;
    private Post post;
    private RecyclerView rvComments;
    private CommentsAdapter commentsAdapter;
    private ActionBar actionbar;
    @Inject
    PostPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initInjection();

        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setTitle("Post");

        post = getIntent().getParcelableExtra("post");

        rvComments = binding.rvComments;
        rvComments.setLayoutManager(new LinearLayoutManager(this));



        binding.textViewTitle.setText(post.getTitle());
        binding.textViewBody.setText(post.getBody());

        presenter.onCommentsFetched(post.getId());
        commentsAdapter = new CommentsAdapter();
        rvComments.setAdapter(commentsAdapter);
    }

    private void initInjection() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, this))
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .build();
        appComponent.inject(this);
    }

    @Override
    public void showComments(ArrayList<Comment> comment) {
        commentsAdapter.setComments(comment);
        commentsAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
