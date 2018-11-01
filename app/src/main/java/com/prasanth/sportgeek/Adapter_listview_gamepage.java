package com.prasanth.sportgeek;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class Adapter_listview_gamepage extends ArrayAdapter<String> {
    String[] names1;


    public Adapter_listview_gamepage(Context context, String[] names, String[] names1) {
        super(context, R.layout.activity_second_activity, names);
        this.names1=names1;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater akhilsinflater = LayoutInflater.from(getContext());
        View customView = akhilsinflater.inflate(R.layout.single1, parent, false);

        String singlename = getItem(position);
        String doublename = names1[position];

        TextView t1 = (TextView)customView.findViewById(R.id.textView2);
        TextView t2 = (TextView)customView.findViewById(R.id.textView4);



        t1.setText(singlename);
        t2.setText(doublename);


        t1.setTextColor(Color.WHITE);
        t2.setTextColor(Color.WHITE);


        return customView;
    }

}