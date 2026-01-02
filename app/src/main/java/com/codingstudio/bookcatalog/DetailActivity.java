package com.codingstudio.bookcatalog;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.codingstudio.bookcatalog.model.BookRepository;
import com.codingstudio.bookcatalog.model.tipeData.Book;

public class DetailActivity extends AppCompatActivity {

    ImageView imgCover;
    TextView txtTitle, txtAuthor, txtStatus, txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgCover = findViewById(R.id.imgCover);
        txtTitle = findViewById(R.id.txtTitle);
        txtAuthor = findViewById(R.id.txtAuthor);
        txtStatus = findViewById(R.id.txtStatus);
        txtDescription = findViewById(R.id.txtDescription);

        String bookId = getIntent().getStringExtra("BOOK_ID");
        if (bookId == null) {
            finish(); // ⛔ TANPA ID → TUTUP
            return;
        }

        Book book = BookRepository.getById(bookId);
        if (book == null) {
            finish(); // ⛔ DATA TIDAK ADA
            return;
        }

        bindData(book);
    }

    private void bindData(Book book) {
        txtTitle.setText(book.title);
        txtAuthor.setText(book.author);
        txtStatus.setText(book.status.toUpperCase());
        txtDescription.setText(book.description);

        if (book.imagePath != null && !book.imagePath.isEmpty()) {
            imgCover.setImageURI(Uri.parse(book.imagePath));
        } else {
            imgCover.setImageResource(R.drawable.ic_launcher_background);
        }
    }
}