package com.example.nimmy.task8_quotes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

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

/**
 * Created by Nimmy on 14-02-2017.
 */

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

       int i = getIntent().getIntExtra("position",1);
        new MyClass2().execute("http://rapidans.esy.es/test/getquotes.php?cat_id="+i);
    }

    class MyClass2 extends AsyncTask<String, Void, String> {

        private ProgressDialog dialog;
        CustomAdapter_List listAdapter;
        private ListView listView;
        Post2 post2;
        ArrayList<Post2> postArrayList2 = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog=new ProgressDialog(MainActivity2.this);
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

            try {
                JSONObject rootObject = new JSONObject(s);
                JSONArray jsonArray = rootObject.getJSONArray("data");
                for (int i = 0; i <jsonArray.length() ; i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    post2 = new Post2();
//                    int id  = jsonObject.getInt("id");
//                    int  cat_id = jsonObject.getInt("cat_id");
//                    String quotes = jsonObject.getString("quotes");
                    post2.setId(jsonObject.getInt("id"));
                    post2.setCat_id(jsonObject.getInt("cat_id"));
                    post2.setQuotes(jsonObject.getString("quotes"));

                    postArrayList2.add(post2);


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            listView = (ListView) findViewById(R.id.main_listview);
            listAdapter = new CustomAdapter_List(MainActivity2.this,postArrayList2);
            listView.setAdapter(listAdapter);

        }
    }
}
