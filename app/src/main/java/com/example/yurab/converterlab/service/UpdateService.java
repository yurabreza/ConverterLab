package com.example.yurab.converterlab.service;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.yurab.converterlab.R;
import com.example.yurab.converterlab.api.FinanceApi;
import com.example.yurab.converterlab.constants.Constants;
import com.example.yurab.converterlab.database.DBHelper;
import com.example.yurab.converterlab.model.PublicCurrency;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Yura Breza
 * Date  20.04.2016.
 */
public final class UpdateService extends Service {


    private static final String RESOURCE_FINANCE_UA = "http://resources.finance.ua/ru/public/";
    private static final String TAG = "UpdateService";
    private Retrofit retrofit;
    private FinanceApi financeApi;
    private Call<PublicCurrency> call;


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

        final DBHelper dbHelper = new DBHelper();

        showNotification();
        call.enqueue(new Callback<PublicCurrency>() {
            @Override
            public void onResponse(Response<PublicCurrency> response) {
                //boolean variable upd is true when actual date is newer and there is need to update
                boolean upd = loadedDateNewer(response.body().getDate());
                //saving new date
                saveDate(response.body().getDate());


                dbHelper.writeDB(response.body(), upd, new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        stop();
                        sendMessage();
                        return false;
                    }
                });

            }


            @Override
            public void onFailure(Throwable t) {

            }
        });


    }

    private void saveDate(String date) {
        SharedPreferences sPref = getSharedPreferences(Constants.CONVERT_LAB_S_PREFS, this.MODE_PRIVATE);

        sPref.edit().putString(Constants.SAVE_DATE_KEY, date).apply();


    }


    private boolean loadedDateNewer(String newDate) {
        SharedPreferences sPref = this.getSharedPreferences(Constants.CONVERT_LAB_S_PREFS, Context.MODE_PRIVATE);
        String s = sPref.getString(Constants.SAVE_DATE_KEY, null);
        if (s==null) return false;
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date dateNew = df.parse(newDate);

            Log.d(TAG, "loadedDateNewer: " + dateNew);
            Date dateOld = df.parse(s);
            Log.d(TAG, "loadedDateNewer: " + dateOld);
            if (dateNew.after(dateOld)) {
                Log.d(TAG, "loadedDateNewer: -----------date.after(res)");
                return true;

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "loadedDateNewer: ----------- not (date.after(res))");
        return false;
    }

    private void sendMessage() {
        Log.d("sender", "Broadcasting message");
        Intent intent = new Intent(Constants.BROADCAST_UPDATE_END);


        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
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
            return new Notification.Builder(this)
                    .setSmallIcon(R.drawable.ic_red_arrow_down)
                    .setContentTitle("Converter Lab")
                    .setContentText("Обновление базы данных")
                    .setAutoCancel(false)
                    .setOngoing(true).getNotification();
        } else {
            return new Notification.Builder(this)
                    .setSmallIcon(R.drawable.ic_red_arrow_down)
                    .setContentTitle("Converter Lab")
                    .setContentText("Обновление базы данных")
                    .setAutoCancel(false)
                    .setOngoing(true).build();
        }

    }


}
