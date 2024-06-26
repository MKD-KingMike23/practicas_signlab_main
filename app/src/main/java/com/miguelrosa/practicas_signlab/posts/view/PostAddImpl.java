package com.miguelrosa.practicas_signlab.posts.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.miguelrosa.practicas_signlab.api.Models.Post;
import com.miguelrosa.practicas_signlab.api.Models.User;
import com.miguelrosa.practicas_signlab.databinding.ActivityAddpostBinding;
import com.miguelrosa.practicas_signlab.di.appComponent.AppComponent;
import com.miguelrosa.practicas_signlab.di.appComponent.DaggerAppComponent;
import com.miguelrosa.practicas_signlab.di.appModule.AppModule;
import com.miguelrosa.practicas_signlab.di.appModule.SharedPreferencesModule;
import com.miguelrosa.practicas_signlab.posts.presenter.PostAddPresenter;

import javax.inject.Inject;

public class PostAddImpl extends AppCompatActivity implements PostAdd {
    private ActivityAddpostBinding binding;
    private ActionBar actionBar;
    private User user;
    private Post post;
    @Inject
    PostAddPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddpostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initInjection();

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Crear post");

        user = getIntent().getParcelableExtra("user");
        post = getIntent().getParcelableExtra("post");

        if(post != null){
            actionBar.setTitle("Editar post");
            setField(post);
        }
        binding.buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (post != null) {
                    presenter.onEditPost(post.getId(), post.getId(), binding.editTextTitle.getText().toString(), binding.editTextBody.getText().toString(), post.getUserId());
                    finish();
                } else {
                    presenter.onAddPost(user.getId(), binding.editTextTitle.getText().toString(), binding.editTextBody.getText().toString());
                    finish();
                }
            }
        });
    }

    private void initInjection() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, this))
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .build();
        appComponent.inject(this);
    }

    private void setField(com.miguelrosa.practicas_signlab.api.Models.Post post) {
        binding.editTextTitle.setText(post.getTitle());
        binding.editTextBody.setText(post.getBody());
        binding.buttonGuardar.setText("EDITAR POST");
        binding.textView.setText("Editar Post");
    }

    @Override
    public void showAnswer(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowSuccessData(Post response) {
        Toast.makeText(this, response.getTitle()+"\n"+response.getBody(), Toast.LENGTH_SHORT).show();
        if(post != null){
            setField(post);
            finish();
        }else{
            Intent intent = new Intent();
            intent.putExtra("user", user);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
