

package com.example.bhargav.sportgeek;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bhargav.sportgeek.R;

public class admin_login extends Activity {
    data_base db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button btn;

        db = new data_base(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_login);
        final EditText txtUserName = (EditText)findViewById(R.id.text0);
        final EditText txtPassword = (EditText) findViewById(R.id.text01);
        btn = (Button) findViewById(R.id.button12);
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Cursor cursor = db.Login_check(txtUserName.getText().toString(), txtPassword.getText().toString());
                //int count = 0;
                // Toast.makeText(getApplicationContext(),"records"+cursor.getCount(),Toast.LENGTH_LONG).show();
                if (cursor.getCount() > 0) {
                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(admin_login.this, Second_activity.class));
                } else
                    Toast.makeText(getApplicationContext(), "please enter valid username & password", Toast.LENGTH_LONG).show();
            }
        });




    }
}