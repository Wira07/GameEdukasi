package com.putrimaharani.gameedukasi.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.putrimaharani.gameedukasi.data.MenuItem;
import com.putrimaharani.gameedukasi.databinding.ItemPutriBinding;

import java.util.List;

// Data class untuk setiap item menu
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private final List<MenuItem> menuList;
    private final OnItemClickListener onItemClickListener;
    // Consttor
    public MenuAdapter(List<MenuItem> menuList, OnItemClickListener onItemClickListener) {
        this.menuList = menuList;
        this.onItemClickListener = onItemClickListener;
    }

    // Interface untuk klik listener
    public interface OnItemClickListener {
        void onItemClick(MenuItem menuItem);
    }

    // ViewHolder menggunakan View Binding
    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        private final ItemPutriBinding binding;

        public MenuViewHolder(ItemPutriBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        // Fungsi bind untuk mengisi data ke dalam View
        public void bind(MenuItem menuItem, OnItemClickListener onItemClickListener) {
            binding.menuTitle.setText(menuItem.getTitle());
            binding.menuDescription.setText(menuItem.getDescription());
            binding.menuIcon.setImageResource(menuItem.getIconResId());

            // Menambahkan listener klik untuk item
            binding.getRoot().setOnClickListener(v -> onItemClickListener.onItemClick(menuItem));
        }
    }

    // Membuat ViewHolder dengan View Binding
    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPutriBinding binding = ItemPutriBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MenuViewHolder(binding);
    }

    // Menghubungkan data dengan View
    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItem menuItem = menuList.get(position);
        holder.bind(menuItem, onItemClickListener);
    }

    // Mengembalikan jumlah item
    @Override
    public int getItemCount() {
        return menuList.size();
    }
}