package com.example.carpool.carpool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CarDetailsActivity extends AppCompatActivity {

    private EditText cName, NoOfSeats, rcNo, pricePerSeat, email;
    private TextView carDetails;
    private Button btnNext;
    String carName, carSeats, carRC, carSeatPrice,emailAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        cName = (EditText)findViewById(R.id.etCarName);
        NoOfSeats = (EditText)findViewById(R.id.etNumberOfSeats);
        rcNo = (EditText)findViewById(R.id.etRcNo);
        pricePerSeat = (EditText)findViewById(R.id.etPrice);
        email = (EditText)findViewById(R.id.etEmailAddress);
        btnNext = (Button) findViewById(R.id.btCarNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 CarDetails();
            }
        });
    }

    public void CarDetails() {
        initialize();
        if (!validate()) {
            Toast.makeText(this, "Car Details Registration Failed!", Toast.LENGTH_SHORT).show();
        } else {
            onSuccess();

        }
    }

    public void onSuccess() {
        Toast.makeText(this, "Car Details Registered Successfully!", Toast.LENGTH_SHORT).show();
        carName = carSeats = carRC = carSeatPrice = emailAdd = "";
        Intent carIntent = new Intent(CarDetailsActivity.this, LocationDetailsActivity.class);
        CarDetailsActivity.this.startActivity(carIntent);
    }

    public boolean validate() {
        boolean val = true;

        if(carName.isEmpty()){
            cName.setError("Car Name Can't be Blank");
            cName.requestFocus();
            val=false;
        }
        if(carSeats.isEmpty()){
            NoOfSeats.setError("No. of Seats Can't be Blank");
            NoOfSeats.requestFocus();
            val=false;
        }


        if(carRC.isEmpty()){
            rcNo.setError("RC Number Can't be Blank");
            rcNo.requestFocus();
            val=false;
        }

        if(carSeatPrice.isEmpty()){
            pricePerSeat.setError("Price per Seat Can't be Blank");
            pricePerSeat.requestFocus();
            val=false;
        }

        if(emailAdd.isEmpty()){
            email.setError("Email Address Can't be Blank");
            email.requestFocus();
            val=false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailAdd).matches()){
            email.setError("Please Enter Valid Email Address");
            email.requestFocus();
            val=false;
        }

        return val;

    }

    public void initialize() {
        carName = cName.getText().toString().trim();
        carSeats= NoOfSeats.getText().toString().trim();
        carRC= rcNo.getText().toString().trim();
        carSeatPrice = pricePerSeat.getText().toString().trim();
        emailAdd = email.getText().toString().trim();
    }
}
