package com.example.github_researcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Repositories extends AppCompatActivity {
    ListView list;

    ArrayList<String> array;
    ArrayAdapter<String> arrayAdapter;
    Researcher researcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

        researcher = new Researcher();

        list = findViewById(R.id.list);

        arrayAdapter = new ArrayAdapter<String>(this,R.layout.listlayout, array);

        list.setAdapter(arrayAdapter);


    }
}