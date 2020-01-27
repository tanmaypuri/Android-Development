package com.example.carpool.carpool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RideActivity extends AppCompatActivity {

    private Button giveRide;
    private Button getRide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride);

        giveRide=(Button) findViewById(R.id.bGiveRide);
        getRide=(Button) findViewById(R.id.bGetRide);

        giveRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent giveRideIntent = new Intent(RideActivity.this, DriverDetailsActivity.class);
                RideActivity.this.startActivity(giveRideIntent);
            }
        });

        getRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getRideIntent = new Intent(RideActivity.this, FindRideActivity.class);
                RideActivity.this.startActivity(getRideIntent);
            }
        });
    }
}
