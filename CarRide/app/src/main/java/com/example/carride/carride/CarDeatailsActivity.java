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

public class CarDeatailsActivity extends AppCompatActivity {
    private EditText name;
    private EditText noofseat;
    private EditText rcnumber;
    private EditText price;
    private TextView text;
    private Button btnNext;
    private static String URL="http://192.168.43.244/insert4.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_deatails);

        name = (EditText) findViewById(R.id.name);
        noofseat = (EditText) findViewById(R.id.noofseat);
        rcnumber = (EditText) findViewById(R.id.rcnumber);
        price = (EditText) findViewById(R.id.price);
        text = (TextView) findViewById(R.id.text);
        btnNext = (Button) findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ename = name.getText().toString().trim();
                final String enoofseat = noofseat.getText().toString().trim();
                final String ercnumber = rcnumber.getText().toString().trim();
                final String eprice = price.getText().toString().trim();
                if (ename.isEmpty()) {
                    name.setError("Name Can't be Blank");
                    /*email.setError("Email Id Can't be Blank");
                    contactno.setError("Contact No. Can't be Blank");
                    licencenumber.setError("Driver Licence Can't be Blank");
                    gender1.setError("Driver Gender Can't be Blank");*/
                }
                else if (enoofseat.isEmpty()) {
                    noofseat.setError("No Of Seats Can't be Blank");
                    noofseat.requestFocus();
                }
               /* else if (econtact.length() < 10 || econtact.length() > 10) {
                    contactno.setError("Invalid Contact No.");
                    //contactno.requestFocus();
                }*/
                else if (ercnumber.isEmpty()) {
                    rcnumber.setError("RC Number Can't be Blank");
                    rcnumber.requestFocus();
                }

                else if (eprice.isEmpty()) {
                    price.setError("Driver Licence Can't be Blank");
                    //licencenumber.requestFocus();
                }
                else if (eprice.equals("0"))
                {
                    price.setError("Price Should Not be zero");
                }

                else if (ercnumber.length() != 10) {
                    rcnumber.setError("RC Number should be of 10 characters");
                    //licencenumber.requestFocus();
                }

                /*else if (!Patterns.EMAIL_ADDRESS.matcher(eemail).matches()) {
                    email.setError("Please Enter Valid Email Address");
                    //email.requestFocus();
                }*/
                else {
                    onSuccess(ename, enoofseat, ercnumber, eprice);
                }
            }
        });
    }
    public void onSuccess(final String name,final String noofseat,final String rcnumber,final String price) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            final JSONObject jsonObject = new JSONObject(response); {
                                String success = jsonObject.getString("success");

                                if(success.equals("1")) {
                                    Toast.makeText(CarDeatailsActivity.this, "Data Register Success", Toast.LENGTH_SHORT).show();
                                    Intent CarIntent = new Intent(CarDeatailsActivity.this,HomeActivity.class);
                                    CarDeatailsActivity.this.startActivity(CarIntent);
                                }else {
                                    btnNext.setVisibility(View.VISIBLE);
                                    Toast.makeText(CarDeatailsActivity.this, "Register Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(CarDeatailsActivity.this, " Error" , Toast.LENGTH_SHORT).show();
                            btnNext.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CarDeatailsActivity.this, "Error" , Toast.LENGTH_SHORT).show();
                        btnNext.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params =new HashMap<>();
                params.put("name",name);
                params.put("noofseat",noofseat);
                params.put("rcnumber",rcnumber);
                params.put("price",price);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
