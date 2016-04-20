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
//        com.activeandroid.Configuration.Builder config = new com.activeandroid.Configuration.Builder(this);
//        config.addModelClasses(City.class,
//                Currency.class, Organization.class, OrganizationCurrency.class,
//                OrganizationType.class, Region.class);
        ActiveAndroid.initialize(this);
    }
}
