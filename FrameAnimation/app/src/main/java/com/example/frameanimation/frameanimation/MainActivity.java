package com.example.frameanimation.frameanimation;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private AnimationDrawable batAnimation;
    private ImageView batImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        batImage=(ImageView)findViewById(R.id.batID);
        batImage.setBackgroundResource(R.drawable.bat_anim);
        batAnimation=(AnimationDrawable) batImage.getBackground();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        batAnimation.start();
        return super.onTouchEvent(event);
    }
}
