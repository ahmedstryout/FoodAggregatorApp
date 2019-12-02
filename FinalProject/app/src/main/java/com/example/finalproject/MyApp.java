package com.example.finalproject;

import android.app.Application;

import org.androidannotations.annotations.EApplication;

import io.realm.Realm;

@EApplication
public class MyApp extends Application {
    public void onCreate(){
        super.onCreate();

        //initialize realm and stuff
        Realm.init(this);
    }
}
