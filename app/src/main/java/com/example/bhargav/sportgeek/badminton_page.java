package com.example.bhargav.sportgeek;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;


public class badminton_page extends ActionBarActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    data_base db;
    Button btn;
    ListView listView;
    ArrayList<String> arrayList;
    ArrayList<String> array2;
    ArrayList<Integer> array3;
    ArrayList<Integer> usersid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badminton_page);

        db = new data_base(this);


        arrayList = db.get_users();
        array2=db.get_useremails();
        array3=db.get_userage();
        usersid = db.get_usersid();

        listView = (ListView) findViewById(R.id.listView2);

        //  to convert the string array into array view
        //     ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

        myadapter1 myad = new myadapter1(this,arrayList,usersid,array2,array3);

        //set the array view in listview
        listView.setAdapter(myad);
        listView.setOnItemClickListener(this);


    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String tittle= "you found someone";
        String subject = arrayList.get(position)+" is ready to play";
        Random rn = new Random();
        int answer = rn.nextInt(10) + 1;
        String body = "Only "+answer+" kilometres away from you";

        //Android provides NotificationManager class for this purpose. In order to use this class, you need to instantiate an object of this class by requesting the android system through getSystemService() method
        NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        //After that you will create Notification through Notification class and specify its attributes such as icon,title and time e.t.c
        Notification notify=new Notification(R.drawable.a,tittle,System.currentTimeMillis());

        //The next thing you need to do is to create a PendingIntent by passing context and intent as a parameter.
        // By giving a PendingIntent to another application,
        // you are granting it the right to perform the operation you have specified as if the other application was yourself.
        //PendingIntent.FLAG_UPDATE_CURRENT
        PendingIntent pending= PendingIntent.getActivity(getApplicationContext(), 0, new Intent(), 0);

        notify.setLatestEventInfo(getApplicationContext(), subject, body, pending);

        notif.notify(0, notify);

    }
}
class myadapter1 extends ArrayAdapter<String>
{

    Context context;
    ArrayList<String> arrylist;
    ArrayList<Integer> arryids;
    ArrayList<String> arryemail;
    ArrayList<Integer> arryage;
    public myadapter1(Context c, ArrayList<String> ls, ArrayList<Integer> ids, ArrayList<String> email, ArrayList<Integer> age) {
        super(c, R.layout.single_row, R.id.textView2, ls);
        this.context = c;
        this.arrylist = ls;
        this.arryids = ids;
        this.arryemail=email;
        this.arryage=age;
    }

    public View getView(int position,View convrtview,ViewGroup parent)
    {
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflator.inflate(R.layout.single1,parent,false);

        TextView textView = (TextView) row.findViewById(R.id.textView2);
        textView.setText(arrylist.get(position));



        TextView textView4 = (TextView) row.findViewById(R.id.textView4);
        textView4.setText("age:" + arryage.get(position));

        return row;
    }
}