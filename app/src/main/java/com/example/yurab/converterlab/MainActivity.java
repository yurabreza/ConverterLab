package com.example.yurab.converterlab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.example.yurab.converterlab.model.CurrencyOrg;
import com.example.yurab.converterlab.model.Organization;
import com.example.yurab.converterlab.service.UpdateService;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity";
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // startService(new Intent(this, UpdateService.class));
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.btn1):
                startService(new Intent(this, UpdateService.class));
                break;
            case (R.id.btn2):
                //Toast.makeText(this, getOrgz().get(i).getTitle().toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(this, getOrgz().get(i).getTitle().toString(), Toast.LENGTH_SHORT).show();
                List<CurrencyOrg> list = getCurrz();
                for (CurrencyOrg cOrg : list)
                    Log.d(TAG, "onClick: getCurz"+cOrg.getCurrencyId().toString());
                // i++;
                break;
        }
    }

    public List<Organization> getOrgz() {
        List<Organization> list = new Select()
                .from(Organization.class)
                .orderBy("RANDOM()")
                .execute();
        return list;

    }

    public List<CurrencyOrg> getCurrz() {
        List<CurrencyOrg> list = new Select()
                .from(CurrencyOrg.class)
                .orderBy("RANDOM()")
                .execute();
        return list;

    }
}
