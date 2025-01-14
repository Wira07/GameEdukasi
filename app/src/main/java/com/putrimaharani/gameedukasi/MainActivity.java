package com.putrimaharani.gameedukasi;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.putrimaharani.gameedukasi.adapter.WisataAdapter;
import com.putrimaharani.gameedukasi.data.Wisata;
import com.putrimaharani.gameedukasi.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements WisataAdapter.OnItemClickListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize View Binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set up RecyclerView
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create list of wisata
        List<Wisata> wisataList = new ArrayList<>();
        wisataList.add(new Wisata("Curug Sidomba", "Air terjun dengan pemandangan alam yang asri.", R.drawable.arunika_view));
        wisataList.add(new Wisata("Telaga Remis", "Danau dengan air jernih di kaki Gunung Ciremai.", R.drawable.arunika_view));
        wisataList.add(new Wisata("Pemandian Cibulan", "Kolam renang alami dengan ikan keramat.", R.drawable.arunika_view));
        wisataList.add(new Wisata("Wisata Bukit Panembongan", "Tempat selfie dengan pemandangan alam yang indah.", R.drawable.arunika_view));
        wisataList.add(new Wisata("Curug Putri", "Air terjun di kaki Gunung Ciremai.", R.drawable.arunika_view));

        // Set up adapter
        WisataAdapter adapter = new WisataAdapter(this, wisataList, this);
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null; // Avoid memory leaks
    }

    @Override
    public void onItemClick(Wisata wisata) {
        // Start DescriptionActivity and pass the clicked Wisata object
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra("wisata_title", wisata.getTitle());
        intent.putExtra("wisata_description", wisata.getDescription());
        intent.putExtra("wisata_image", wisata.getImageResId());
        startActivity(intent);
    }
}
