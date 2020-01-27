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

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    TextView signup;
    EditText name, contact, email, password, cpassword;
    Button sign_up;
    String fullname, contactNo, emailId, pass, conPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signup = (TextView) findViewById(R.id.tvSignUp);
        name = (EditText) findViewById(R.id.etName);
        contact = (EditText) findViewById(R.id.etContact);
        email = (EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPassword);
        cpassword = (EditText) findViewById(R.id.etConfirmPassword);
        sign_up = (Button) findViewById(R.id.bSignUp);


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

    }

    public void register() {
        initialize();
        if (!validate()) {
            Toast.makeText(this, "User Registration Failed!", Toast.LENGTH_SHORT).show();
        } else {
            onRegisterSuccess();
        }
    }

    public void onRegisterSuccess() {
        Toast.makeText(this, "User Registration Successful!", Toast.LENGTH_SHORT).show();
        fullname = contactNo = emailId = pass = conPass = "";
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        RegisterActivity.this.startActivity(intent);
    }

    public boolean validate() {
        boolean val = true;

        if(fullname.isEmpty()){
            name.setError("Full Name Can't be Blank");
            name.requestFocus();
            val=false;
        }
        if(contactNo.isEmpty()){
            contact.setError("Contact No. Can't be Blank");
            contact.requestFocus();
            val=false;
        }
        if(contactNo.length()<10 || contactNo.length()>10) {
            contact.setError("Invalid Contact No.");
            contact.requestFocus();
            val = false;
        }
        if(emailId.isEmpty()){
            email.setError("Email Id Can't be Blank");
            email.requestFocus();
            val=false;
        }
        if(pass.isEmpty()){
            password.setError("Password Can't be Blank");
            password.requestFocus();
            val=false;
        }
        if(conPass.isEmpty()){
            cpassword.setError("Confirm Password Can't be Blank");
            cpassword.requestFocus();
            val=false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailId).matches()){
            email.setError("Please Enter Valid Email Address");
            email.requestFocus();
            val=false;
        }

        if(pass.length()<8){
            password.setError("Password Can't be less than 8 Characters");
            password.requestFocus();
            val= false;
        }

        if(conPass.length()<8 || !pass.equals(conPass)){
            cpassword.setError("Password Doesn't Matches");
            cpassword.requestFocus();
            val= false;
        }

        return val;

    }

    public void initialize() {
        fullname = name.getText().toString().trim();
        contactNo = contact.getText().toString().trim();
        emailId = email.getText().toString().trim();
        pass = password.getText().toString().trim();
        conPass = cpassword.getText().toString().trim();
    }
}