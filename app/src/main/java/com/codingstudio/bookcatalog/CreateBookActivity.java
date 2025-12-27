package com.codingstudio.bookcatalog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class CreateBookActivity extends AppCompatActivity {

    ImageView imgCover;
    EditText etTitle, etAuthor, etCategory, etDescription;
    Button btnPickImage, btnPublish;

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
        btnPublish = findViewById(R.id.btnPublish);

        btnPickImage.setOnClickListener(v -> showImagePicker());
        btnPublish.setOnClickListener(v -> publishBook());
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

        Toast.makeText(this, "Buku berhasil dibuat (dummy)", Toast.LENGTH_SHORT).show();
        finish();
    }
}
