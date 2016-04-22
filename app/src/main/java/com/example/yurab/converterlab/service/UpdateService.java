package com.example.yurab.converterlab.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.activeandroid.query.Select;
import com.example.yurab.converterlab.Api.FinanceApi;
import com.example.yurab.converterlab.model.CurrencyOrg;
import com.example.yurab.converterlab.model.Organization;
import com.example.yurab.converterlab.model.PublicCurrency;
import com.example.yurab.converterlab.model.PublicOrganization;

import java.util.HashMap;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Yura Breza
 * Date  20.04.2016.
 */
public class UpdateService extends Service {


    private static final String RESOURCE_FINANCE_UA = "http://resources.finance.ua/ru/public/";
    private static final String TAG = "UpdateService";

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RESOURCE_FINANCE_UA)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FinanceApi financeApi = retrofit.create(FinanceApi.class);

        Call<PublicCurrency> call = financeApi.load();

        call.enqueue(new Callback<PublicCurrency>() {
            @Override
            public void onResponse(Response<PublicCurrency> response) {
                Log.d(TAG, "onResponse: " + response.body().getOrganizations().get(0).getTitle().toString());
                Log.d(TAG, "onResponse: " + response.body().getOrganizations().get(0).getAddress().toString());

                writeDB(response.body());
                //Log.d(TAG, "getAll: " + getAll().get(2).getTitle().toString());

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    private void writeDB(PublicCurrency body) {
        for (PublicOrganization pOrganization : body.getOrganizations()) {
            // PublicOrganization pOrganization = body.getOrganizations().get(0);
            Organization o = new Organization();
            o.setAddress(pOrganization.getAddress());
            o.setPhone(pOrganization.getPhone());
            o.setIdString(pOrganization.getId());
            o.setTitle(pOrganization.getTitle());
            o.setLink(pOrganization.getLink());


            o.setCity(body.getCities().get(pOrganization.getCityId()));
            o.setOrganizationType(body.getOrgTypes().get(pOrganization.getOrgType()));
            o.setRegion(body.getRegions().get(pOrganization.getRegionId()));
            o.save();
            Log.d(TAG, "writeDB: " + o.getTitle().toString());
            HashMap<String,HashMap<String,String>> hMap = pOrganization.getCurrencies();
            for(String s :hMap.keySet()){
                HashMap<String,String> hashMap = hMap.get(s);
                CurrencyOrg cOrg = new CurrencyOrg();
                cOrg.setAsk(hashMap.get("ask"));
                Log.d(TAG, "setAsk: " +cOrg.getAsk());
                cOrg.setBid(hashMap.get("bid"));
                Log.d(TAG, "setBid: " +cOrg.getAsk());
                cOrg.setOrganizationId(pOrganization.getId());
                Log.d(TAG, "setOrganizationId: " +cOrg.getOrganizationId().toString());
                cOrg.setCurrencyId(s);
                Log.d(TAG, "setCurrencyId: " +cOrg.getCurrencyId().toString());
                cOrg.setCurrencyName(body.getCurrencies().get(s));
                Log.d(TAG, "setCurrencyName: " + cOrg.getCurrencyName().toString());
                cOrg.save();
            }

        }
    }

    public static List<Organization> getAll() {
        return new Select().from(Organization.class)
                .executeSingle();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
