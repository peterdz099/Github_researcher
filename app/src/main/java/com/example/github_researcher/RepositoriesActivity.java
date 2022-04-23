package com.example.github_researcher;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

public class RepositoriesActivity extends AppCompatActivity {

    ListView list;
    ArrayList<String> array;
    ArrayAdapter<String> arrayAdapter;
    Button goBackButton;
    Thread thread;
    StringBuilder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

        array = new ArrayList<>();

        list = findViewById(R.id.list);

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.listlayout, array);


        findRepos();

        goBackButton = findViewById(R.id.goBack);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void findRepos() {

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect("https://github.com/peterdz099?tab=repositories").get();
                    //Elements name = doc.getElementsByClass("blankslate-heading");
                    Elements repos = doc.getElementsByClass("wb-break-all");
                    for (Element repo : repos) {
                        array.add(repo.text());
                    }

                    //if(!name.hasText()){}
                    //System.out.println(array);

                } catch (IOException e) {
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

