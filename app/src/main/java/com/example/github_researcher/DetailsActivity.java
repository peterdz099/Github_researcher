package com.example.github_researcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    Button button;
    TextView nameOfRepo;
    TextView details;
    String repoString;
    String userName;
    StringBuilder repoLink;
    StringBuilder detailsText;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        repoString = intent.getStringExtra("REPO");
        userName = intent.getStringExtra("NAME");

        repoLink = new StringBuilder("https://github.com/");
        detailsText = new StringBuilder("LANGUAGES:").append("\n");

        button = findViewById(R.id.goBackToRepos);

        nameOfRepo = findViewById(R.id.nameOfRepo);

        nameOfRepo.setText(repoString);

        details = findViewById(R.id.details);

        readDetails();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void readDetails(){
        thread = new Thread(new Runnable(){
            @Override
            public void run() {
                repoLink.append(userName).append("/").append(repoString);
                System.out.println(repoLink.toString());
                try {
                    Document doc = Jsoup.connect(repoLink.toString()).get();
                    //Document doc = Jsoup.connect("https://github.com/bazelbuild/bazel").get();
                    Elements languages = doc.getElementsByClass("d-inline-flex flex-items-center flex-nowrap Link--secondary no-underline text-small mr-3");
                    Elements languageLast = doc.getElementsByClass("d-inline-flex flex-items-center flex-nowrap text-small mr-3");
                    for (Element language : languages) {
                        detailsText.append(language.text()).append("\n");
                    }
                    detailsText.append(languageLast.text());
                }catch (IOException e) {
                    e.printStackTrace();
                }
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
