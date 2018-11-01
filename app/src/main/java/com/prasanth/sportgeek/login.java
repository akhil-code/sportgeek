

package com.prasanth.sportgeek;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class login extends Activity {
    public static final String LOGGED_OUT_FLAG = "loggedout";
    public static final String PREFS_FILE_NAME = "myfile";
    public static final String REGISTER_FLAG_NAME = "register_flag";
    ProgressBar spinner;



    data_base db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button btn;
        db = new data_base(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);

        btn = (Button) findViewById(R.id.button11);

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected()){
            btn.setEnabled(true);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please connect to internet",Toast.LENGTH_LONG).show();
            btn.setEnabled(false);

        }

        final EditText txtUserName = (EditText)findViewById(R.id.text1);
         final EditText txtPassword = (EditText) findViewById(R.id.text2);
        btn = (Button) findViewById(R.id.button11);
        spinner=(ProgressBar)findViewById(R.id.progressBar);

        spinner.setVisibility(View.GONE);
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                spinner.setVisibility(View.VISIBLE);

                Authenticator authenticator = new Authenticator(login.this,txtUserName.getText().toString(),txtPassword.getText().toString(),spinner);
                authenticator.return_data();


                /*

                Cursor cursor =  db.Login_check(txtUserName.getText().toString(), txtPassword.getText().toString());
                //int count = 0;
               // Toast.makeText(getApplicationContext(),"records"+cursor.getCount(),Toast.LENGTH_LONG).show();
                if(cursor.getCount()>0) {
                    Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_LONG).show();
                    Intent nxt = new Intent(getApplicationContext(),homepage.class);


                    SharedPreferences sharedPreferences1;
                    sharedPreferences1 = getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences1.edit();
                    editor.putString(LOGGED_OUT_FLAG,"0");
                    editor.commit();

                    startActivity(nxt);
                }
                else
                    Toast.makeText(getApplicationContext(), "please enter valid username & password", Toast.LENGTH_LONG).show();
                */
            }
        });

        TextView reg = (TextView)findViewById(R.id.reg);
        reg.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent nxt = new Intent(getApplicationContext(),MainActivity.class);

                SharedPreferences sharedPreferences1;
                sharedPreferences1 = getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putString(REGISTER_FLAG_NAME,"0");
                editor.commit();

                startActivity(nxt);
            }
        });

        TextView admin = (TextView)findViewById(R.id.admin);
        admin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent nxt = new Intent(getApplicationContext(),admin_login.class);
                startActivity(nxt);
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}