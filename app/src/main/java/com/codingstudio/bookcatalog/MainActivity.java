package com.codingstudio.bookcatalog;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    Button btn_oop,btn_main,btn_activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_oop = findViewById(R.id.btn_oop);
        btn_main = findViewById(R.id.btn_main);
        btn_activity = findViewById(R.id.btn_activity);

        btn_oop.setOnClickListener(v -> {
            startActivity(new Intent(this, OOPActivity.class));
        });
        btn_main.setOnClickListener(v -> {
            startActivity(new Intent(this,DashboardActivity.class));
            finish();
        });
        btn_activity.setOnClickListener(v -> {
            startActivity(new Intent(this,ToasActivity.class));
        });
    }


}