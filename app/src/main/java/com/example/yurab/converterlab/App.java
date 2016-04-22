package com.example.yurab.converterlab;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by Yura Breza
 * Date  20.04.2016.
 */
public class App  extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        ActiveAndroid.initialize(this);
    }
}
