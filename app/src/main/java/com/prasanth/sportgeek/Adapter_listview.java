package com.prasanth.sportgeek;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Adapter_listview extends ArrayAdapter<String> {
    String[] names1,names2,names3;



    public Adapter_listview(Context context, String[] names, String[] names1,String[] names2,String[] names3) {
        super(context, R.layout.activity_second_activity, names);
        this.names1=names1;
        this.names2 = names2;
        this.names3 = names3;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater akhilsinflater = LayoutInflater.from(getContext());
        View customView = akhilsinflater.inflate(R.layout.layout_adapter_fragment, parent, false);

        String singlename = getItem(position);
        String doublename = names1[position];
        String triplename = names2[position];
        String quadname = names3[position];

        TextView t1 = (TextView)customView.findViewById(R.id.t1);
        TextView t2 = (TextView)customView.findViewById(R.id.t2);
        TextView t3 = (TextView)customView.findViewById(R.id.t3);
        TextView t4 = (TextView)customView.findViewById(R.id.t4);



        t1.setText(singlename);
        t2.setText(doublename);
        t3.setText(triplename);
        t4.setText(quadname);


        t1.setTextColor(Color.WHITE);
        t2.setTextColor(Color.WHITE);
        t3.setTextColor(Color.WHITE);
        t4.setTextColor(Color.WHITE);


        return customView;
    }



}