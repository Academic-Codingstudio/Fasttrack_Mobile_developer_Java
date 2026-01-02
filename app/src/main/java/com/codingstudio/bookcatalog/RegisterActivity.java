package com.codingstudio.bookcatalog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.codingstudio.bookcatalog.model.SessionManagement;

public class RegisterActivity extends AppCompatActivity {

    EditText edt_nama_lengkap,edt_email,edt_phone,edt_password,edt_password_confirm;
    Button btn_register;
    String nama_lengkap, email , no_phone, password, password_confirm;
    SessionManagement session;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        // Panggil Data yang mau di pakai
        session = new SessionManagement(this);


        // mencocokan data dengan tampilan
        edt_nama_lengkap = findViewById(R.id.edt_nama_lengkap);
        edt_email = findViewById(R.id.edt_email);
        edt_phone = findViewById(R.id.edt_phone);
        edt_password = findViewById(R.id.edt_password);
        edt_password_confirm = findViewById(R.id.edt_password_confirm);
        btn_register = findViewById(R.id.btn_register);


        btn_register.setOnClickListener(v -> {
            register();
        });
    }


    private void register(){
        // menyimpan sementara nilai yang di ambil dari tampilan
        nama_lengkap = edt_nama_lengkap.getText().toString();
        email = edt_email.getText().toString();
        no_phone = edt_phone.getText().toString();
        password = edt_password.getText().toString();
        password_confirm = edt_password_confirm.getText().toString();

        if(!validationInput(nama_lengkap,email,no_phone,password,password_confirm)) return;

        if(session.isExistUser()){
            toast("User sudah ada");
            return;
        }
        session.setRegister(nama_lengkap,email,no_phone,password);
        toast("Register berhasil");
        startActivity(new Intent(this,DashboardActivity.class));
        finish();
    }

    private boolean validationInput(String nama_lengkap, String email, String no_phone, String password, String password_confirm){
        if (nama_lengkap.isEmpty() || email.isEmpty() || no_phone.isEmpty() || password.isEmpty()){
            toast("Silahkan Periksa kembail data tidak boleh kosong");
            return false;
        }
        if (password.length() < 8 ){
            toast("Password minimal 8 karakter");
            return false;
        }
        if (!password.equals(password_confirm)){
            toast("Password tidak sama");
            return false;
        }
        return true;
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}