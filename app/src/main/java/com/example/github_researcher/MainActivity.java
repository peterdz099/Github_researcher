package com.example.github_researcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    StringBuilder userLink = new StringBuilder("https://github.com/");
    ImageView img;
    Button loadButton;
    TextView login;
    TextView message;
    Thread thread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        login = findViewById(R.id.loginView);

        img = findViewById(R.id.img);

        message = findViewById(R.id.user_not_found);

        loadButton = findViewById(R.id.loadButton);

        configureLoadButton();

    }
    private void configureLoadButton(){
        loadButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                userLink.append(login.getText().toString()).append("?tab=repositories");
                if(!login.getText().toString().isEmpty()){
                    thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Document doc = Jsoup.connect(userLink.toString()).get();
                                startActivity(new Intent(MainActivity.this,RepositoriesActivity.class).putExtra("LINK",userLink.toString()).putExtra("NAME",login.getText().toString()));
                                finish();
                            }catch(org.jsoup.HttpStatusException ex){
                                if (ex.getStatusCode() == 404){
                                    runOnUiThread(new Runnable(){
                                        @Override
                                        public void run() {
                                            message.setVisibility(View.VISIBLE);
                                            System.out.println("dupa");
                                            userLink.delete(19,userLink.length());
                                            login.setText("");
                                        }
                                    });
                                }
                            }catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    thread.start();
                }
            }
        });
    }
}