package com.example.github_researcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.dom.Text;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    StringBuilder userLink = new StringBuilder("https://github.com/");
    ImageView img;
    Button loadButton;
    TextView login;
    TextView message;
    TextView swipe;
    Thread thread;
    float x1,x2,y1,y2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        login = findViewById(R.id.loginView);

        img = findViewById(R.id.img);

        message = findViewById(R.id.user_not_found);

        swipe = findViewById(R.id.user_not_found2);

        loadButton = findViewById(R.id.loadButton);

        configureLoadButton();

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

                if(x1 < x2){
                    Intent i = new Intent(MainActivity.this, SavedRepositoriesActivity.class);
                    startActivity(i);
                }
            break;
        }
        return false;
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
                                startActivity(new Intent(MainActivity.this,RepositoriesActivity.class).putExtra(
                                        "LINK",userLink.toString()).putExtra("NAME",login.getText().toString()));
                                finish();
                            }catch(org.jsoup.HttpStatusException ex){
                                if (ex.getStatusCode() == 404){
                                    runOnUiThread(new Runnable(){
                                        @Override
                                        public void run() {
                                            swipe.setVisibility(View.INVISIBLE);
                                            message.setVisibility(View.VISIBLE);
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