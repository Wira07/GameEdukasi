
package com.putrimaharani.gameedukasi.menu;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.putrimaharani.gameedukasi.adapter.ScoreAdapter;
import com.putrimaharani.gameedukasi.data.Score;
import com.putrimaharani.gameedukasi.databinding.ActivityLeaderboardBinding;
import com.putrimaharani.gameedukasi.R;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {

    private ActivityLeaderboardBinding binding;
    private ScoreAdapter scoreAdapter;
    private List<Score> scoreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize ViewBinding
        binding = ActivityLeaderboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Sistem insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize score list
        scoreList = new ArrayList<>();
        scoreList.add(new Score("Player 1", 1200));
        scoreList.add(new Score("Player 2", 1100));
        scoreList.add(new Score("Player 3", 1000));

        // Set up RecyclerView using ViewBinding
        scoreAdapter = new ScoreAdapter(scoreList);
        binding.scoreRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.scoreRecyclerView.setAdapter(scoreAdapter);
    }
}
