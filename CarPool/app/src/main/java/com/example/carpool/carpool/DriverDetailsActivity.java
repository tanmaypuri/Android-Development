package com.example.carpool.carpool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class DriverDetailsActivity extends AppCompatActivity {

    private EditText driverName;
    private EditText driverContact;
    private EditText driverEmail;
    private EditText driverLicence;
    private TextView personalDetails;
    private TextView gender;
    private RadioButton male;
    private RadioButton female;
    private RadioButton other;
    private Button nextButton;

    String dName,dContact,dEmail,dLicence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_details);

        driverName = (EditText) findViewById(R.id.etDriverName);
        driverContact = (EditText) findViewById(R.id.etDriverMobile);
        driverEmail = (EditText) findViewById(R.id.etDriverEmail);
        driverLicence = (EditText) findViewById(R.id.etDriverLicence);
        personalDetails = (TextView) findViewById(R.id.tvPersonalDetails);
        personalDetails = (TextView) findViewById(R.id.tvDriverGender);
        male = (RadioButton) findViewById(R.id.rbMale);
        female = (RadioButton) findViewById(R.id.rbFemale);
        other = (RadioButton) findViewById(R.id.rbOther);
        nextButton = (Button) findViewById(R.id.bNext);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DriverDetails();
            }
        });
    }

    public void DriverDetails() {
        initialize();
        if (!validate()) {
            Toast.makeText(this, "User Registration Failed!", Toast.LENGTH_SHORT).show();
        } else {
            onSuccess();

        }
    }

    public void onSuccess() {
        Toast.makeText(this, "Personal Details Registered Successfully!", Toast.LENGTH_SHORT).show();
        dName = dContact = dEmail = dLicence = "";
        Intent driverIntent = new Intent(DriverDetailsActivity.this, CarDetailsActivity.class);
        DriverDetailsActivity.this.startActivity(driverIntent);
    }

    public boolean validate() {
        boolean val = true;

        if(dName.isEmpty()){
            driverName.setError("Driver Name Can't be Blank");
            driverName.requestFocus();
            val=false;
        }
        if(dContact.isEmpty()){
            driverContact.setError("Contact No. Can't be Blank");
            driverContact.requestFocus();
            val=false;
        }
        if(dContact.length()<10 || dContact.length()>10) {
            driverContact.setError("Invalid Contact No.");
            driverContact.requestFocus();
            val = false;
        }
        if(dEmail.isEmpty()){
            driverEmail.setError("Email Id Can't be Blank");
            driverEmail.requestFocus();
            val=false;
        }

        if(dLicence.isEmpty()){
            driverLicence.setError("Driver Licence Can't be Blank");
            driverLicence.requestFocus();
            val=false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(dEmail).matches()){
            driverEmail.setError("Please Enter Valid Email Address");
            driverEmail.requestFocus();
            val=false;
        }

        return val;

    }

    public void initialize() {
        dName = driverName.getText().toString().trim();
        dContact= driverContact.getText().toString().trim();
        dEmail= driverEmail.getText().toString().trim();
        dLicence = driverLicence.getText().toString().trim();

    }
}
