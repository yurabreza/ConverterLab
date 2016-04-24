package com.example.yurab.converterlab;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.yurab.converterlab.database.DBHelper;
import com.example.yurab.converterlab.fragments.OrgzListFragment;
import com.example.yurab.converterlab.model.CurrencyOrg;
import com.example.yurab.converterlab.service.UpdateService;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "";

    private OrgzListFragment orgzListFragment;
    private boolean bound = false;
    private UpdateService updateService;
    private Intent intent;
    private ServiceConnection sConn;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper();

        orgzListFragment = new OrgzListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity, orgzListFragment)
                .commit();
        getData();

    }


    private void deleteList() {
       // int x = getOrgz().size();
        int y = dbHelper.getCurrzAll().size();
     //    for (int i = 0; i < x; i++)
       //      Organization.delete(CurrencyOrg.class, i);
        Log.d(TAG, "deleteList: ----------------------------------------------"+y);
        for (int i = 0; i < y; i++){

            CurrencyOrg.delete(CurrencyOrg.class, i);

        }
    }

    public void getData() {
        startService(new Intent(this, UpdateService.class));
    }



}
