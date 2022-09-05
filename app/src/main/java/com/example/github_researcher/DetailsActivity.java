package com.example.github_researcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DetailsActivity extends AppCompatActivity {

    Button button;
    TextView nameOfRepo;
    TextView details;
    String repoString;
    String userName;
    String lastUpdate;
    StringBuilder repoLink;
    StringBuilder detailsText;
    Thread thread;
    JSONObject user;
    ApiConnector connector;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        repoString = intent.getStringExtra("REPO");
        userName = intent.getStringExtra("NAME");
        lastUpdate = intent.getStringExtra("UPDATE");


        repoLink = new StringBuilder("https://github.com/");
        detailsText = new StringBuilder("LANGUAGES:").append("\n");

        connector = new ApiConnector();

        button = findViewById(R.id.goBackToRepos);

        nameOfRepo = findViewById(R.id.nameOfRepo);

        nameOfRepo.setText(repoString);

        details = findViewById(R.id.details);


        readDetails2();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    public void readDetails2(){
        thread = new Thread(new Runnable(){
            @Override
            public void run() {

                user = connector.connectLanguages(userName, repoString);

                Iterator keys = user.keys();

                detailsText.append("\n");

                while(keys.hasNext()){
                    detailsText.append((String)keys.next() + "\n" );
                }



                detailsText.append("\n");
                detailsText.append("UPDATED AT:" + "\n");

                detailsText.append(lastUpdate);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        details.setText(detailsText.toString());
                    }
                });

            }
        });
        thread.start();
    }
}
