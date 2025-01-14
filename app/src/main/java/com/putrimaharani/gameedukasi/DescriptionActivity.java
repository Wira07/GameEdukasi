package com.putrimaharani.gameedukasi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.putrimaharani.gameedukasi.data.Wisata;
import com.putrimaharani.gameedukasi.databinding.ActivityDescriptionBinding;

public class DescriptionActivity extends AppCompatActivity {

    private ActivityDescriptionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize View Binding
        binding = ActivityDescriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Apply Edge-to-Edge functionality
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve Wisata object passed from MainActivity
        Wisata wisata = getIntent().getParcelableExtra("wisata");

        // Display Wisata data
        if (wisata != null) {
            binding.textViewNama.setText(wisata.getNama()); // Set Nama
            binding.textViewDeskripsi.setText(wisata.getDeskripsi()); // Set Deskripsi
            binding.imageViewGambar.setImageResource(wisata.getGambar()); // Set Gambar
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null; // Avoid memory leaks
    }
}
