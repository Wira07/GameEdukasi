package com.putrimaharani.gameedukasi.menu;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.putrimaharani.gameedukasi.R;
import com.putrimaharani.gameedukasi.databinding.ActivityQuizBinding;

public class QuizActivity extends AppCompatActivity {

    private ActivityQuizBinding binding;

    // Array gambar dan pertanyaan
    private int[] imageResources = {
            R.drawable.ic_pencil, // Gambar 1
            R.drawable.ic_book,   // Gambar 2
            R.drawable.ic_bike    // Gambar 3
    };

    private String[] questions = {
            "What activity is shown in this picture?",  // Pertanyaan untuk gambar 1
            "What is used for reading?",               // Pertanyaan untuk gambar 2
            "What is used for riding on the road?"      // Pertanyaan untuk gambar 3
    };

    private String[] correctAnswers = {
            "write",  // Jawaban benar untuk gambar 1
            "read",   // Jawaban benar untuk gambar 2
            "bike"    // Jawaban benar untuk gambar 3
    };

    private int currentIndex = 0; // Indeks gambar dan pertanyaan saat ini

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Sistem insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Tampilkan pertanyaan pertama
        updateQuestion();
        enableEdgeToEdge();

        // Tombol Submit Jawaban
        binding.submitButton.setOnClickListener(v -> {
            String userAnswer = binding.answerInput.getText().toString().trim();
            checkAnswer(userAnswer);
        });
    }

    // Method untuk mengupdate pertanyaan dan gambar
    private void updateQuestion() {
        binding.questionImage.setImageResource(imageResources[currentIndex]); // Tampilkan gambar
        binding.questionText.setText(questions[currentIndex]); // Tampilkan pertanyaan
        binding.answerInput.setText(""); // Kosongkan jawaban
    }

    // Method untuk memeriksa jawaban
    // Method untuk memeriksa jawaban
    private void checkAnswer(String userAnswer) {
        if (userAnswer.equalsIgnoreCase(correctAnswers[currentIndex])) {
            // Menampilkan Toast sebagai alert jika jawaban benar
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            // Berikan alert yang memberi tahu jawaban benar
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Correct Answer!")
                    .setMessage("Well done! You answered correctly.")
                    .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                    .show();

            goToNextQuestion(); // Pindah ke pertanyaan berikutnya
        } else {
            Toast.makeText(this, "Wrong Answer. Try Again!", Toast.LENGTH_SHORT).show();
        }
    }


    // Method untuk pindah ke pertanyaan berikutnya
    private void goToNextQuestion() {
        currentIndex++;
        if (currentIndex >= imageResources.length) {
            currentIndex = 0; // Ulangi dari awal jika sudah mencapai akhir
            Toast.makeText(this, "You finished all questions! Restarting.", Toast.LENGTH_LONG).show();
        }
        updateQuestion();
    }

    private void enableEdgeToEdge() {
        // Membuat status bar menjadi transparan tetapi tetap mempertahankan tata letak yang standar
        Window window = getWindow();
        WindowInsetsControllerCompat insetsController = new WindowInsetsControllerCompat(window, window.getDecorView());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Menyesuaikan warna status bar pada Android 11 dan lebih tinggi
            window.setStatusBarColor(Color.TRANSPARENT);
//            insetsController.setSystemBarsBehavior(
//                    WindowInsetsControllerCompat.APPEARANCE_LIGHT_STATUS_BARS,
//                    WindowInsetsControllerCompat.APPEARANCE_LIGHT_STATUS_BARS
//            );
        } else {
            // Untuk versi Android sebelum 11 (API level < 30), kita dapat menggunakan cara lain untuk mengubah status bar color
            window.setStatusBarColor(Color.BLACK); // Atau sesuaikan dengan warna lain
        }

        // Menjaga tata letak tidak sepenuhnya fullscreen
        ViewCompat.setOnApplyWindowInsetsListener(window.getDecorView(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


}
