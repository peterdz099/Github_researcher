package com.example.github_researcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //boolean click = true;
    Researcher researcher;
    String user;
    ImageView img;
    Button loadButton;
    TextView login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        configureLoadButton();

        login = findViewById(R.id.loginView);

        img = findViewById(R.id.img);

        researcher = new Researcher();

    }
    private void configureLoadButton(){
        loadButton = findViewById(R.id.loadButton);

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RepositoriesActivity.class));
            }
        });
    }
}