package com.codingstudio.bookcatalog.viewFregment;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codingstudio.bookcatalog.R;
import com.codingstudio.bookcatalog.RegisterActivity;
import com.codingstudio.bookcatalog.asset.StoryAdapter;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView rvBest, rvRandom, rvEditor;

    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        View header = view.findViewById(R.id.header);

        TextView tvTitle = header.findViewById(R.id.tvHeaderTitle);
        Button btnAction = header.findViewById(R.id.btnHeaderAction);


        // ===== SETTING HOME =====
        tvTitle.setText("Pilihan terbaik untukmu");

        btnAction.setText("Login");
        btnAction.setOnClickListener(v -> showLoginDialog());

        rvBest = view.findViewById(R.id.rvBest);
        rvRandom = view.findViewById(R.id.rvRandom);
        rvEditor = view.findViewById(R.id.rvEditor);

        setupRecycler(rvBest);
        setupRecycler(rvRandom);
        setupRecycler(rvEditor);


    }

    private void showLoginDialog() {
        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.dialog_login);
        dialog.setCancelable(true);

        EditText etUsername = dialog.findViewById(R.id.etUsername);
        EditText etPassword = dialog.findViewById(R.id.etPassword);
        Button btnLogin = dialog.findViewById(R.id.btnLogin);
        TextView tvRegister = dialog.findViewById(R.id.tvRegister);

        btnLogin.setOnClickListener(v -> {
            if (etUsername.getText().toString().isEmpty() ||
                    etPassword.getText().toString().isEmpty()) {

                Toast.makeText(getContext(),
                        "Username dan Password wajib diisi",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(),
                        "Login berhasil",
                        Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        tvRegister.setOnClickListener(v -> {
            dialog.dismiss();
            startActivity(new Intent(getActivity(), RegisterActivity.class));
        });

        dialog.show();
    }

    private void setupRecycler(RecyclerView rv) {
        rv.setLayoutManager(
                new LinearLayoutManager(getContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false)
        );

        List<Integer> dummyImages = Arrays.asList(
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background
        );

        rv.setAdapter(new StoryAdapter(getContext(), dummyImages));
    }
}
