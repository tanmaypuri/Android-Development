package com.example.sharedpref.sharedpref;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button saveButton;
    private TextView result;
    private EditText enterMessage;
    private SharedPreferences myPrefs;
    private static final String PREF_NAME="myPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result=(TextView) findViewById(R.id.resulttextView);
        enterMessage=(EditText)findViewById(R.id.enterName);
        saveButton=(Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPrefs=getSharedPreferences(PREF_NAME,0);
                SharedPreferences.Editor editor= myPrefs.edit();

                editor.putString("message",enterMessage.getText().toString());
            }
        });
        //get back data
        SharedPreferences prefs= getSharedPreferences(PREF_NAME, 0);
        if(prefs.contains("message")){
            String message= prefs.getString("message","not found");
            result.setText("Message: "+message);
        }
    }
}
