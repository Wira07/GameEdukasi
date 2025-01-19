package com.putrimaharani.gameedukasi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Insets;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.putrimaharani.gameedukasi.adapter.MenuAdapter;
import com.putrimaharani.gameedukasi.data.MenuItem;
import com.putrimaharani.gameedukasi.databinding.ActivityMainGameBinding;
import com.putrimaharani.gameedukasi.menu.LeaderboardActivity;
import com.putrimaharani.gameedukasi.menu.QuizActivity;
import com.putrimaharani.gameedukasi.menu.SettingsActivity;

public class MainGame extends AppCompatActivity {
    private ActivityMainGameBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Menyembunyikan status bar dan navigasi untuk layar penuh
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        // Menangani status bar dengan menggunakan WindowInsets
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            getWindow().getDecorView().setOnApplyWindowInsetsListener((view, insets) -> {
                // Menghilangkan padding/area hitam
                Insets systemBarsInsets = insets.getInsets(WindowInsets.Type.systemBars());
                view.setPadding(0, 0, 0, 0); // Pastikan padding dihapus
                return WindowInsets.CONSUMED; // Gunakan WindowInsets.CONSUMED
            });
        } else {
            // Cara lama untuk versi sebelum Android 11
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
            );
        }

        // Inisialisasi View Binding
        binding = ActivityMainGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Data untuk RecyclerView
        java.util.List<MenuItem> menuList = java.util.Arrays.asList(
                new MenuItem("Start Game", "Mulai permainan baru", R.drawable.logo_play),
                new MenuItem("Leaderboard", "Lihat skor tertinggi", R.drawable.quiz),
                new MenuItem("About", "Pengaturan permainan", R.drawable.about)
        );

        // Mengatur RecyclerView dengan listener klik
        MenuAdapter adapter = new MenuAdapter(menuList, selectedItem -> {
            if (selectedItem instanceof MenuItem) {
                String title = ((MenuItem) selectedItem).getTitle();
                if ("Start Game".equals(title)) {
                    startActivity(new Intent(MainGame.this, QuizActivity.class));
                } else if ("Leaderboard".equals(title)) {
                    startActivity(new Intent(MainGame.this, LeaderboardActivity.class));
                } else if ("Settings".equals(title)) {
                    startActivity(new Intent(MainGame.this, SettingsActivity.class));
                }
            }
        });

        // Mengatur RecyclerView
        binding.yogaKriyaList.setLayoutManager(new LinearLayoutManager(this));
        binding.yogaKriyaList.setAdapter(adapter);

        // Setup Bottom Navigation
        setupBottomNavigation();
    }


    @SuppressLint("NonConstantResourceId")
    private void setupBottomNavigation() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId(); // Ambil item ID
            if (itemId == R.id.navigation_home) {
                startActivity(new Intent(MainGame.this, MainActivity.class));
                return true;
            } else if (itemId == R.id.navigation_library) {
                startActivity(new Intent(MainGame.this, MainActivity.class));
                return true;
            } else if (itemId == R.id.navigation_profile) {
                startActivity(new Intent(MainGame.this, ProfileActivity.class));
                return true;
            } else {
                return false;
            }
        });
    }
}
