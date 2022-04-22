package com.example.github_researcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class RepositoriesActivity extends AppCompatActivity {
    ListView list;

    ArrayList<String> array;
    ArrayAdapter<String> arrayAdapter;
    Researcher researcher;
    Button goBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);
        configureBackButton();



        //researcher = new Researcher();

        //list = findViewById(R.id.list);

        //arrayAdapter = new ArrayAdapter<String>(this,R.layout.listlayout, array);

        //list.setAdapter(arrayAdapter);
    }
    private void configureBackButton(){
        goBackButton = findViewById(R.id.goBack);

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}