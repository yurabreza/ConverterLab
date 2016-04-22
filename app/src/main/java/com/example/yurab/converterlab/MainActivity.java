package com.example.yurab.converterlab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.activeandroid.query.Select;
import com.example.yurab.converterlab.fragments.OrgzListFragment;
import com.example.yurab.converterlab.model.CurrencyOrg;
import com.example.yurab.converterlab.model.Organization;
import com.example.yurab.converterlab.service.UpdateService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "";

    private  OrgzListFragment orgzListFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//       deleteList();
//       getData();
        orgzListFragment = new OrgzListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity,orgzListFragment)
                .commit();


    }

    private void deleteList() {
        int x =getOrgz().size();
        int y =getCurrzAll().size();
        for (int i =0;i<x;i++)
            CurrencyOrg.delete(CurrencyOrg.class,i);

        for (int i =0;i<y;i++)
            Organization.delete(Organization.class,i);
    }

    public void getData() {
        startService(new Intent(this, UpdateService.class));
    }

    public List<Organization> getOrgz() {
        List<Organization> list = new Select()
                .from(Organization.class)
                .execute();
        Log.d(TAG, "getOrgz: " +list.get(0).getTitle().toString());
        return list;

    }
    public List<CurrencyOrg> getCurrzAll() {
        List<CurrencyOrg> list = new Select()
                .from(CurrencyOrg.class)
                .execute();
        Log.d(TAG, "getCurrz: ");
        return list;

    }

    public List<CurrencyOrg> getCurrz(String id) {
        List<CurrencyOrg> list = new Select()
                .from(CurrencyOrg.class)
                .where("OrganizationId = ?", id)
                .execute();
        Log.d(TAG, "getCurrz: ");
        return list;

    }

}
