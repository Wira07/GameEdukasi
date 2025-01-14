package com.putrimaharani.gameedukasi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.putrimaharani.gameedukasi.databinding.ActivityProfileBinding;
import com.putrimaharani.gameedukasi.login_register.LoginActivity;
import com.putrimaharani.gameedukasi.menu.StartGameActivity;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding; // Deklarasi binding

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inisialisasi binding
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);

        // Terapkan padding sesuai dengan window insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        setupBottomNavigation(); // Memanggil metode setup bottom navigation
        setupLogoutButton();
    }

    @SuppressLint("NonConstantResourceId")
    private void setupBottomNavigation() {
        // Set listener untuk navigasi bawah
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                startActivity(new Intent(ProfileActivity.this, MainGame.class));
                return true;
            } else if (itemId == R.id.navigation_library) {
                startActivity(new Intent(ProfileActivity.this, StartGameActivity.class));
                return true;
            } else if (itemId == R.id.navigation_profile) {
                // Tetap di halaman ini
                return true;
            } else {
                return false;
            }
        });
    }

    private void setupLogoutButton() {
        // Listener untuk tombol logout
        binding.buttonPanel.setOnClickListener(v -> {
            // Arahkan ke LoginActivity
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(intent);
            // Tutup aktivitas saat ini
            finish();
        });
    }
}
