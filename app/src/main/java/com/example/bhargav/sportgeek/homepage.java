package com.example.bhargav.sportgeek;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class homepage extends Activity {
      Button imgButton, imgButton0;
    Button imgButton2, imgButton9;
    Button imgButton3, imgButton8;
    Button imgButton4, imgButton7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

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



}
