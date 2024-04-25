package com.miguelrosa.practicas_signlab.posts.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miguelrosa.practicas_signlab.api.Models.Comment;
import com.miguelrosa.practicas_signlab.databinding.ItemCommentBinding;


import java.util.ArrayList;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {
    private ArrayList<Comment> comments;

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCommentBinding binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CommentsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.bind(comment);
    }

    @Override
    public int getItemCount() {
        return comments != null ? comments.size() : 0;
    }

    public class CommentsViewHolder extends RecyclerView.ViewHolder {
        ItemCommentBinding binding;

        public CommentsViewHolder(@NonNull ItemCommentBinding itemCommentBinding) {
            super(itemCommentBinding.getRoot());
            binding = itemCommentBinding;
        }

        public void bind(Comment comment) {
            binding.textViewName.setText(comment.getName());
            binding.textViewEmail.setText(comment.getEmail());
            binding.textViewBody.setText(comment.getBody());
        }
    }
}
