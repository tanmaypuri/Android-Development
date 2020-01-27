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

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    EditText username, login_password;
    TextView register_link;
    Button blogin;
    String contactNo, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.etUsername);
        login_password = (EditText) findViewById(R.id.etLoginPassword);
        register_link = (TextView) findViewById(R.id.tvSignUpHere);
        blogin = (Button) findViewById(R.id.bLogin);

        register_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();

            }
        });
    }

    public void login() {
        initialize();
        if (!validate()) {
            Toast.makeText(this, "User Login Failed!", Toast.LENGTH_SHORT).show();
        } else {
            onLoginSuccess();
        }
    }

    public void onLoginSuccess() {
        Toast.makeText(this, "User Login Successful!", Toast.LENGTH_SHORT).show();
        contactNo = pass = "";
        Intent loginIntent = new Intent(LoginActivity.this, RideActivity.class);
        LoginActivity.this.startActivity(loginIntent);
    }

    public boolean validate() {
        boolean value = true;

        if (contactNo.isEmpty()) {
            username.setError("Contact No. Can't be Blank");
            username.requestFocus();
            value = false;
        }
        if (contactNo.length() < 10 || contactNo.length() > 10) {
            username.setError("Invalid Contact No.");
            username.requestFocus();
            value = false;
        }

        if (pass.isEmpty()) {
            login_password.setError("Password Can't be Blank");
            login_password.requestFocus();
            value = false;
        }

        if (pass.length() < 8) {
            login_password.setError("Password Can't be less than 8 Characters");
            login_password.requestFocus();
            value = false;
        }

        return value;

}

    public void initialize() {

        contactNo = username.getText().toString().trim();
        pass = login_password.getText().toString().trim();

    }
}