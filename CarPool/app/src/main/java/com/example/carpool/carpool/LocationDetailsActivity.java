package com.example.carpool.carpool;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class LocationDetailsActivity extends AppCompatActivity {

    String leaving,going;
     EditText leavingFrom;
    EditText goingTo;
     Button nextButton;
    Button selectDate;
    TextView date;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;

    TextView time;
    Button selectTime;
    Context mcontext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details);

        leavingFrom = (EditText) findViewById(R.id.etLeavingFrom);
        goingTo = (EditText) findViewById(R.id.etGoingTo);
        nextButton = (Button) findViewById(R.id.bLocationNext);

        selectDate = findViewById(R.id.btnDate);
        date = findViewById(R.id.tvSelectedDate);

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(LocationDetailsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        time = (TextView) findViewById(R.id.tvSelectedTime);
        calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int min = calendar.get(Calendar.MINUTE);

        selectTime = (Button) findViewById(R.id.btnTime);
        selectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(mcontext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        time.setText(hourOfDay+":"+minute);
                    }
                },hour,min,android.text.format.DateFormat.is24HourFormat(mcontext));
                timePickerDialog.show();
            }
        });




        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Location();

            }
        });

    }
    public void Location() {
        initialize();
        if (!validate()) {
            Toast.makeText(this, "Location Details Registration Failed!", Toast.LENGTH_SHORT).show();
        } else {
            onRegisterSuccess();
        }
    }

    public void onRegisterSuccess() {
        Toast.makeText(this, "Location Details Registration Successful!", Toast.LENGTH_SHORT).show();
        leaving = going = "";
        Intent locIntent = new Intent(LocationDetailsActivity.this, DetailsRegisteredActivity.class);
        LocationDetailsActivity.this.startActivity(locIntent);
    }

    public boolean validate() {
        boolean val = true;

        if(leaving.isEmpty()){
            leavingFrom.setError("Leaving from Can't be Blank");
            leavingFrom.requestFocus();
            val=false;
        }
        if(going.isEmpty()){
            goingTo.setError("Going to Can't be Blank");
            goingTo.requestFocus();
            val=false;
        }

        return val;

    }

    public void initialize() {
        leaving = leavingFrom.getText().toString().trim();
        going = goingTo.getText().toString().trim();

    }
}
