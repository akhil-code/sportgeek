package com.example.bhargav.sportgeek;

import android.app.AlertDialog;
import android.app.Dialog;
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


public class Second_activity extends ActionBarActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

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
        setContentView(R.layout.activity_second_activity);

        db = new data_base(this);


         arrayList = db.get_users();
        array2=db.get_useremails();
        array3=db.get_userage();
        usersid = db.get_usersid();

        listView = (ListView) findViewById(R.id.listView);

        //  to convert the string array into array view
   //     ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

        myadapter myad = new myadapter(this,arrayList,usersid,array2,array3);

        //set the array view in listview
        listView.setAdapter(myad);
        listView.setOnItemClickListener(this);

        btn = (Button) findViewById(R.id.button3);
        btn.setOnClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        String str = arrayList.get(position);


        // alterdialog box to ask do you want to delete the record or not
       final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Do you want to delete it?")
                .setCancelable(true)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        db.delete_user(usersid.get(position));

                        startActivity(new Intent(Second_activity.this, Second_activity.class));

                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });



            CharSequence[] items = {"edit","delete"};
        new AlertDialog.Builder(this).setTitle("Hi admin").setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (item == 0) {

                }
                if (item == 1) {

                      AlertDialog alertDialog = builder.create();
                     alertDialog.setTitle("Hi Admin");
                      alertDialog.show();
                }
            }
        }).show();






        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
    }

    public void onClick(View v) {
        //int a = 0, b = 0, c = 0, d = 0, e = 0;

        //Toast.makeText(getApplicationContext(), "Pleae provide your name", Toast.LENGTH_LONG).show();

        startActivity(new Intent(Second_activity.this,MainActivity.class));
    }
}
class myadapter extends ArrayAdapter<String>
{

    Context context;
    ArrayList<String> arrylist;
    ArrayList<Integer> arryids;
    ArrayList<String> arryemail;
    ArrayList<Integer> arryage;
    public myadapter(Context c, ArrayList<String> ls, ArrayList<Integer> ids, ArrayList<String> email, ArrayList<Integer> age) {
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
        View row = inflator.inflate(R.layout.single_row,parent,false);

        TextView textView = (TextView) row.findViewById(R.id.textView2);
        textView.setText(arrylist.get(position));

        TextView textView2 = (TextView) row.findViewById(R.id.textView3);
        textView2.setText("id:   "+arryids.get(position));

        TextView textView3= (TextView) row.findViewById(R.id.textView4);
        textView3.setText("email:"+arryemail.get(position));

        TextView textView4 = (TextView) row.findViewById(R.id.textView5);
        textView4.setText("age:  "+ arryage.get(position));

        return row;
    }
}