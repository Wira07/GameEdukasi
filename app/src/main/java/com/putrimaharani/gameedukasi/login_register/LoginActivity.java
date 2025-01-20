package com.putrimaharani.gameedukasi.login_register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.putrimaharani.gameedukasi.MainGame;
import com.putrimaharani.gameedukasi.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private static final String PREFS_NAME = "GameEdukasiPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableEdgeToEdge();
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());  

        // Cek status login
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);
        if (isLoggedIn) {
            navigateToMainGame();
        }

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

        binding.signupLink.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        binding.loginButton.setOnClickListener(v -> {
            // Validasi username
            String username = binding.emailInput.getText().toString().trim();
            String savedUsername = preferences.getString("username", "");

            if (username.equals(savedUsername)) {
                // Simpan status login
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isLoggedIn", true);
                editor.apply();

                navigateToMainGame();
            } else {
                binding.emailInput.setError("Username tidak valid");
            }
        });
    }

    private void enableEdgeToEdge() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );
        getWindow().setStatusBarColor(android.graphics.Color.TRANSPARENT);
    }

    private void navigateToMainGame() {
        Intent intent = new Intent(this, MainGame.class);
        startActivity(intent);
        finish(); // Tutup LoginActivity
    }
}
