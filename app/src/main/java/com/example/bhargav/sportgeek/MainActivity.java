package com.example.bhargav.sportgeek;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    Button btn;
    EditText e1, e2, e3, e4, e5;
    data_base db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(this);

        e1 = (EditText) findViewById(R.id.editText1);
        e2 = (EditText) findViewById(R.id.editText2);
        e3 = (EditText) findViewById(R.id.editText3);
        e4 = (EditText) findViewById(R.id.editText4);
        e5 = (EditText) findViewById(R.id.editText5);


        db = new data_base(this);
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
    @Override
    public void onClick(View v) {
        int a = 0, b = 0, c = 0, d = 0, e = 0;
        if (e1.getText().toString().length() == 0)
        {


            Toast.makeText(getApplicationContext(), "please provide  name", Toast.LENGTH_LONG).show();

        }
        else if(e1.getText().toString().length()<=2)
            Toast.makeText(getApplicationContext(), "Name should be more than 2 characters", Toast.LENGTH_LONG).show();

        else
            a = 1;
        if (e2.getText().toString().length() == 0)
        {

            Toast.makeText(getApplicationContext(), "please provide  phone number", Toast.LENGTH_LONG).show();

        }
       else if(e2.getText().toString().length()!=10)
            Toast.makeText(getApplicationContext(), "Phone number not valid", Toast.LENGTH_LONG).show();

        else
            b = 1;
        if (e3.getText().toString().length() == 0) {

            Toast.makeText(getApplicationContext(), "please provide  email", Toast.LENGTH_LONG).show();
        }


       else if(!isEmailValid(e3.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "invalid email", Toast.LENGTH_LONG).show();
        }
        else
            c = 1;
        if (e4.getText().toString().length() == 0) {

            Toast.makeText(getApplicationContext(), "please provide  password ", Toast.LENGTH_LONG).show();

        }
        else if (e4.getText().toString().length() < 8) {

            Toast.makeText(getApplicationContext(), "password should be more than 8 characters ", Toast.LENGTH_LONG).show();

        }




        else
            d = 1;
        //int i=Integer.parseInt(e5.getText().toString());
        if (e5.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "please provide  age", Toast.LENGTH_LONG).show();
        }
        else if (e5.getText().toString().length()>3 || e5.getText().toString().length() <2) {

            Toast.makeText(getApplicationContext(), "Enter valid age", Toast.LENGTH_LONG).show();
        }

        else
            e = 1;


        if (a == 1 && b == 1 && c == 1 && d == 1 && e == 1) {
            Toast.makeText(getApplicationContext(), "Successfully submitted", Toast.LENGTH_LONG).show();

            db.add_user(e1.getText().toString(), e2.getText().toString(), e3.getText().toString(), e4.getText().toString(),e5.getText().toString());
            startActivity(new Intent(MainActivity.this, homepage.class));
        }
    }

}