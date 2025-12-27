package com.codingstudio.bookcatalog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codingstudio.bookcatalog.asset.StoryAdapter;

import java.util.Arrays;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    RecyclerView rvBest, rvRandom, rvEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        View header = findViewById(R.id.header);

        TextView tvTitle = header.findViewById(R.id.tvHeaderTitle);
        Button btnAction = header.findViewById(R.id.btnHeaderAction);
        btnAction.setOnClickListener(v -> {

        });


        rvBest = findViewById(R.id.rvBest);
        rvRandom = findViewById(R.id.rvRandom);
        rvEditor = findViewById(R.id.rvEditor);

        setupRecycler(rvBest);
        setupRecycler(rvRandom);
        setupRecycler(rvEditor);
    }


    private void setupRecycler(RecyclerView rv) {
        rv.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );

        List<Integer> dummyImages = Arrays.asList(
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background
        );

        rv.setAdapter(new StoryAdapter(this, dummyImages));
    }
}