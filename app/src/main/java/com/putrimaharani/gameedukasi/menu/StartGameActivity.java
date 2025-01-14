package com.putrimaharani.gameedukasi.menu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.putrimaharani.gameedukasi.R;
import com.putrimaharani.gameedukasi.databinding.ActivityStartGameBinding;
import java.util.Random;

public class StartGameActivity extends AppCompatActivity {
    private int presCounter = 0;
    private final int maxPresCounter = 4;
    private String[] keys = {"R", "I", "B", "D", "X"};
    private final String textAnswer = "BIRD";
    private Animation smallbigforth;
    private ActivityStartGameBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize View Binding
        binding = ActivityStartGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Load animation
        smallbigforth = AnimationUtils.loadAnimation(this, R.anim.smallbigforth);

        // Shuffle keys and initialize the game
        keys = shuffleArray(keys);
        initializeGame();
    }

    private void initializeGame() {
        binding.layoutParent.removeAllViews();
        for (String key : keys) {
            addKeyView(key);
        }
        // Set custom fonts
//        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FredokaOneRegular.ttf");
//        binding.textQuestion.setTypeface(typeface);
//        binding.textScreen.setTypeface(typeface);
//        binding.textTitle.setTypeface(typeface);
//        binding.editText.setTypeface(typeface);
    }

    private String[] shuffleArray(String[] array) {
        Random rnd = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            String temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        return array;
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private void addKeyView(final String key) {
        // Create TextView dynamically for each key
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.rightMargin = 30;

        final TextView textView = new TextView(this);
        textView.setLayoutParams(params);
        textView.setBackground(getResources().getDrawable(R.drawable.background));
        textView.setTextColor(getResources().getColor(R.color.colorPurple));
        textView.setGravity(Gravity.CENTER);
        textView.setText(key);
        textView.setClickable(true);
        textView.setFocusable(true);
        textView.setTextSize(32);
        textView.setTypeface(binding.textQuestion.getTypeface());

        textView.setOnClickListener(v -> {
            if (presCounter < maxPresCounter) {
                if (presCounter == 0) binding.editText.setText("");
                binding.editText.append(key);
                textView.startAnimation(smallbigforth);
                textView.animate().alpha(0).setDuration(300);
                presCounter++;

                if (presCounter == maxPresCounter) validateAnswer();
            }
        });

        binding.layoutParent.addView(textView);
    }

    private void validateAnswer() {
        presCounter = 0;

        String userAnswer = binding.editText.getText().toString();
        if (userAnswer.equals(textAnswer)) {
            // Correct answer
            Intent intent = new Intent(StartGameActivity.this, BossAct.class);
            startActivity(intent);
            binding.editText.setText("");
        } else {
            // Wrong answer
            Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
            binding.editText.setText("");
        }

        // Reset keys and UI
        keys = shuffleArray(keys);
        initializeGame();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null; // Avoid memory leaks
    }
}
