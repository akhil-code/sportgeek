package com.prasanth.sportgeek;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;


public class homepage extends Activity {
    int kill_page_flag = 0;

    public static final String PREFS_FILE_NAME = "myfile";
    public static final String BACK_PRESSED_FLAG_NAME = "back_pressed";
    public static final String LOGGED_OUT_FLAG = "loggedout";



    Button imgButton, imgButton0;
    Button imgButton2, imgButton9;
    Button imgButton3, imgButton8;
    Button imgButton4, imgButton7;
    Button setting_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


        SharedPreferences sharedPreferences2;
        sharedPreferences2 = getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        String temp_loggedout = sharedPreferences2.getString(LOGGED_OUT_FLAG,"0");
        if(temp_loggedout.contentEquals("1")){
            kill_page_flag=1;
            Intent i = new Intent(homepage.this, com.prasanth.sportgeek.login.class);
            startActivity(i);
        }

        setting_button = (Button)findViewById(R.id.settings_button);
        setting_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(homepage.this, setting_button);
                popup.getMenuInflater().inflate(R.menu.settings_popupmenu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getTitle().toString().contentEquals("Log out")){
                            Intent i = new Intent(homepage.this, com.prasanth.sportgeek.login.class);
                            kill_page_flag = 1;

                            SharedPreferences sharedPreferences1;
                            sharedPreferences1 = getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences1.edit();
                            editor.putString(LOGGED_OUT_FLAG,"1");
                            editor.commit();


                            startActivity(i);
                        }
                        if(item.getTitle().toString().contentEquals("Your Profile")){
                            Intent i = new Intent(getApplicationContext(),Edit_user_details.class);
                            startActivity(i);
                        }
                        return true;
                    }
                });
                popup.show();//showing popup menu

            }
        });

        imgButton =(Button)findViewById(R.id.button2);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Get ready to football", Toast.LENGTH_LONG).show();
                Intent nxt = new Intent(getApplicationContext(), badminton_page.class);
                startActivity(nxt);
            }
        });
        imgButton2 =(Button)findViewById(R.id.button4);
        imgButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Get ready to basketball",Toast.LENGTH_LONG).show();
                Intent nxt = new Intent(getApplicationContext(),badminton_page.class);
                startActivity(nxt);
            }
        });
        imgButton3 =(Button)findViewById(R.id.button5);
        imgButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Get ready to Volleyball",Toast.LENGTH_LONG).show();
                Intent nxt = new Intent(getApplicationContext(),badminton_page.class);
                startActivity(nxt);
            }
        });
        imgButton4 =(Button)findViewById(R.id.button6);
        imgButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Get ready to smash the shuttle",Toast.LENGTH_LONG).show();
                Intent nxt = new Intent(getApplicationContext(),badminton_page.class);
                startActivity(nxt);
            }
        });
        imgButton0 =(Button)findViewById(R.id.button);
        imgButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Get ready to Tennis",Toast.LENGTH_LONG).show();
                Intent nxt = new Intent(getApplicationContext(),badminton_page.class);
                startActivity(nxt);
            }
        });
        imgButton7 =(Button)findViewById(R.id.button7);
        imgButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Get ready to Hockey",Toast.LENGTH_LONG).show();
                Intent nxt = new Intent(getApplicationContext(),badminton_page.class);
                startActivity(nxt);
            }
        });
        imgButton9 =(Button)findViewById(R.id.button9);
        imgButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Get ready to Throwball",Toast.LENGTH_LONG).show();
                Intent nxt = new Intent(getApplicationContext(),badminton_page.class);
                startActivity(nxt);
            }
        });
        imgButton8 =(Button)findViewById(R.id.button8);
        imgButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Get ready to Cricket",Toast.LENGTH_LONG).show();
                Intent nxt = new Intent(getApplicationContext(),badminton_page.class);
                startActivity(nxt);
            }
        });
    }

    @Override
    public void onBackPressed() {
        SharedPreferences sharedPreferences1;
        sharedPreferences1 = getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences1.edit();
        editor.putString(BACK_PRESSED_FLAG_NAME,"0");
        editor.commit();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(kill_page_flag == 1){
            finish();
        }
    }
}
