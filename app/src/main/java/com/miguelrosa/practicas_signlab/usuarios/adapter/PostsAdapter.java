package com.miguelrosa.practicas_signlab.usuarios.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miguelrosa.practicas_signlab.api.Models.Post;
import com.miguelrosa.practicas_signlab.api.Models.Todo;
import com.miguelrosa.practicas_signlab.databinding.ItemPostBinding;

import java.util.ArrayList;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {
    private ArrayList<Post> posts;
    private PostClickListener postClickListener;

    public PostsAdapter(PostClickListener postListener) {this.postClickListener = postListener;}

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostBinding binding = ItemPostBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PostViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts != null ? posts.size() : 0;
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        ItemPostBinding binding;

        public PostViewHolder(@NonNull ItemPostBinding itemPostBinding) {
            super(itemPostBinding.getRoot());
            binding = itemPostBinding;
        }

        public void bind(Post post) {

            binding.textViewTitle.setText(post.getTitle());
            binding.textViewTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){postClickListener.onPostClicked(post);}
            });
            binding.imageViewEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {postClickListener.onEditClicked(post);}
            });
        }
    }
    public interface PostClickListener{
        void onPostClicked(Post post);
        void onEditClicked(Post post);
    }
}
