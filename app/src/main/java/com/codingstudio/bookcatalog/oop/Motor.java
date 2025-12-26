package com.codingstudio.bookcatalog.oop;

public class Motor {
    private int speed;
    public int getSpeed(){
        return speed;
    }
    public void  setSpeed(int value){
        if (value > 0){
            speed += value;
        }
    }
}
