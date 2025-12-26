package com.codingstudio.bookcatalog;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // =========================
    // CREATE
    // =========================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showToast("onCreate() → Activity dibuat");


    }

    // =========================
    // START
    // =========================
    @Override
    protected void onStart() {
        super.onStart();
        showToast("onStart() → Activity mulai terlihat");
    }

    // =========================
    // RESUME (Foreground)
    // =========================
    @Override
    protected void onResume() {
        super.onResume();
        showToast("onResume() → Activity aktif (foreground)");
    }

    // =========================
    // PAUSE (Sebentar ke background)
    // =========================
    @Override
    protected void onPause() {
        super.onPause();
        showToast("onPause() → Activity dijeda");
    }

    // =========================
    // STOP (Background)
    // =========================
    @Override
    protected void onStop() {
        super.onStop();
        showToast("onStop() → Activity di background");
    }

    // =========================
    // RESTART
    // =========================
    @Override
    protected void onRestart() {
        super.onRestart();
        showToast("onRestart() → Activity kembali dari background");
    }

    // =========================
    // DESTROY (Terminate)
    // =========================
    @Override
    protected void onDestroy() {
        super.onDestroy();
        showToast("onDestroy() → Activity dihancurkan");
    }
}