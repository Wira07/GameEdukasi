package com.putrimaharani.gameedukasi.menu;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.media.MediaPlayer;
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
    private MediaPlayer mediaPlayer;
    // Array gambar dan pertanyaan
    private int[] imageResources = {
            R.drawable.ic_pencil, // Gambar 1
            R.drawable.ic_book,   // Gambar 2
            R.drawable.ic_badak,   // Gambar 3
            R.drawable.ic_dino,   // Gambar 3
            R.drawable.ic_saurus,   // Gambar 3
    };

    private String[] questions = {
            "What activity is shown in this picture?",  // Pertanyaan untuk gambar 1
            "What is used for reading?",               // Pertanyaan untuk gambar 2
            "What is used for riding on the road?",    // Pertanyaan untuk gambar 3
            "What was the Triceratops favorite food?",
            "Where does Dinoo live?",
            "Who is Ptera's best friend?"
    };

    private String[] correctAnswers = {
            "write",  // Jawaban benar untuk gambar 1
            "read",   // Jawaban benar untuk gambar 2
            "bike",    // Jawaban benar untuk gambar 3
            "fruit",
            "forest",
            "Dinoo"
    };

    private int currentIndex = 0; // Indeks gambar dan pertanyaan saat ini

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Memutar musik opening
        mediaPlayer = MediaPlayer.create(this, R.raw.cartoon);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        // Tampilkan pertanyaan pertama
        updateQuestion();
        enableEdgeToEdge();

        // Tombol Submit Jawaban
        binding.submitButton.setOnClickListener(v -> {
            String userAnswer = binding.answerInput.getText().toString().trim();
            checkAnswer(userAnswer);
        });

        // Sistem insets (untuk API 30+ dan versi lebih rendah)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().getDecorView().setOnApplyWindowInsetsListener((view, insets) -> {
                Insets systemBarsInsets = WindowInsetsCompat.toWindowInsetsCompat(insets).getInsets(WindowInsetsCompat.Type.systemBars());
                view.setPadding(systemBarsInsets.left, systemBarsInsets.top, systemBarsInsets.right, systemBarsInsets.bottom);
                return insets;
            });
        } else {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
            );
        }
    }

    // Method untuk mengupdate pertanyaan dan gambar
    private void updateQuestion() {
        binding.questionImage.setImageResource(imageResources[currentIndex]);
        binding.questionText.setText(questions[currentIndex]);
        binding.answerInput.setText("");
    }

    // Method untuk memeriksa jawaban
    private void checkAnswer(String userAnswer) {
        if (userAnswer.equalsIgnoreCase(correctAnswers[currentIndex])) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            animateCorrectAnswer();

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Correct Answer!")
                    .setMessage("Well done! You answered correctly.")
                    .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                    .show();

            goToNextQuestion();
        } else {
            Toast.makeText(this, "Wrong Answer. Try Again!", Toast.LENGTH_SHORT).show();
        }
    }

    // Method untuk pindah ke pertanyaan berikutnya
    private void goToNextQuestion() {
        currentIndex++;
        if (currentIndex >= imageResources.length) {
            currentIndex = 0;
            Toast.makeText(this, "You finished all questions! Restarting.", Toast.LENGTH_LONG).show();
        }
        updateQuestion();
    }

    private void enableEdgeToEdge() {
        Window window = getWindow();
        WindowInsetsControllerCompat insetsController = new WindowInsetsControllerCompat(window, window.getDecorView());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setStatusBarColor(Color.TRANSPARENT);
        } else {
            window.setStatusBarColor(Color.BLACK);
        }

        ViewCompat.setOnApplyWindowInsetsListener(window.getDecorView(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Method untuk menambahkan animasi halus
    private void animateCorrectAnswer() {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(binding.questionImage, "scaleX", 1f, 1.2f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(binding.questionImage, "scaleY", 1f, 1.2f, 1f);
        ObjectAnimator scaleTextX = ObjectAnimator.ofFloat(binding.questionText, "scaleX", 1f, 1.2f, 1f);
        ObjectAnimator scaleTextY = ObjectAnimator.ofFloat(binding.questionText, "scaleY", 1f, 1.2f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX, scaleY, scaleTextX, scaleTextY);
        animatorSet.setDuration(500);
        animatorSet.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
