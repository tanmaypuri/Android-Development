package com.example.carride.carride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DriverDetails extends AppCompatActivity {

    private EditText name;
    private EditText contactno;
    private EditText email;
    private EditText licencenumber,gender1;
    private TextView text;
    private Button btn_Next;
    private static String URL="http://192.168.43.244/insert3.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_details);

        name = (EditText) findViewById(R.id.name);
        contactno = (EditText) findViewById(R.id.contactno);
        email = (EditText) findViewById(R.id.email);
        licencenumber = (EditText) findViewById(R.id.licence);
        text = (TextView) findViewById(R.id.text);
        gender1 = (EditText) findViewById(R.id.gender1);
        btn_Next = (Button) findViewById(R.id.btn_Next);

        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ename = name.getText().toString().trim();
                final String econtact = contactno.getText().toString().trim();
                final String eemail = email.getText().toString().trim();
                final String elicencenumber = licencenumber.getText().toString().trim();
                final String egender1 = gender1.getText().toString().trim();
                if (ename.isEmpty()) {
                    name.setError("Name Can't be Blank");
                    /*email.setError("Email Id Can't be Blank");
                    contactno.setError("Contact No. Can't be Blank");
                    licencenumber.setError("Driver Licence Can't be Blank");
                    gender1.setError("Driver Gender Can't be Blank");*/
                }
                else if (econtact.isEmpty()) {
                    contactno.setError("Contact No. Can't be Blank");
                    contactno.requestFocus();
                }
                else if (econtact.length() < 10 || econtact.length() > 10) {
                    contactno.setError("Invalid Contact No.");
                    //contactno.requestFocus();
                }
                else if (eemail.isEmpty()) {
                    email.setError("Email Id Can't be Blank");
                    email.requestFocus();
                }

                else if (elicencenumber.isEmpty()) {
                    licencenumber.setError("Driver Licence Can't be Blank");
                    //licencenumber.requestFocus();
                }

             /*  else if (elicencenumber.length() < 16 || elicencenumber.length() > 16) {
                    licencenumber.setError("Licence number should be of 16 characters");
                    //licencenumber.requestFocus();
                }

                else if (!Patterns.EMAIL_ADDRESS.matcher(eemail).matches()) {
                    email.setError("Please Enter Valid Email Address");
                    //email.requestFocus();
                }*/
                else if (egender1.isEmpty()) {
                    gender1.setError("Driver Gender Can't be Blank");
                    //gender1.requestFocus();
                }
                else {
                    onSuccess(ename, econtact, egender1, eemail, elicencenumber);
                    }
            }
        });
    }
    public void onSuccess(final String name,final String contactno,final String gender1,final String email,final String licencenumber) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            final JSONObject jsonObject = new JSONObject(response); {
                                String success = jsonObject.getString("success");

                                if(success.equals("1")) {
                                    //Toast.makeText(DriverDetails.this, "Register Success", Toast.LENGTH_SHORT).show();
                                    Intent driverIntent = new Intent(DriverDetails.this, CarDeatailsActivity.class);
                                    DriverDetails.this.startActivity(driverIntent);
                                }
                                else{
                                    btn_Next.setVisibility(View.VISIBLE);
                                    Toast.makeText(DriverDetails.this, "Register Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(DriverDetails.this, " Error" , Toast.LENGTH_SHORT).show();
                            btn_Next.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DriverDetails.this, "Error" , Toast.LENGTH_SHORT).show();
                        btn_Next.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params =new HashMap<>();
                params.put("name",name);
                params.put("contactno",contactno);
                params.put("email",email);
                params.put("licencenumber",licencenumber);
                params.put("gender1",gender1);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}

