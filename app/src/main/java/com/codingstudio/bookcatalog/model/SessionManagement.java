package com.codingstudio.bookcatalog.model;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {
    private static final String data_Name = "user_session";
    private static final String key_Name = "nama";

    private static final String key_Email = "email";
    private static final String key_noPhone = "phone";
    private static final String key_Password = "password";
    private static final String key_Login ="isLogin";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(data_Name,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setRegister(String nama,String email,String phone,String password){
        editor.putString(key_Name,nama);
        editor.putString(key_Email,email);
        editor.putString(key_noPhone,phone);
        editor.putString(key_Password,password);
        editor.putBoolean(key_Login,false);
        editor.commit();
    }

    public boolean setLogin(String email,String password) {
        String getEmail = sharedPreferences.getString(key_Email,null);
        String getPassword = sharedPreferences.getString(key_Password,null);

        if(email.equals(getEmail) && password.equals(getPassword)){
            editor.putBoolean(key_Login,true);
            editor.commit();
            return true;
        }
        return false;
    }

    public boolean isExistUser(){
        return sharedPreferences.contains(key_Email);
    }
    public boolean isLogin(){
        return sharedPreferences.getBoolean(key_Login,false);
    }
    public void logout(){
        editor.clear();
        editor.commit();
    }
}
