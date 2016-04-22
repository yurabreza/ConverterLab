package com.example.yurab.converterlab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.activeandroid.query.Select;
import com.example.yurab.converterlab.fragments.OrgzListFragment;
import com.example.yurab.converterlab.model.CurrencyOrg;
import com.example.yurab.converterlab.model.Organization;

import java.util.List;

public class MainActivity extends AppCompatActivity {

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


    public List<Organization> getOrgz() {
        List<Organization> list = new Select()
                .from(Organization.class)
                .execute();
        return list;

    }

    public List<CurrencyOrg> getCurrz(String id) {
        List<CurrencyOrg> list = new Select()
                .from(CurrencyOrg.class)
                .where("OrganizationId = ?", id)
                .execute();
        return list;

    }
}
