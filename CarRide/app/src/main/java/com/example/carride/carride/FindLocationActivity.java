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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FindLocationActivity extends AppCompatActivity {

    private EditText leavingto;
    private EditText goingto;
    private EditText Time;
    private EditText Date;
    private TextView text;
    private Button btn_Search;
    private static String URL="http://192.168.43.244/insert6.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_location);

        leavingto = (EditText) findViewById(R.id.leavingto);
        goingto = (EditText) findViewById(R.id.goingto);
        Time = (EditText) findViewById(R.id.Time);
        Date = (EditText) findViewById(R.id.Date);
        text = (TextView) findViewById(R.id.text);
        btn_Search = (Button) findViewById(R.id.btn_Search);

        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String eleavingto = leavingto.getText().toString().trim();
                final String egoingto = goingto.getText().toString().trim();
                final String eTime = Time.getText().toString().trim();
                final String eDate = Date.getText().toString().trim();
                if (eleavingto.isEmpty()) {
                    leavingto.setError("Leaving From Can't be Blank");
                    /*email.setError("Email Id Can't be Blank");
                    contactno.setError("Contact No. Can't be Blank");
                    licencenumber.setError("Driver Licence Can't be Blank");
                    gender1.setError("Driver Gender Can't be Blank");*/
                }
                else if (egoingto.isEmpty()) {
                    goingto.setError("Going To Can't be Blank");
                    //contactno.requestFocus();
                }
                /*else if (econtact.length() < 10 || econtact.length() > 10) {
                    contactno.setError("Invalid Contact No.");
                    //contactno.requestFocus();
                }*/
                /*else if (eTime.isEmpty()) {
                    Time.setError("Time Can't be Blank");
                    //email.requestFocus();
                }*/

                else if (eDate.isEmpty()) {
                    Date.setError("Date Can't be Blank");
                    //licencenumber.requestFocus();
                }

                /*else if (elicencenumber.length() < 16 || elicencenumber.length() > 16) {
                    licencenumber.setError("Licence number should be of 16 characters");
                    //licencenumber.requestFocus();
                }

                else if (!Patterns.EMAIL_ADDRESS.matcher(eemail).matches()) {
                    email.setError("Please Enter Valid Email Address");
                    //email.requestFocus();
                }
                else if (egender1.isEmpty()) {
                    gender1.setError("Driver Gender Can't be Blank");
                    //gender1.requestFocus();
                }*/
                else {
                    onSuccess(eleavingto, egoingto, eTime, eDate);
                }
            }
        });
    }
    public void onSuccess(final String leavingto,final String goingto,final String Time,final String Date) {
        btn_Search.setVisibility(View.GONE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");
                            if(success.equals("1")) {
                                for(int i=0; i<jsonArray.length();i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String id = object.getString("id").trim();
                                    //String email = object.getString("email").trim();
                                    Toast.makeText(FindLocationActivity.this,"Successfull Search.\n Your Id :"+id,Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(FindLocationActivity.this,DriverListActivity.class);
                                    intent.putExtra("id",id);
                                    //intent.putExtra("email",email);
                                    startActivity(intent);
                                }
                            }
                            else{

                                btn_Search.setVisibility(View.VISIBLE);
                                Toast.makeText(FindLocationActivity.this,"No Relevant Output" ,Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            btn_Search.setVisibility(View.VISIBLE);
                            Toast.makeText(FindLocationActivity.this,"Error" +e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        btn_Search.setVisibility(View.VISIBLE);
                        Toast.makeText(FindLocationActivity.this,"Error" +error.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("leavingto", leavingto);
                params.put("goingto", goingto);
                params.put("Time", Time);
                params.put("Date", Date);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
