package com.example.github_researcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class SavedRepositoriesActivity extends AppCompatActivity {

    ListView list;
    ArrayList<String> array;
    ArrayAdapter<String> arrayAdapter;
    Thread thread;
    float x1,x2,y1,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        list = findViewById(R.id.list);

        array = new ArrayList<>();

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.listlayout, array);


    }

    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();

                if(x1 > x2){
                    Intent i = new Intent(SavedRepositoriesActivity.this, MainActivity.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }

    public void loadSavedRepos(){
        thread = new Thread(new Runnable(){
            @Override
            public void run() {
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

    public boolean fileExist(String fname){
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }
}