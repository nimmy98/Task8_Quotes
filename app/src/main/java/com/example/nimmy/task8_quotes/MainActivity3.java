package com.example.nimmy.task8_quotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Nimmy on 14-02-2017.
 */

public class MainActivity3 extends AppCompatActivity {

    private TextView textView;
    private Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        textView = (TextView)findViewById(R.id.main_TV3);
        String quotes;
        Intent intent=getIntent();
        quotes=intent.getStringExtra("position");
        textView.setText(quotes);

        button  = (Button)findViewById(R.id.main_button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/html");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p>This is the text that will be shared.</p>"));
                startActivity(Intent.createChooser(shareIntent, "Share using"));
            }
        });

    }
}
