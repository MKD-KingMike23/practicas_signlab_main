package com.miguelrosa.practicas_signlab.usuarios.view;

import com.miguelrosa.practicas_signlab.R;
import com.miguelrosa.practicas_signlab.api.Models.Todo;
import com.miguelrosa.practicas_signlab.api.Models.User;
import com.miguelrosa.practicas_signlab.databinding.ActivityPerfilusuarioBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miguelrosa.practicas_signlab.api.Models.Post;
import com.miguelrosa.practicas_signlab.di.appComponent.AppComponent;
import com.miguelrosa.practicas_signlab.di.appComponent.DaggerAppComponent;
import com.miguelrosa.practicas_signlab.di.appModule.AppModule;
import com.miguelrosa.practicas_signlab.di.appModule.SharedPreferencesModule;
import com.miguelrosa.practicas_signlab.home.view.HomeActivity;
import com.miguelrosa.practicas_signlab.posts.view.PostAddImpl;
import com.miguelrosa.practicas_signlab.posts.view.PostEditImpl;
import com.miguelrosa.practicas_signlab.posts.view.PostViewImpl;
import com.miguelrosa.practicas_signlab.usuarios.adapter.PostsAdapter;
import com.miguelrosa.practicas_signlab.usuarios.adapter.TodosAdapter;
import com.miguelrosa.practicas_signlab.usuarios.presenter.PerfilUsuarioPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

public class PerfilUsuarioImpl extends AppCompatActivity implements PerfilUsuario, TodosAdapter.TaskClickListener, PostsAdapter.PostClickListener {

    private ActivityPerfilusuarioBinding binding;
    private User user;
    private RecyclerView rvPosts;
    private RecyclerView rvTodos;
    private TodosAdapter todosAdapter;
    private PostsAdapter postsAdapter;
    private ActionBar actionbar;
    @Inject
    PerfilUsuarioPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPerfilusuarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initInjection();

        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setTitle("Perfil");

        user = getIntent().getParcelableExtra("user");

        rvPosts = binding.rvPosts;
        rvTodos = binding.rvTodos;
        rvPosts.setLayoutManager(new LinearLayoutManager(this));
        rvTodos.setLayoutManager(new LinearLayoutManager(this));

        binding.textViewName.setText(user.getName());
        binding.textViewUsername.setText(user.getUsername());
        binding.textViewEmail.setText(user.getEmail());

        presenter.onPostsFetched(user.getId());
        postsAdapter = new PostsAdapter(this);
        rvPosts.setAdapter(postsAdapter);

        presenter.onTodosFetched(user.getId());
        todosAdapter = new TodosAdapter(this);
        rvTodos.setAdapter(todosAdapter);
    }

    private void initInjection() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, this))
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .build();
        appComponent.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_post_usuario, menu);
        return true;
    }

    public void onGoBack(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void showTodos(ArrayList<Todo> todos) {
        todosAdapter.setTodos(todos);
        todosAdapter.notifyDataSetChanged();

    }

    @Override
    public void showPosts(ArrayList<Post> posts) {
        postsAdapter.setPosts(posts);
        postsAdapter.notifyDataSetChanged();

    }

    @Override
    public void onTaskClicked(Todo todo) {
        todo.setCompleted(!todo.isCompleted());
        todosAdapter.notifyDataSetChanged();
    }

    public void onPostClicked(Post post) {
        Intent intent = new Intent(PerfilUsuarioImpl.this, PostViewImpl.class);
        intent.putExtra("post", post);
        startActivity(intent);
    }

    @Override
    public void onEditClicked(Post post) {
        Intent intent = new Intent(PerfilUsuarioImpl.this, PostEditImpl.class);
        intent.putExtra("post", post);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

            if (id == android.R.id.home) {
                finish();
            }

            if (id == R.id.add){
            Intent intent = new Intent(this, PostAddImpl.class);
            intent.putExtra("user", user);
            activityResultLauncher.launch(intent);
            }

        return super.onOptionsItemSelected(item);
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            if (o.getResultCode() == RESULT_OK){
                Intent intent  = o.getData();
                presenter.onPostsFetched(user.getId());
            }
        }
    });
}