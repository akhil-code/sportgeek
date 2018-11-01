package com.prasanth.sportgeek;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;


public class badminton_page extends ActionBarActivity implements OnMapReadyCallback {
    String main_line="";
    ListView listView;

    private GoogleMap mMap;


    String[] names = new String[20];
    String[] names1= new String[20];
    Context context;

    com.prasanth.sportgeek.data_base db;
    Button btn;

    /*
    ListView listView;
    ArrayList<String> arrayList;
    ArrayList<String> array2;
    ArrayList<Integer> array3;
    ArrayList<Integer> usersid;
    */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(29.9493,76.8164);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setMyLocationEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badminton_page);
        this.context = getApplicationContext();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        /*
        db = new com.bhargav.sportgeek.data_base(this);


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
        */
//THROUGH INTERNET





        ListAdapter akhilsadapter =new com.prasanth.sportgeek.Adapter_listview_gamepage(this,names,names1);
        listView= (ListView)findViewById(R.id.main_listview);
        listView.setAdapter(akhilsadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String tittle= "you found someone";
                String subject = names[position]+" is ready to play";
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
        });


        BackgroundTask_retrieve_message backgroundTask_retrieve_message1 = new BackgroundTask_retrieve_message();
        backgroundTask_retrieve_message1.execute();

        //THROUGH INTERNET ENDS HERE

    }




    /*
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

    */



    private class BackgroundTask_retrieve_message extends AsyncTask<Void,Void,String> {




        String JSON_url;
        public String JSON_string;
        private String json_string_msg;

        public BackgroundTask_retrieve_message() {
        }

        @Override
        protected String doInBackground(Void... voids) {

            try{
                URL url = new URL(JSON_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder stringBuilder = new StringBuilder();
                while((JSON_string = bufferedReader.readLine())!=null){

                    stringBuilder.append(JSON_string+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            }catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch(IOException e){
                e.printStackTrace();
            }

            return null;
        }



        @Override
        protected void onPreExecute() {
            JSON_url="http://akhil.freevar.com/phpx/json_get_data.php";

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


        @Override
        protected void onPostExecute(String result) {
            json_string_msg=result;
            decode_json(json_string_msg);
            System.out.println(main_line);

            ListAdapter newadapter =new com.prasanth.sportgeek.Adapter_listview_gamepage(context,names,names1);
            listView.setAdapter(newadapter);
            main_line = "" ;



        }
        JSONObject jsonObject;
        JSONArray jsonArray;
        public void decode_json(String json_string){

            try {
                jsonObject = new JSONObject(json_string);
                jsonArray = jsonObject.getJSONArray("server_response");

                int count1=0;
                String username,message,email,age;
                while(count1<jsonArray.length()){
                    JSONObject JO = jsonArray.getJSONObject(count1);
                    username = JO.getString("name");
                    message = JO.getString("age");

                    names[count1] = username;
                    names1[count1] = message;

                    count1++;

                }


            }
            catch (JSONException e) {
                e.printStackTrace();
            }

        }


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