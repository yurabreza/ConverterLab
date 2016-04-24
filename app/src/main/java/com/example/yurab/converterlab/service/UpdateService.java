package com.example.yurab.converterlab.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.yurab.converterlab.R;
import com.example.yurab.converterlab.api.FinanceApi;
import com.example.yurab.converterlab.database.DBHelper;
import com.example.yurab.converterlab.model.CurrencyOrg;
import com.example.yurab.converterlab.model.Organization;
import com.example.yurab.converterlab.model.PublicCurrency;

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
    private Retrofit retrofit;
    private FinanceApi financeApi;
    private Call<PublicCurrency> call;
    public List<CurrencyOrg> currencyOrgList;
    public List<Organization> organizationList;


    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);


        retrofit = new Retrofit.Builder()
                .baseUrl(RESOURCE_FINANCE_UA)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        financeApi = retrofit.create(FinanceApi.class);

        call = financeApi.load();
        updateDb();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void updateDb() {

        showNotification();
        call.enqueue(new Callback<PublicCurrency>() {
            @Override
            public void onResponse(Response<PublicCurrency> response) {
                Log.d(TAG, "onResponse: " + response.body().getOrganizations().get(0).getTitle().toString());
                Log.d(TAG, "onResponse: " + response.body().getOrganizations().get(0).getAddress().toString());
                DBHelper dbHelper = new DBHelper();

                dbHelper.writeDB(response.body(), new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        stop();
                        return false;
                    }
                });


            }


            @Override
            public void onFailure(Throwable t) {

            }
        });


    }

    private void stop() {
        stopForeground(true);
        stopSelf();
    }

    private void showNotification() {

        Notification status = buildNotif();
        status.icon = R.drawable.ic_red_arrow_down;
        status.tickerText = "Downloading data";


        startForeground(101, status);


    }

    private Notification buildNotif() {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            return new Notification.Builder(this).getNotification();
        } else {
            return new Notification.Builder(this).build();
        }

    }


}
