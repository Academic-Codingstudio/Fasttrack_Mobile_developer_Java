package com.codingstudio.bookcatalog.viewFregment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codingstudio.bookcatalog.CreateBookActivity;
import com.codingstudio.bookcatalog.R;
import com.codingstudio.bookcatalog.asset.WriterStoryAdapter;
import com.codingstudio.bookcatalog.model.WriterBook;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class WriterDashboardFragment extends Fragment {

    public WriterDashboardFragment() {
        super(R.layout.fragment_writer_dashboard);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        RecyclerView rv = view.findViewById(R.id.rvWriterBooks);
        FloatingActionButton fab = view.findViewById(R.id.fabAddBook);

        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Dummy data (sesuai struktur lama kamu)
        List<WriterBook> books = new ArrayList<>();
        books.add(new WriterBook(R.drawable.ic_launcher_background, "Roman", true));
        books.add(new WriterBook(R.drawable.ic_launcher_background, "Horror", false));
        books.add(new WriterBook(R.drawable.ic_launcher_background, "Drama", true));

        rv.setAdapter(new WriterStoryAdapter(getContext(), books));

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CreateBookActivity.class);
            intent.putExtra(CreateBookActivity.MODE, CreateBookActivity.MODE_CREATE);
            startActivity(intent);
        });

    }
}
