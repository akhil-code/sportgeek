package com.prasanth.sportgeek;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class Second_activity extends ActionBarActivity {

    String main_line="";
    ListView akhilslistview;

    String[] names = new String[20];
    String[] names1= new String[20];
    String[] names2= new String[20];
    String[] names3= new String[20];


    Context context;
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
        this.context = getApplicationContext();

        //THROUGH INTERNET





        ListAdapter akhilsadapter =new com.prasanth.sportgeek.Adapter_listview(this,names,names1,names2,names3);
        akhilslistview= (ListView)findViewById(R.id.main_listview);
        akhilslistview.setAdapter(akhilsadapter);
        akhilslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"delete",Toast.LENGTH_LONG).show();
                final int pos = position;
                String delete_key = names[pos];
                Log.i("akhilstag",delete_key);
                BackgroundTask_delete backgroundTask_delete = new BackgroundTask_delete();
                backgroundTask_delete.execute(delete_key);



//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setMessage("Delete ?")
//                        .setCancelable(false)
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                            }
//                        });
//
//                //Creating dialog box
//                AlertDialog alert = builder.create();
//                alert.setTitle("Delete");
//                alert.show();

            }
        });



        BackgroundTask_retrieve_message backgroundTask_retrieve_message1 = new BackgroundTask_retrieve_message();
        backgroundTask_retrieve_message1.execute();

        //THROUGH INTERNET ENDS HERE

        //WITHOUT INTERNET
//         arrayList = db.get_users();
//        array2=db.get_useremails();
//        array3=db.get_userage();
//        usersid = db.get_usersid();
//
//        listView = (ListView) findViewById(R.id.listView);
//        myadapter myad = new myadapter(this,arrayList,usersid,array2,array3);
//
//        //set the array view in listview
//        listView.setAdapter(myad);
//        listView.setOnItemClickListener(this);



    }



    public void back_to_loginpage(View v){
        Intent i = new Intent(context,login.class);
        startActivity(i);

    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        finish();
//        Intent i = new Intent(context,login.class);
//        startActivity(i);
//    }



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

            ListAdapter newadapter =new com.prasanth.sportgeek.Adapter_listview(context,names,names1,names2,names3);
            akhilslistview.setAdapter(newadapter);
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
                    message = JO.getString("phone");
                    email = JO.getString("email");
                    age = JO.getString("age");

                    names[count1] = username;
                    names1[count1] = message;
                    names2[count1] = email;
                    names3[count1] = age;

                    count1++;

                }


            }
            catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }



  private  class BackgroundTask_delete extends AsyncTask<String,Void,String> {

        String addinfo_url;

        @Override
        protected void onPreExecute() {
            addinfo_url="http://akhil.freevar.com/phpx/delete_record.php";
        }

        @Override
        protected String doInBackground(String... args) {

            String name = args[0];

            String data = "none";

            try{
                URL url = new URL(addinfo_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter  = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                data = URLEncoder.encode("name", "UTF-8") + "="+URLEncoder.encode(name,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS = httpURLConnection.getInputStream();
                IS.close();

                httpURLConnection.disconnect();
                return  data;


            }catch (MalformedURLException e) {
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }



            return "registered !";
        }


        @Override
        protected void onPostExecute(String aresult) {

        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }

}





//Another subclass
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
        textView4.setText("age:  "+ arryage.get(position)+"\n");

        return row;
    }









}