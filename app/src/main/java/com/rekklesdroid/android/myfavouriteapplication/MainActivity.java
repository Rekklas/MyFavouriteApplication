package com.rekklesdroid.android.myfavouriteapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;
    TextView text5;
    int d =0;
    AnimationSet animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.txt_word_you);
        text2 = findViewById(R.id.txt_adverb_quantity);
        text3 = findViewById(R.id.txt_adverb_quality);
        text4 = findViewById(R.id.txt_verb);
        text5 = findViewById(R.id.txt_noun);

        ImageButton refreshBtn = findViewById(R.id.imgbtn_refresh);
        ImageButton sendBtn = findViewById(R.id.imgbtn_send);


        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1.setText("Text changed");
                text3.setText("Text changed");
            }
        });

        animation = (AnimationSet) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.textview_anim);

        for (Animation anim : animation.getAnimations())
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d(TAG, "onAnimationEnd: text changed " + d);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                text2.setText("Text changed " + d++);
                text3.setText("Text changed " + d++);
                text4.setText("Text changed " + d++);
                text5.setText("Text changed " + d++);
                Log.d(TAG, "onAnimationRepeat: text changed " + d);
            }
        });

        text2.startAnimation(animation);
        text3.startAnimation(animation);
        text4.startAnimation(animation);
        text5.startAnimation(animation);

        text4.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                text4.startAnimation(animation);

            }
        });

    }

}
