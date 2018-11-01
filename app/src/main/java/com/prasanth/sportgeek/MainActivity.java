

package com.prasanth.sportgeek;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button btn;
    EditText e1, e2, e3, e4, e5;
    data_base db;

    public static final String REGISTER_FLAG_NAME = "register_flag";
    public static final String BACK_PRESSED_FLAG_NAME = "back_pressed";
    public static final String LOGGED_OUT_FLAG = "loggedout";
    public static final String PREFS_FILE_NAME = "myfile";

    public static final String USERNAME = "username";
    public static final String PHONE = "phone";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String AGE = "age";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Register");

        SharedPreferences sharedPreferences1;
        sharedPreferences1 = getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences1.edit();
        editor.putString(BACK_PRESSED_FLAG_NAME,"0");
        editor.commit();

        SharedPreferences sharedPreferences2;
        sharedPreferences2 = getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        String temp_registered = sharedPreferences2.getString(REGISTER_FLAG_NAME,"0");
        String back_pressed = sharedPreferences2.getString(BACK_PRESSED_FLAG_NAME,"0");
        String temp_logged_out = sharedPreferences2.getString(LOGGED_OUT_FLAG,"0");
        if(temp_registered.contentEquals("1")&&temp_logged_out.contentEquals("0")){
            startActivity(new Intent(MainActivity.this, homepage.class));
        }


        if(back_pressed.contentEquals("1")){
            finish();
        }
        if(temp_logged_out.contentEquals("1")&&temp_registered.contentEquals("1")){
            startActivity(new Intent(MainActivity.this,login.class));
        }

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
            //Toast.makeText(getApplicationContext(), "Successfully submitted", Toast.LENGTH_LONG).show();

            //writing the user details in online server
            BackgroundTask backgroundService = new BackgroundTask();
            backgroundService.execute(e1.getText().toString(), e2.getText().toString(), e3.getText().toString(), e4.getText().toString(),e5.getText().toString());

            //writing the user details in shared preferences
            SharedPreferences sharedPreferences;
            sharedPreferences = getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(REGISTER_FLAG_NAME,"1");
            editor.putString(USERNAME,e1.getText().toString());
            editor.putString(PHONE,e2.getText().toString());
            editor.putString(EMAIL,e3.getText().toString());
            editor.putString(PASSWORD,e4.getText().toString());
            editor.putString(AGE,e5.getText().toString());

            editor.commit();

            db.add_user(e1.getText().toString(), e2.getText().toString(), e3.getText().toString(), e4.getText().toString(),e5.getText().toString());
            startActivity(new Intent(MainActivity.this, homepage.class));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    public void back_to_login(View v){
        Intent i = new Intent(MainActivity.this,login.class);
        startActivity(i);
    }


    class BackgroundTask extends AsyncTask<String,Void,String> {

        String addinfo_url;

        @Override
        protected void onPreExecute() {

            addinfo_url="http://akhil.freevar.com/phpx/add_info.php";
        }

        @Override
        protected String doInBackground(String... args) {

            String name = args[0];
            String phone = args[1];
            String email = args[2];
            String password = args[3];
            String age = args[4];

            Log.i("akhiltag",name+phone+email+password+age);


            String data = "none";



            try{
                URL url = new URL(addinfo_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter  = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                data = URLEncoder.encode("name", "UTF-8") + "="+URLEncoder.encode(name,"UTF-8")+
                        "&"+URLEncoder.encode("phone","UTF-8")+ "="+URLEncoder.encode(phone,"UTF-8")+
                        "&"+URLEncoder.encode("email","UTF-8")+ "="+URLEncoder.encode(email,"UTF-8")+
                        "&"+URLEncoder.encode("password","UTF-8")+ "="+URLEncoder.encode(password,"UTF-8")+
                        "&"+ URLEncoder.encode("age","UTF-8") + "="+URLEncoder.encode(age,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS = httpURLConnection.getInputStream();
                IS.close();

                httpURLConnection.disconnect();
                return  data;


            }catch (MalformedURLException e) {
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }



            return "registered !";
        }


        @Override
        protected void onPostExecute(String aresult) {
        Toast.makeText(getApplicationContext(), "registered !", Toast.LENGTH_LONG).show();

        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }

}



