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
import com.codingstudio.bookcatalog.DetailActivity;
import com.codingstudio.bookcatalog.R;
import com.codingstudio.bookcatalog.asset.WriterStoryAdapter;
import com.codingstudio.bookcatalog.model.BookRepository;
import com.codingstudio.bookcatalog.model.tipeData.Book;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class WriterDashboardFragment extends Fragment {

    RecyclerView rv;
    FloatingActionButton fab;

    public WriterDashboardFragment() {
        super(R.layout.fragment_writer_dashboard);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        rv = view.findViewById(R.id.rvWriterBooks);
        fab = view.findViewById(R.id.fabAddBook);

        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));

        loadBooks();

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CreateBookActivity.class);
            intent.putExtra(CreateBookActivity.MODE, CreateBookActivity.MODE_CREATE);
            startActivity(intent);
        });
    }

    private void loadBooks() {
        List<Book> books = BookRepository.getAll();

        WriterStoryAdapter adapter =
                new WriterStoryAdapter(getContext(), books, book -> {
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra("BOOK_ID", book.id);
                    startActivity(intent);
                });

        rv.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        loadBooks(); // refresh setelah create / edit
    }
}
