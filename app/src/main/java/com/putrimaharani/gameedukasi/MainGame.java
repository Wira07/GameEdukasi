package com.putrimaharani.gameedukasi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Insets;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.ViewFlipper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.putrimaharani.gameedukasi.adapter.MenuAdapter;
import com.putrimaharani.gameedukasi.data.MenuItem;
import com.putrimaharani.gameedukasi.databinding.ActivityMainGameBinding;
import com.putrimaharani.gameedukasi.menu.LeaderboardActivity;
import com.putrimaharani.gameedukasi.menu.QuizActivity;
import com.putrimaharani.gameedukasi.menu.SettingsActivity;
import java.util.Arrays;
import java.util.List;

public class MainGame extends AppCompatActivity {
    private ActivityMainGameBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Menyembunyikan status bar dan navigasi untuk layar penuh
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        // Menangani status bar dengan menggunakan WindowInsets
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().getDecorView().setOnApplyWindowInsetsListener((view, insets) -> {
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
        List<MenuItem> menuList = Arrays.asList(
                new MenuItem("Start Game", "Mulai permainan baru", R.drawable.logo_play),
                new MenuItem("Leaderboard", "Lihat skor tertinggi", R.drawable.ic_quiz),
                new MenuItem("Setting", "Pengaturan permainan", R.drawable.ic_setting)
        );

        // Mengatur RecyclerView dengan listener klik
        MenuAdapter adapter = new MenuAdapter(menuList, selectedItem -> {
            if (selectedItem != null) {
                String title = selectedItem.getTitle();
                if ("Start Game".equals(title)) {
                    startActivity(new Intent(MainGame.this, QuizActivity.class));
                } else if ("Leaderboard".equals(title)) {
                    startActivity(new Intent(MainGame.this, LeaderboardActivity.class));
                } else if ("Setting".equals(title)) {
                    startActivity(new Intent(MainGame.this, SettingsActivity.class));
                }
            }
        });

        // Mengatur RecyclerView
        binding.yogaKriyaList.setLayoutManager(new LinearLayoutManager(this));
        binding.yogaKriyaList.setAdapter(adapter);

        // Mengatur ViewFlipper
        setupYogaFlipper();

        // Setup Bottom Navigation
        setupBottomNavigation();
    }

    private void setupYogaFlipper() {
        ViewFlipper viewFlipper = binding.yogaFlipper;

        // Mengatur animasi untuk ViewFlipper
        viewFlipper.setInAnimation(this, R.anim.slide_in_right);
        viewFlipper.setOutAnimation(this, R.anim.slide_out_left);

        // Mulai auto flip
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(3000); // Interval 3 detik
    }

    @SuppressLint("NonConstantResourceId")
    private void setupBottomNavigation() {
        // Pastikan ID sesuai dengan XML
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        // Set item aktif untuk halaman Settings
        bottomNavigation.setSelectedItemId(R.id.navigation_home);

        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
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
