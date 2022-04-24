package com.example.github_researcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

public class RepositoriesActivity extends AppCompatActivity{

    ListView list;
    ArrayList<String> array;
    ArrayAdapter<String> arrayAdapter;
    Button goBackButton;
    Thread thread;
    String userLink;
    String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

        Intent intent = getIntent();

        userLink = intent.getStringExtra("LINK");
        userName = intent.getStringExtra("NAME");

        array = new ArrayList<>();

        list = findViewById(R.id.list);

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.listlayout, array);


        findRepos();

        goBackButton = findViewById(R.id.goBack);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RepositoriesActivity.this,MainActivity.class));
                finish();
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(RepositoriesActivity.this,DetailsActivity.class);
                intent.putExtra("REPO",list.getItemAtPosition(i).toString());
                intent.putExtra("NAME",userName);
                startActivity(intent);
            }
        });
    }
    public void findRepos() {

        thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(userLink).get();
                    Elements repos = doc.getElementsByClass("wb-break-all");
                    Elements noRepos = doc.getElementsByClass("blankslate-heading");
                    for (Element repo : repos) {
                        array.add(repo.text().substring(0,repo.text().length()-7));
                    }

                    if(!repos.hasText()) {
                        array.add(noRepos.text());
                    }

                }catch (IOException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        list.setAdapter(arrayAdapter);
                    }
                });
            }
        });
        thread.start();
    }
}

