package com.putrimaharani.gameedukasi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.putrimaharani.gameedukasi.data.Score;
import com.putrimaharani.gameedukasi.databinding.ItemScoreBinding;

import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {

    private List<Score> scores;

    public ScoreAdapter(List<Score> scores) {
        this.scores = scores;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use ViewBinding to inflate the item layout
        ItemScoreBinding binding = ItemScoreBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ScoreViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        Score score = scores.get(position);
        holder.binding.playerName.setText(score.getPlayerName());
        holder.binding.score.setText(String.valueOf(score.getScore()));
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    static class ScoreViewHolder extends RecyclerView.ViewHolder {
        private final ItemScoreBinding binding;

        public ScoreViewHolder(ItemScoreBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
