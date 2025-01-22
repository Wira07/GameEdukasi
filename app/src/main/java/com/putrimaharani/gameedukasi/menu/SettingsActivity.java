package com.putrimaharani.gameedukasi.menu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.putrimaharani.gameedukasi.MainGame;
import com.putrimaharani.gameedukasi.ProfileActivity;
import com.putrimaharani.gameedukasi.R;
import com.putrimaharani.gameedukasi.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {

    private ActivitySettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Inflate layout menggunakan ViewBinding
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Mengatur padding sesuai dengan insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Menangani status bar dengan menggunakan WindowInsets
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            getWindow().getDecorView().setOnApplyWindowInsetsListener((view, insets) -> {
                // Menghilangkan padding/area hitam
                android.graphics.Insets systemBarsInsets = insets.getInsets(WindowInsets.Type.systemBars());
                view.setPadding(0, 0, 0, 0); // Pastikan padding dihapus
                return WindowInsets.CONSUMED; // Gunakan WindowInsets.CONSUMED
            });
        } else {
            // Cara lama untuk versi sebelum Android 11
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }

        setupBottomNavigation(); // Memanggil metode setup bottom navigation
    }
    @SuppressLint("NonConstantResourceId")
    private void setupBottomNavigation() {
        // Pastikan ID sesuai dengan XML
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        // Set item aktif untuk halaman Settings
        bottomNavigation.setSelectedItemId(R.id.navigation_profile);
        // Set listener untuk navigasi bawah
        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId(); // Ambil ID menu item
            if (itemId == R.id.navigation_home) {
                startActivity(new Intent(SettingsActivity.this, MainGame.class));
                return true;
            } else if (itemId == R.id.navigation_library) {
                startActivity(new Intent(SettingsActivity.this, StartGameActivity.class));
                return true;
            } else if (itemId == R.id.navigation_profile) {
                startActivity(new Intent(SettingsActivity.this, ProfileActivity.class));
                return true;
            } else {
                return false;
            }
        });
    }
}
