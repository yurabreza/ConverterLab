package com.example.yurab.converterlab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.yurab.converterlab.fragments.OrgzListFragment;

public final class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity, new OrgzListFragment())
                .commit();
    }
}
