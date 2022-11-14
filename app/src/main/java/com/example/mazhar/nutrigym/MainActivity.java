package com.example.mazhar.nutrigym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                goToBMIActivity();
            }
        });
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                goToSCActivity();
            }
        });
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                goToFDActivity();
            }
        });
        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                goToSBActivity();
            }
        });
        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                goToExitActivity();
            }
        });
    }
    private void goToBMIActivity() {
        Intent intent = new Intent(this, BMIActivity.class);
        startActivity(intent);
    }
    private void goToSCActivity() {
        Intent intent2 = new Intent(this, SCActivity.class);
        startActivity(intent2);
    }
    private void goToFDActivity() {
        Intent intent3 = new Intent(this, LauncherActivity.class);
        startActivity(intent3);
    }
    private void goToSBActivity() {
        Intent intent4 = new Intent(this, SBActivity.class);
        startActivity(intent4);
    }
    private void goToExitActivity() {
        finish();
        System.exit(0);
    }
}
