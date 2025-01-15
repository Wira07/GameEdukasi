package com.putrimaharani.gameedukasi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import androidx.appcompat.app.AppCompatActivity;

import com.putrimaharani.gameedukasi.databinding.ActivitySplashScreenBinding;
import com.putrimaharani.gameedukasi.login_register.LoginActivity;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate layout with View Binding
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set up fade-in animation for the logo
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(1500); // Animation duration in milliseconds
        fadeIn.setFillAfter(true);

        // Apply the animation to the logo
        binding.logo.startAnimation(fadeIn);

        // Navigate to LoginActivity after the animation
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish(); // Finish the splash screen activity
        }, 2000); // Delay in milliseconds
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null; // Avoid memory leaks
    }
}
