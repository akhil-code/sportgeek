

package com.prasanth.sportgeek;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class admin_login extends Activity {
    ProgressBar spinner;
    Context context;


    com.prasanth.sportgeek.data_base db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button btn;

        db = new com.prasanth.sportgeek.data_base(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_login);

        this.context = getApplicationContext();
        final EditText txtUserName = (EditText)findViewById(R.id.text0);
        final EditText txtPassword = (EditText) findViewById(R.id.text01);
        btn = (Button) findViewById(R.id.button12);
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);

//
//                Cursor cursor = db.Login_check(txtUserName.getText().toString(), txtPassword.getText().toString());
//                //int count = 0;
//                // Toast.makeText(getApplicationContext(),"records"+cursor.getCount(),Toast.LENGTH_LONG).show();
//                if (cursor.getCount() > 0) {
//                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(admin_login.this, com.bhargav.sportgeek.Second_activity.class));
//                } else
//                    Toast.makeText(getApplicationContext(), "please enter valid username & password", Toast.LENGTH_LONG).show();

                com.prasanth.sportgeek.Authenticator_admin authenticator_admin = new com.prasanth.sportgeek.Authenticator_admin(admin_login.this,txtUserName.getText().toString(),txtPassword.getText().toString(),spinner);
                authenticator_admin.return_data();

            }
        });




    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}