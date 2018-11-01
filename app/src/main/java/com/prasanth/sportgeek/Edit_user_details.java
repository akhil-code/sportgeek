package com.prasanth.sportgeek;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Edit_user_details extends AppCompatActivity {

    public static final String PREFS_FILE_NAME = "myfile";

    public static final String USERNAME = "username";
    public static final String PHONE = "phone";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String AGE = "age";


    TextView e1,e2,e3,e4,e5;
    String temp_username,temp_phone,temp_email,temp_password,temp_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_details);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Your Profile");

        e1 = (TextView)findViewById(R.id.value_username);
        e2 = (TextView)findViewById(R.id.value_phone);
        e3 = (TextView)findViewById(R.id.value_email);
        e4 = (TextView)findViewById(R.id.value_password);
        e5 = (TextView)findViewById(R.id.value_age);

        SharedPreferences sharedPreferences2;
        sharedPreferences2 = getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
         temp_username = sharedPreferences2.getString(USERNAME,"N/A");
         temp_phone = sharedPreferences2.getString(PHONE,"N/A");
         temp_email = sharedPreferences2.getString(EMAIL,"N/A");
         temp_password = sharedPreferences2.getString(PASSWORD,"N/A");
         temp_age = sharedPreferences2.getString(AGE,"N/A");

        e1.setText(temp_username);
        e2.setText(temp_phone);
        e3.setText(temp_email);
        e4.setText(temp_password);
        e5.setText(temp_age);

        Button editbutton = (Button)findViewById(R.id.editbutton_editpage);
        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Edit_user_details.this, com.prasanth.sportgeek.Editpage_user_details.class);


                i.putExtra("username",temp_username);
                i.putExtra("phone",temp_phone);
                i.putExtra("email",temp_email);
                i.putExtra("password",temp_password);
                i.putExtra("age",temp_age);

                startActivity(i);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
