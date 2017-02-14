package com.example.nimmy.task8_quotes;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

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

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MyClass().execute("http://rapidans.esy.es/test/getallcat.php");

//        new Again().execute();

    }

//    class Again extends AsyncTask<String,Void,String>{
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//        }
//    }

    class MyClass extends AsyncTask<String,Void,String>{

        private ProgressDialog dialog;
        private  CustomAdapter_Grid gridAdapter;
        private GridView gridView;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog=new ProgressDialog(MainActivity.this);
            dialog.setMessage("Loading...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection;
            try {
                URL url = new URL(params[0]);
                try {
                    connection = (HttpURLConnection)url.openConnection();
                    connection.connect();

                    InputStream stream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();
                    String line = "";

                    while ((line =reader.readLine())!= null){
                        buffer.append(line);
                    }

                    String bufferString = buffer.toString();
                    return  bufferString;


                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(dialog.isShowing()) {
                dialog.dismiss();
            }
            ArrayList<Post> postArrayList = new ArrayList<>();
            try {
                JSONObject rootObject = new JSONObject(s);
                JSONArray jsonArray = rootObject.getJSONArray("data");
                for (int i = 0; i <jsonArray.length() ; i++) {
                    JSONObject postObject = jsonArray.getJSONObject(i);
                    Post post = new Post();
                    String id  = postObject.getString("id");
                    String name = postObject.getString("name");
                    post.setid(id);
                    post.setname(name);
                    postArrayList.add(post);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            gridView = (GridView) findViewById(R.id.main_gridview);
             gridAdapter = new CustomAdapter_Grid(MainActivity.this,postArrayList);
            gridView.setAdapter(gridAdapter);


        }
    }


}
