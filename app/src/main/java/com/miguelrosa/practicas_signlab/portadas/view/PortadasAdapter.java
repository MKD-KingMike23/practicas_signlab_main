package com.miguelrosa.practicas_signlab.portadas.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miguelrosa.practicas_signlab.api.Models.Photo;
import com.miguelrosa.practicas_signlab.databinding.ItemPhotoBinding;
import com.miguelrosa.practicas_signlab.databinding.ItemUserBinding;

import java.util.ArrayList;

public class PortadasAdapter extends RecyclerView.Adapter<PortadasAdapter.PortadaViewHolder> {
    private ArrayList<Photo> photos;

    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    @NonNull
    @Override
    public PortadaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPhotoBinding binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PortadaViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PortadaViewHolder holder, int position) {
        Photo photo = photos.get(position);
        // Configurar vistas con datos de usuario
        holder.bind(photo);
    }

    @Override
    public int getItemCount() {
        return photos != null ? photos.size() : 0;
    }

    public static class PortadaViewHolder extends RecyclerView.ViewHolder {
        ItemPhotoBinding binding;

        public PortadaViewHolder(@NonNull ItemPhotoBinding itemPhotoBinding) {
            super(itemPhotoBinding.getRoot());
            binding = itemPhotoBinding;
        }

        public void bind(Photo photo) {
            binding.textViewAlbumId.setText(photo.getAlbumId());
            // binding.textViewUsername.setText(String.valueOf(user.getUsername()));
            // Puedes configurar más vistas aquí
        }
    }
}
