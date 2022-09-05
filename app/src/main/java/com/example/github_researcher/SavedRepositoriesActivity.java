package com.example.github_researcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
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
    ApiConnector connector;
    float x1,x2,y1,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        list = findViewById(R.id.list);

        array = new ArrayList<>();

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.listlayout, array);

        connector = new ApiConnector();

        findSavedRepos();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                finish();
                startActivity(getIntent());
            }
        });
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
                    finish();

                }
                break;
        }
        return false;
    }

    public void findSavedRepos() {
        thread = new Thread(new Runnable(){
            @Override
            public void run() {
                if(fileExist("SavedRepositories.json")){
                   try{

                       String savedRepos = readFromFile("SavedRepositories.json");

                       JSONArray arr = new JSONArray(savedRepos);

                       loadSavedRepos(arr, array);


                   }catch (JSONException ex){
                       ex.printStackTrace();
                   }
                }else{
                    array.add("You haven't saved any repository yet");
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



    public String readFromFile(String filename){
        File path = getApplicationContext().getFilesDir();
        File readFrom = new File(path,filename);
        byte[] content = new byte[(int)readFrom.length()];
        try{
            FileInputStream stream = new FileInputStream(readFrom);
            stream.read(content);
            stream.close();
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
    public void loadSavedRepos(JSONArray savedRepos, ArrayList<String> array){

        String repoString;
        String lastUpdate;

        try {
            JSONObject object;

            System.out.println(savedRepos);

            for (int i = 0; i < savedRepos.length(); i++) {

                object = savedRepos.getJSONObject(i);
                lastUpdate = connector.connectUpdatedat((String) object.get("USERNAME"), (String) object.get("REPO"));

                if(lastUpdate.equals((String) object.get("UPDATE"))){

                    repoString = (String) (object.get("USERNAME") + "/" + object.get("REPO") +
                            "\n" + "UPDATED AT: " + object.get("UPDATE"));



                }else{
                    System.out.println(savedRepos);
                    repoString = (String) (object.get("USERNAME") + "/" + object.get("REPO") +
                            "\n" +  "UPDATED!!!!");
                    object.put("UPDATE",lastUpdate);
                    writeToFile("SavedRepositories.json", savedRepos.toString());
                }

                array.add(repoString);
                System.out.println(savedRepos);
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
}