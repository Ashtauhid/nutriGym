package com.example.mazhar.nutrigym;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class LauncherActivity extends AppCompatActivity {
    Button btnSearch,btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        init();
        register();
    }

    private void register() {
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LauncherActivity.this,MainActivity.class));
//                finish();
//            }
//        }
       // );
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LauncherActivity.this,SecondActivity.class));
                finish();
            }
        });
    }

    private void init() {
        btnSearch=(Button)findViewById(R.id.btnSearch);
        btnAdd=(Button)findViewById(R.id.btnAdd);
    }
}

