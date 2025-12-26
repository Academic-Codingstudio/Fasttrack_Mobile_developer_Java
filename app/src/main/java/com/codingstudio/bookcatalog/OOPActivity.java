package com.codingstudio.bookcatalog;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.codingstudio.bookcatalog.oop.Car;
import com.codingstudio.bookcatalog.oop.Honda;
import com.codingstudio.bookcatalog.oop.Mitsubisi;
import com.codingstudio.bookcatalog.oop.Mobil;
import com.codingstudio.bookcatalog.oop.Motor;
import com.codingstudio.bookcatalog.oop.Vehicle;

public class OOPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_oop);

        // park 1 class object property method dan constructor
        Car myCar = new Car("Mitsubisi","Biru");
        Log.d("Park 1","Class Object property method and constructor");
        Log.d("Park 1",myCar.brand); // myCar.brand memanggil object dari dalam class
        Log.d("Park 1 ","Speed : " + myCar.getSpeed());
        // park 2 Enkapsulation
        Log.d("Park 2 " , "Enkapsulation");
        Motor myMotor = new Motor();
        myMotor.setSpeed(60);
        Log.d("Park 2","Speed : " + myMotor.getSpeed());
        // park 3 Inheritance
        Log.d("Park 3" ,"Inheritance");
        Mitsubisi car = new Mitsubisi();
        Log.d("Park 3" , "Get Message : " + car.move());
        // park 4 Polymorphism
        Log.d("Park 4", "Polymorphism");
        Car kendaran = new Vehicle();
        Log.d("Park 4","Get Message" + kendaran.move());
        Log.d("Park 5", "Abstraction");
        Mobil honda = new Honda();
        Log.d("Park 5 ", "Get Message: " + honda.move());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}