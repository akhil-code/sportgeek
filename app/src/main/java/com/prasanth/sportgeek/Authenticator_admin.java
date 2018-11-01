package com.prasanth.sportgeek;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

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
import java.util.Stack;


public class Authenticator_admin {
    protected String json_string;
    protected String input_username,input_password;
    protected Stack stack_username,stack_password;
    protected int final_count;
    Context context;


    public static final String PREFS_FILE_NAME = "myfile";
    public static final String LOGGED_OUT_FLAG = "loggedout";

    ProgressBar spinner;

    public Authenticator_admin(Context context, String input_username, String input_password,ProgressBar spinner) {
        this.input_username = input_username;
        this.input_password = input_password;
        this.context=context;
        this.spinner = spinner;
    }

    public void return_data(){

        stack_username = new Stack();
        stack_password = new Stack();

        BackgroundTask1 backgroundTask1 = new BackgroundTask1();
        backgroundTask1.execute();
    }





    class BackgroundTask1 extends AsyncTask<Void,Void,String> {


        String JSON_url;
        private String JSON_string;

        public BackgroundTask1() {
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
            json_string=result;
            Log.i("akhiltag",json_string);
            decode_json();
            final_count = match_case();
            if(final_count==1){

                Toast.makeText(context,"Login Success",Toast.LENGTH_LONG).show();

                SharedPreferences sharedPreferences;
                sharedPreferences = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(LOGGED_OUT_FLAG,"0");
                editor.commit();




                Intent i = new Intent(context, com.prasanth.sportgeek.Second_activity.class);
                i.putExtra("username",input_username);
//                spinner.setVisibility(View.GONE);

                context.startActivity(i);


            }
            else {
                Toast.makeText(context,"login Failed",Toast.LENGTH_LONG).show();
//                spinner.setVisibility(View.GONE);

            }


        }


    }



    JSONObject jsonObject;
    JSONArray jsonArray;


    public void decode_json(){

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");

            int count1=0;
            String username,password;
            System.out.println("length of array is "+jsonArray.length());
            while(count1<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count1);
                username = JO.getString("name");
                password = JO.getString("password");



                stack_username.push(new String(username));
                stack_password.push(new String(password));

                count1++;



            }


        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private int match_case(){
        int counter=0;
        int countx,county;
        for(int i=0;i<stack_username.size();i++){
            String field1=(String)stack_username.get(i);
            String field2=input_username;
            String field3=(String)stack_password.get(i);
            String field4=input_password;

            System.out.println(field1+field2+field3+field4);
            countx=field1.compareTo(field2);
            county=field3.compareTo(field4);
            if(countx==0 && county==0){
                counter++; System.out.print("success");
            }



        }
        System.out.println("local counter counts"+counter);
        return counter;

    }

}

