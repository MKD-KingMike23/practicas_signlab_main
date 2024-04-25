package com.miguelrosa.practicas_signlab.posts.view;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.miguelrosa.practicas_signlab.api.Models.Post;
import com.miguelrosa.practicas_signlab.databinding.ActivityEditpostBinding;
import com.miguelrosa.practicas_signlab.di.appComponent.AppComponent;
import com.miguelrosa.practicas_signlab.di.appComponent.DaggerAppComponent;
import com.miguelrosa.practicas_signlab.di.appModule.AppModule;
import com.miguelrosa.practicas_signlab.di.appModule.SharedPreferencesModule;
import com.miguelrosa.practicas_signlab.posts.presenter.PostEditPresenter;

import javax.inject.Inject;

public class PostEditImpl extends AppCompatActivity implements PostEdit{
    private ActivityEditpostBinding binding;
    private Post post;
    private ActionBar actionbar;
    @Inject
    PostEditPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditpostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initInjection();

        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setTitle("Editar Post");

        post = getIntent().getParcelableExtra("post");

        setFill(post);

        binding.buttonEditPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onGuardar(post.getId(), post.getId(), binding.editTextTitle.getText().toString(), binding.editTextBody.getText().toString(), post.getUserId());
                finish();
            }
        });;
    }

    private void initInjection() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, this))
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .build();
        appComponent.inject(this);
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

    private void setFill(com.miguelrosa.practicas_signlab.api.Models.Post post) {
        binding.editTextTitle.setText(post.getTitle());
        binding.editTextBody.setText(post.getBody());
    }

    @Override
    public void showAnswer(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowSuccessData(com.miguelrosa.practicas_signlab.api.Models.Post response) {
        setFill(response);
    }
}
