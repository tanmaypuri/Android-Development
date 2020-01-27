package com.example.carride.carride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private TextView text,name;
    private Button btn_search,btn_give;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        text = findViewById(R.id.text);
        name = findViewById(R.id.name);
        btn_search = findViewById(R.id.btn_search);
        btn_give = findViewById(R.id.btn_give);

        btn_give.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent giveRideIntent = new Intent(HomeActivity.this,DriverDetails.class);
                HomeActivity.this.startActivity(giveRideIntent);
            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getRideIntent = new Intent(HomeActivity.this, DriverListActivity.class);
                HomeActivity.this.startActivity(getRideIntent);
            }
        });
    }
}
