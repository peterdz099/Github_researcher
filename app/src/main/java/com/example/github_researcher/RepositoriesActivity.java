package com.example.github_researcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
    ApiConnector connector;
    JSONObject user;
    String lastUpdate;
    ArrayList<String> languages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

        Intent intent = getIntent();

        userLink = intent.getStringExtra("LINK");
        userName = intent.getStringExtra("NAME");

        connector = new ApiConnector();

        list = findViewById(R.id.list);

        array = new ArrayList<>();

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.listlayout, array);

        //findRepos();
        findRepos2();



        goBackButton = findViewById(R.id.goBack);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RepositoriesActivity.this,MainActivity.class));
                finish();
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String lastUpdate;
                try {
                    lastUpdate = user.getJSONArray("items").getJSONObject(i).getString("updated_at");
                    Intent intent = new Intent(RepositoriesActivity.this,DetailsActivity.class);
                    intent.putExtra("REPO",list.getItemAtPosition(i).toString());
                    intent.putExtra("NAME",userName);
                    intent.putExtra("UPDATE",lastUpdate);
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            if(!list.getItemAtPosition(i).equals("User doesn't have any public repositories yet")){
                if(fileExist("SavedRepositories.json")){
                    try {

                        String update = user.getJSONArray("items").getJSONObject(i).getString("updated_at");
                        String savedRepos = readFromFile("SavedRepositories.json");

                        JSONObject obj = new JSONObject();
                        JSONArray arr = new JSONArray(savedRepos);

                        System.out.println(arr);

                        obj.put("USERNAME",userName);
                        obj.put("REPO",list.getItemAtPosition(i).toString());
                        obj.put("UPDATE",update);

                        if(isSaved(arr,obj)){
                            Toast.makeText(getApplicationContext(),"Already Saved",Toast.LENGTH_SHORT).show();
                        }else {

                            arr.put(obj);
                            writeToFile("SavedRepositories.json", arr.toString());
                            System.out.println(arr);

                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    try {
                        JSONArray arr = new JSONArray();
                        JSONObject obj = new JSONObject();

                        obj.put("USERNAME", userName);
                        obj.put("REPO", list.getItemAtPosition(i).toString());
                        obj.put("UPDATE", "1.1.1.1");
                        arr.put(obj);

                        writeToFile("SavedRepositories.json", arr.toString());

                    }catch(JSONException ex){
                        ex.printStackTrace();
                    }
                }
                }
            }
        });
    }

    public void findRepos2() {
        thread = new Thread(new Runnable(){
            @Override
            public void run() {

                user = connector.connect(userName);

                connector.loadRepos(user,array);

                if(array.isEmpty()){
                    array.add("User doesn't have any public repositories yet");
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



    public void writeToFile(String name, String content){
        File path = getApplicationContext().getFilesDir();
        try{
            FileOutputStream writer = new FileOutputStream(new File(path,name));
            writer.write(content.getBytes());
            writer.close();
            Toast.makeText(getApplicationContext(),"SAVED",Toast.LENGTH_SHORT).show();
        }catch ( Exception e){
            e.printStackTrace();
        }
    }

    public String readFromFile(String filename){
        File path = getApplicationContext().getFilesDir();
        File readFrom = new File(path,filename);
        byte[] content = new byte[(int)readFrom.length()];
        try{
            FileInputStream stream = new FileInputStream(readFrom);
            stream.read(content);
            return new String(content);
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }

    public boolean isSaved (JSONArray arr, JSONObject object) throws JSONException {
        for(int i=0; i<arr.length(); i++){
            JSONObject temp = arr.getJSONObject(i);
            if(temp.get("REPO").equals(object.get("REPO"))){
                if(temp.get("USERNAME").equals(object.get("USERNAME"))){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean fileExist(String fname){
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }
}

