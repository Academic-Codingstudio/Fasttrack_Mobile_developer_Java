package com.codingstudio.bookcatalog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.codingstudio.bookcatalog.model.BookRepository;
import com.codingstudio.bookcatalog.model.tipeData.Book;


public class CreateBookActivity extends AppCompatActivity {
    public static final String MODE = "mode";
    public static final String MODE_CREATE = "create";
    public static final String MODE_EDIT = "edit";

    public static final String STATUS_DRAFT = "draft";
    public static final String STATUS_PUBLISHED = "published";


    ImageView imgCover;
    EditText etTitle, etAuthor, etCategory, etDescription;
    Button btnPickImage, btnSaveDraft, btnPublish;



    boolean isEditMode = false;
    String bookStatus = STATUS_DRAFT;
    String bookId;
    Uri selectedImageUri;
    static final int REQ_GALLERY = 1;
    static final int REQ_CAMERA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_book);

        initView();
        initMode();
        initAction();

    }
    // ================= INIT =================
    private void initView() {
        imgCover = findViewById(R.id.imgCover);
        etTitle = findViewById(R.id.etTitle);
        etAuthor = findViewById(R.id.etAuthor);
        etCategory = findViewById(R.id.etCategory);
        etDescription = findViewById(R.id.etDescription);
        btnPickImage = findViewById(R.id.btnPickImage);
        btnSaveDraft = findViewById(R.id.btnSaveDraft);
        btnPublish = findViewById(R.id.btnPublish);
    }

    private void initMode() {
        String mode = getIntent().getStringExtra(MODE);
        isEditMode = MODE_EDIT.equals(mode);

        if (isEditMode) {
            setTitle("Edit Buku");
            bookId = getIntent().getStringExtra("BOOK_ID");

            btnSaveDraft.setVisibility(View.GONE);
            btnPublish.setVisibility(View.VISIBLE);
            btnPublish.setText("Update Buku");

            loadBookData();
        } else {
            setTitle("Buat Buku Baru");
            btnSaveDraft.setVisibility(View.VISIBLE);
            btnPublish.setVisibility(View.GONE);
        }
    }
    private void initAction() {
        btnPickImage.setOnClickListener(v -> showImagePicker());
        btnSaveDraft.setOnClickListener(v -> saveBook(STATUS_DRAFT));
        btnPublish.setOnClickListener(v -> saveBook(STATUS_PUBLISHED));
    }


    // ================= LOAD =================
    private void loadBookData() {
        Book book = BookRepository.getById(bookId);
        if (book == null) return;

        etTitle.setText(book.title);
        etAuthor.setText(book.author);
        etCategory.setText(book.category);
        etDescription.setText(book.description);

        if (!book.imageUri.isEmpty()) {
            selectedImageUri = Uri.parse(book.imageUri);
            imgCover.setImageURI(selectedImageUri);
        }

        bookStatus = book.status;
    }


    private void showImagePicker() {
        String[] options = {"Gallery", "Camera"};
        new AlertDialog.Builder(this)
                .setTitle("Pilih Cover Buku")
                .setItems(options, (d, i) -> {
                    if (i == 0) openGallery();
                    else openCamera();
                }).show();
    }

    private void openGallery() {
        Intent i = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, REQ_GALLERY);
    }

    private void openCamera() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, REQ_CAMERA);
    }
    @Override
    protected void onActivityResult(int req, int res, Intent data) {
        super.onActivityResult(req, res, data);

        if (res == RESULT_OK && data != null) {
            if (req == REQ_GALLERY) {
                selectedImageUri = data.getData();
                imgCover.setImageURI(selectedImageUri);
            }
            if (req == REQ_CAMERA) {
                imgCover.setImageBitmap((android.graphics.Bitmap)
                        data.getExtras().get("data"));
            }
        }
    }

    // ================= SAVE =================
    private void saveBook(String status) {
        if (etTitle.getText().toString().isEmpty()) {
            toast("Judul wajib diisi");
            return;
        }

        String id = isEditMode ? bookId :
                String.valueOf(System.currentTimeMillis());

        Book book = new Book(
                id,
                etTitle.getText().toString(),
                etAuthor.getText().toString(),
                etCategory.getText().toString(),
                etDescription.getText().toString(),
                selectedImageUri != null ? selectedImageUri.toString() : "",
                status
        );

        if (isEditMode) {
            BookRepository.update(book);
            toast("Buku diperbarui");
        } else {
            BookRepository.add(book);
            toast("Buku disimpan sebagai " + status);
        }

        finish();
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
