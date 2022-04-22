package com.example.github_researcher;

import androidx.appcompat.app.AppCompatActivity;

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

        login = findViewById(R.id.loginView);

        loadButton = findViewById(R.id.loadButton);

        img = findViewById(R.id.img);

        researcher = new Researcher();


        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = login.getText().toString();

                if (researcher.isUser()) {
                    Toast.makeText(MainActivity.this, "DUPA", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "DUsko", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}