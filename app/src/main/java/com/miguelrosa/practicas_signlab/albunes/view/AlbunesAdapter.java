package com.miguelrosa.practicas_signlab.albunes.view;

import static android.app.PendingIntent.getActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miguelrosa.practicas_signlab.api.Models.Album;
import com.miguelrosa.practicas_signlab.api.Models.User;
import com.miguelrosa.practicas_signlab.databinding.ItemAlbumBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlbunesAdapter extends RecyclerView.Adapter<AlbunesAdapter.AlbumViewHolder> implements Filterable {
    private ArrayList<Album> albums;
    private ArrayList<Album> albumsFiltered;
    private static ArrayList<User> users;

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
        this.albumsFiltered = albums;
    }

    public void setUsers(ArrayList<User> users){
        this.users=users;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAlbumBinding binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AlbumViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        holder.bind(albumsFiltered.get(position));
    }

    @Override
    public int getItemCount() {
        return albumsFiltered != null ? albumsFiltered.size() : 0;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                if (constraint == null || constraint.length() == 0) {
                    results.values = albums;
                    results.count = albums.size();
                } else {
                    ArrayList<Album> filteredList = new ArrayList<>();
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (Album album : albums) {
                        if (album.getTitle().toLowerCase().contains(filterPattern)) {
                            filteredList.add(album);
                        }
                    }
                    results.values = filteredList;
                    results.count = filteredList.size();
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                albumsFiltered = (ArrayList<Album>) results.values;
                notifyDataSetChanged();
            }
        };

        return filter;
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        ItemAlbumBinding binding;

        public AlbumViewHolder(@NonNull ItemAlbumBinding itemAlbumBinding) {
            super(itemAlbumBinding.getRoot());
            binding = itemAlbumBinding;
        }

        public void bind(Album album) {
            binding.textViewId.setText(album.getUserId());
            binding.textViewTitle.setText(album.getTitle());

            for (User user : users) {
                if (user.getId() == Integer.parseInt(album.getUserId())) {
                    binding.textViewId.setText(user.getName());
                    break;
                }
            }
        }
    }
}
