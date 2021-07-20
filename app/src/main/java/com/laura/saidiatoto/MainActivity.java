package com.laura.saidiatoto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 2000;
    //Variable for animations
    Animation top;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Call animation
        top = AnimationUtils.loadAnimation(this ,R.anim.top_animation);

        image = findViewById(R.id.imageView6);

        image.setAnimation(top);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent tologin = new Intent(MainActivity.this,Login.class);
                startActivity(tologin);
                finish();
            }
        },SPLASH_SCREEN);
    }
}