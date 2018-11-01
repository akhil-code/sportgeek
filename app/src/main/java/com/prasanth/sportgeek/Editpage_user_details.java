package com.prasanth.sportgeek;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Editpage_user_details extends AppCompatActivity {
    public static final String PREFS_FILE_NAME = "myfile";

    public static final String USERNAME = "username";
    public static final String PHONE = "phone";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String AGE = "age";

    EditText e1,e2,e3,e4,e5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editpage_user_details);

        Bundle extras = getIntent().getExtras();


        e1 = (EditText)findViewById(R.id.e1);
        e2 = (EditText)findViewById(R.id.e2);
        e3 = (EditText)findViewById(R.id.e3);
        e4 = (EditText)findViewById(R.id.e4);
        e5 = (EditText)findViewById(R.id.e5);

        e1.setText(extras.getString("username"));
        e2.setText(extras.getString("phone"));
        e3.setText(extras.getString("email"));
        e4.setText(extras.getString("password"));
        e5.setText(extras.getString("age"));


        Button save_button = (Button)findViewById(R.id.save_editpage);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //writing the user details in shared preferences
                SharedPreferences sharedPreferences;
                sharedPreferences = getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(USERNAME,e1.getText().toString());
                editor.putString(PHONE,e2.getText().toString());
                editor.putString(EMAIL,e3.getText().toString());
                editor.putString(PASSWORD,e4.getText().toString());
                editor.putString(AGE,e5.getText().toString());

                editor.commit();
                Toast.makeText(getApplicationContext(),"successfully saved",Toast.LENGTH_LONG).show();
                finish();
                Intent i = new Intent(getApplicationContext(), com.prasanth.sportgeek.homepage.class);
                startActivity(i);

            }
        });



    }
}
