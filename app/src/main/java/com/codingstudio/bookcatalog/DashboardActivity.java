package com.codingstudio.bookcatalog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.codingstudio.bookcatalog.model.SessionManagement;
import com.codingstudio.bookcatalog.viewFregment.HomeFragment;
import com.codingstudio.bookcatalog.viewFregment.SearchFragment;
import com.codingstudio.bookcatalog.viewFregment.WriterDashboardFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        session = new SessionManagement(this);
        bottomNav = findViewById(R.id.bottomNav);

        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
            bottomNav.setSelectedItemId(R.id.nav_home);
        }

        bottomNav.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.nav_home) {
                loadFragment(new HomeFragment());
                return true;
            }

            if (item.getItemId() == R.id.nav_search) {
                loadFragment(new SearchFragment());
                return true;
            }

            if (item.getItemId() == R.id.nav_favorite) {
                if (!checkLogin()) return false;

                Toast.makeText(this,
                        "Fitur Favorite segera hadir",
                        Toast.LENGTH_SHORT).show();
                return true;
            }

            if (item.getItemId() == R.id.nav_write) {
                if (!checkLogin()) return false;
                loadFragment(new WriterDashboardFragment());
                return true;
            }

            return false;
        });
    }
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in_right,
                        R.anim.slide_out_left,
                        R.anim.slide_in_left,
                        R.anim.slide_out_right
                )
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    // ================= LOGIN GUARD =================
    private boolean checkLogin() {
        if (!session.isLogin()) {
            Toast.makeText(this,
                    "Silakan login terlebih dahulu",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}