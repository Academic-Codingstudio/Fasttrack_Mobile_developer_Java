package com.codingstudio.bookcatalog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


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

        btnAction.setOnClickListener(v -> showLoginDialog());



        rvBest = findViewById(R.id.rvBest);
        rvRandom = findViewById(R.id.rvRandom);
        rvEditor = findViewById(R.id.rvEditor);

        setupRecycler(rvBest);
        setupRecycler(rvRandom);
        setupRecycler(rvEditor);
    }


    private void showLoginDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_login);
        dialog.setCancelable(true);

        EditText etUsername = dialog.findViewById(R.id.etUsername);
        EditText etPassword = dialog.findViewById(R.id.etPassword);
        Button btnLogin = dialog.findViewById(R.id.btnLogin);
        TextView tvRegister = dialog.findViewById(R.id.tvRegister);

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Username dan Password wajib diisi", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        tvRegister.setOnClickListener(v ->
                startActivity(new Intent(this,RegisterAcivity.class))
        );

        dialog.show();
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