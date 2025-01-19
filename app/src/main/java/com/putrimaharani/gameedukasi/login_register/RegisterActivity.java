package com.putrimaharani.gameedukasi.login_register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.putrimaharani.gameedukasi.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private static final String PREFS_NAME = "GameEdukasiPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableEdgeToEdge();

        // Menggunakan ViewBinding
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Menyesuaikan padding untuk sistem bar
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Event listener untuk tombol submit
        binding.submitButton.setOnClickListener(v -> {
            // Simpan username (contoh)
            String username = binding.emailInput.getText().toString().trim();

            if (!username.isEmpty()) {
                SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username", username);
                editor.putBoolean("isLoggedIn", false); // Awalnya belum login
                editor.apply();

                // Arahkan ke LoginActivity
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            } else {
                binding.emailInput.setError("Username cannot be empty");
            }
        });
    }

    private void enableEdgeToEdge() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );
        getWindow().setStatusBarColor(android.graphics.Color.TRANSPARENT);
    }
}
