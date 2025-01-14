package com.putrimaharani.gameedukasi.menu;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.putrimaharani.gameedukasi.R;
import com.putrimaharani.gameedukasi.databinding.ActivityBossBinding;

public class BossAct extends AppCompatActivity {

    private ActivityBossBinding binding;
    private Animation smalltobig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize View Binding
        binding = ActivityBossBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Apply window insets to the main layout
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Load Animation
        smalltobig = AnimationUtils.loadAnimation(this, R.anim.smalltobig);

        // Apply Animation to Big Boss Image
        binding.bigboss.startAnimation(smalltobig);

        // Set Custom Typeface
//        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FredokaOneRegular.ttf");
//        binding.textScreen.setTypeface(typeface);
//        binding.textQuestion.setTypeface(typeface);
//        binding.textTitle.setTypeface(typeface);
//        binding.textBtn.setTypeface(typeface);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null; // Avoid memory leaks
    }
}
