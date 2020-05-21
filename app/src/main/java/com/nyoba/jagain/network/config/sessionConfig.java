package com.nyoba.jagain.network.config;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.nyoba.jagain.LoginActivity;
import com.nyoba.jagain.MainActivity;

import java.util.HashMap;

public class sessionConfig {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String NAME = "NAME";
    public static final String EMAIL = "EMAIL";
    public static final String TMPTLAHIR = "TMPT_LAHIR";
    public static final String TGLTLAHIR = "TGL_LAHIR";
    public static final String PASSWORD = "PASSWORD";
    public static final String ALAMAT = "ALAMAT";
    public static final String JENKEL = "JENKEL";
    public static final String GOLDAR = "GOLDAR";
    public static final String ID = "ID";

    public sessionConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String name, String email, String alamat, String tmptlhr, String tgllhr, String jenkel, String goldar , String id){

        editor.putBoolean(LOGIN, true);
        editor.putString(NAME, name);
        editor.putString(EMAIL, email);
        editor.putString(ALAMAT, alamat);
        editor.putString(TMPTLAHIR, tmptlhr);
        editor.putString(TGLTLAHIR, tgllhr);
        editor.putString(JENKEL, jenkel);
        editor.putString(GOLDAR, goldar);
        editor.putString(ID, id);
        editor.apply();

    }


    public boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin(){

        if (!this.isLoggin()){
            Intent i = new Intent(context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }else {
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    public HashMap<String, String> getUserDetail(){

        HashMap<String, String> user = new HashMap<>();
        user.put(NAME, sharedPreferences.getString(NAME, null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        user.put(ALAMAT, sharedPreferences.getString(ALAMAT, "-"));
        user.put(TMPTLAHIR, sharedPreferences.getString(TMPTLAHIR, "-"));
        user.put(TGLTLAHIR, sharedPreferences.getString(TGLTLAHIR, "-"));
        user.put(JENKEL, sharedPreferences.getString(JENKEL, "-"));
        user.put(GOLDAR, sharedPreferences.getString(GOLDAR, "-"));
        user.put(ID, sharedPreferences.getString(ID, null));

        return user;
    }

    public void logout(){

        editor.clear();
        editor.commit();
        Intent i = new Intent(context, LoginActivity.class);
        context.startActivity(i);
        ((MainActivity) context).finish();

    }

}
