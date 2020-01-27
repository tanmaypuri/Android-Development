package com.example.tryme.tryme;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private View windowView;
    private Button tryMeButton;
    private int[] colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colors=new int[]{Color.CYAN, Color.BLUE, Color.GREEN, Color.BLACK, Color.MAGENTA,
        Color.YELLOW, Color.LTGRAY, Color.RED};

        windowView=findViewById(R.id.windowViewId);

        tryMeButton=(Button) findViewById(R.id.tryMeButton);
        tryMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int colorArrayLength= colors.length;

                Random random= new Random();
                int randomNum = random.nextInt(colorArrayLength);

                windowView.setBackgroundColor(colors[randomNum]);
            }
        });
    }
}
