package com.putrimaharani.gameedukasi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.putrimaharani.gameedukasi.R;
import com.putrimaharani.gameedukasi.data.Wisata;

import java.util.List;

public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.ViewHolder> {

    private Context context;
    private List<Wisata> wisataList;
    private OnItemClickListener listener;

    // Add listener as a parameter to the constructor
    public WisataAdapter(Context context, List<Wisata> wisataList, OnItemClickListener listener) {
        this.context = context;
        this.wisataList = wisataList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wisata, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Wisata wisata = wisataList.get(position);
        holder.textViewNama.setText(wisata.getNama());
        holder.textViewDeskripsi.setText(wisata.getDeskripsi());
        holder.imageViewGambar.setImageResource(wisata.getGambar());

        // Set the click listener for the item view
        holder.itemView.setOnClickListener(v -> listener.onItemClick(wisata));
    }

    @Override
    public int getItemCount() {
        return wisataList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Wisata wisata);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewGambar;
        TextView textViewNama;
        TextView textViewDeskripsi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewGambar = itemView.findViewById(R.id.imageViewGambar);
            textViewNama = itemView.findViewById(R.id.textViewNama);
            textViewDeskripsi = itemView.findViewById(R.id.textViewDeskripsi);
        }
    }
}
