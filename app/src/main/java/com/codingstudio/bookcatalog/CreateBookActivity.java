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


public class CreateBookActivity extends AppCompatActivity {
    public static final String MODE = "mode";
    public static final String MODE_CREATE = "create";
    public static final String MODE_EDIT = "edit";

    public static final String STATUS = "status";
    public static final String STATUS_DRAFT = "draft";
    public static final String STATUS_PUBLISHED = "published";

    ImageView imgCover;
    EditText etTitle, etAuthor, etCategory, etDescription;
    Button btnPickImage, btnSaveDraft, btnPublish;


    boolean isEditMode = false;
    String bookStatus = STATUS_DRAFT;

    static final int REQ_GALLERY = 1;
    static final int REQ_CAMERA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_book);

        imgCover = findViewById(R.id.imgCover);
        etTitle = findViewById(R.id.etTitle);
        etAuthor = findViewById(R.id.etAuthor);
        etCategory = findViewById(R.id.etCategory);
        etDescription = findViewById(R.id.etDescription);
        btnPickImage = findViewById(R.id.btnPickImage);
        btnSaveDraft = findViewById(R.id.btnSaveDraft);
        btnPublish = findViewById(R.id.btnPublish);
        String mode = getIntent().getStringExtra(MODE);
        isEditMode = MODE_EDIT.equals(mode);
        bookStatus = STATUS_DRAFT;

        if (isEditMode) {
            setTitle("Edit Buku");

            // EDIT MODE
            btnSaveDraft.setVisibility(View.GONE);
            btnPublish.setVisibility(View.VISIBLE);
            btnPublish.setText("Update / Publish");

            loadBookData();
        } else {
            setTitle("Buat Buku Baru");

            // CREATE MODE
            btnSaveDraft.setVisibility(View.VISIBLE);
            btnPublish.setVisibility(View.GONE);
        }



        btnPickImage.setOnClickListener(v -> showImagePicker());
        btnPublish.setOnClickListener(v -> publishBook());

    }

    private void loadBookData() {
        // DUMMY DATA (nanti ganti dari API / DB)
        etTitle.setText("EDUKASI atau SENSASI?");
        etAuthor.setText("Anomalee22");
        etCategory.setText("Roman");
        etDescription.setText("Keadaan televisi zaman sekarang...");
        imgCover.setImageResource(R.drawable.ic_launcher_background);
    }

    private void showImagePicker() {
        String[] options = {"Gallery", "Camera"};

        new AlertDialog.Builder(this)
                .setTitle("Pilih Cover Buku")
                .setItems(options, (dialog, which) -> {
                    if (which == 0) openGallery();
                    else openCamera();
                })
                .show();
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQ_GALLERY);
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQ_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQ_GALLERY && data != null) {
                Uri imageUri = data.getData();
                imgCover.setImageURI(imageUri);
            }
            else if (requestCode == REQ_CAMERA && data != null) {
                imgCover.setImageBitmap((android.graphics.Bitmap) data.getExtras().get("data"));
            }
        }
    }

    private void publishBook() {
        if (etTitle.getText().toString().isEmpty()) {
            Toast.makeText(this, "Judul wajib diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isEditMode) {
            bookStatus = STATUS_DRAFT;
            Toast.makeText(this, "Draft buku disimpan", Toast.LENGTH_SHORT).show();
        } else {
            if (bookStatus.equals(STATUS_DRAFT)) {
                Toast.makeText(this, "Draft buku diperbarui", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Buku berhasil diperbarui", Toast.LENGTH_SHORT).show();
            }
        }

        finish();
    }

}
