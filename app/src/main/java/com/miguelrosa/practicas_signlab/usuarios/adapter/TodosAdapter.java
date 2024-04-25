package com.miguelrosa.practicas_signlab.usuarios.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.miguelrosa.practicas_signlab.api.Models.Todo;
import com.miguelrosa.practicas_signlab.databinding.ItemTodoBinding;
import java.util.ArrayList;

public class TodosAdapter extends RecyclerView.Adapter<TodosAdapter.TodoViewHolder> {
    private ArrayList<Todo> todos;
    private TaskClickListener taskClickListener;

    public TodosAdapter(TaskClickListener taskListener) {this.taskClickListener = taskListener;}

    public void setTodos(ArrayList<Todo> todos) {
        this.todos = todos;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTodoBinding binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TodoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = todos.get(position);
        holder.bind(todo);
    }

    @Override
    public int getItemCount() {
        return todos != null ? todos.size() : 0;
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {
        ItemTodoBinding binding;

        public TodoViewHolder(@NonNull ItemTodoBinding itemTodoBinding) {
            super(itemTodoBinding.getRoot());
            binding = itemTodoBinding;
        }

        public void bind(Todo todo) {
            binding.textViewTodoTitle.setText(todo.getTitle());

            binding.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    taskClickListener.onTaskClicked(todo);
                }
            });
            binding.checkBox.setChecked(todo.isCompleted());
        }
    }

    public interface TaskClickListener{
        void onTaskClicked(Todo todo);
    }
}
