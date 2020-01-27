package com.example.hp.androidregisterandlogin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import org.w3c.dom.Text;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private EditText name,email,password,c_password;
    private Button btn_regist;
    private ProgressBar loading;
    private static String URL="http://192.168.100.8/insert1.php";
    private EditText contactno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        loading = (ProgressBar) findViewById(R.id.loading);
        name = (EditText) findViewById(R.id.name);
        contactno = (EditText) findViewById(R.id.contactno);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        c_password = (EditText) findViewById(R.id.c_password);
        btn_regist = (Button) findViewById(R.id.btn_regist);

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();
            }
        });
    }
     private void Regist(){
        loading.setVisibility(View.VISIBLE);
        btn_regist.setVisibility(View.GONE);

         final String name = this.name.getText().toString().trim();
         final String contactno = this.contactno.getText().toString().trim();
         final String email = this.email.getText().toString().trim();
         final String password = this.password.getText().toString().trim();

         StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                 new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {
                     try{
                         final JSONObject jsonObject = new JSONObject(response); {
                         String success = jsonObject.getString("success");

                           if(success.equals("1")) {
                               Toast.makeText(Register.this, "Register Success", Toast.LENGTH_SHORT).show();
                               Intent intent= new Intent(Register.this,LoginActivity.class);
                               startActivity(intent);
                           }
                         }
                     } catch (JSONException e) {
                         e.printStackTrace();
                         Toast.makeText(Register.this, "Register Error" + e.toString(), Toast.LENGTH_SHORT).show();
                         loading.setVisibility(View.GONE);
                         btn_regist.setVisibility(View.VISIBLE);
                     }
                     }
                 },
                 new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError error) {
                         Toast.makeText(Register.this, "Register Error" + error.toString(), Toast.LENGTH_SHORT).show();
                         loading.setVisibility(View.GONE);
                         btn_regist.setVisibility(View.VISIBLE);
                     }
                 })
         {
             @Override
             protected Map<String, String> getParams() throws AuthFailureError {
                 Map<String,String> params =new HashMap<>();
                 params.put("name",name);
                 params.put("contactno",contactno);
                 params.put("email",email);
                 params.put("password",password);
                 return params;
             }
         };
         RequestQueue requestQueue = Volley.newRequestQueue(this);
         requestQueue.add(stringRequest);
     }
}
