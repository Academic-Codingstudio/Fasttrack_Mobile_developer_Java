package com.codingstudio.bookcatalog.oop;

// ini park 1 berisi
// class
// object
// property
// method
// constructor
public class Car {// ini disebut class dan ini tidak boleh beda dengan nama file
        // Property
        public String brand; // siapa saja boleh mengubah nilainya
        protected String color; // hanya boleh di ubah jika 1 kelas itu sendiri atau paket yang sama atau sub dari class tersbut
        private int speed; // ini boleh di ubah tapi sulit atau butuh izin atau cara tertentu
        final int maxSpeed = 180; // nilai yang tidak akan pernah bisa di ubah

        // Constructor kosong
        public Car() {
            this.speed = 0;
        }

        // Constructor dengan parameter
        public Car(String brand, String color) {
            this.brand = brand;
            this.color = color;
            this.speed = 0;
        }

        // Method
        public void accelerate() {
            if (speed + 10 <= maxSpeed) {
                speed += 10;
            }
        }

        public void brake() {
            speed -= 10;
            if (speed < 0) {
                speed = 0;
            }
        }

        public int getSpeed() {
            return speed;
        }

        public String move() {
            return "Vehicle is Car";
        }

//    Cara manggil jaman dulu
//    Car myCar  = new Car("Mitsubisi","biru");
//    manggil object
//    myCar.brand;
//    manggil method
//    myCar.getSpeed();
}
