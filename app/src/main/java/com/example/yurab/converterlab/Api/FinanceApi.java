package com.example.yurab.converterlab.api;

import com.example.yurab.converterlab.model.PublicCurrency;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Yura Breza
 * Date  20.04.2016.
 */
public interface FinanceApi {
    @GET("currency-cash.json")
     Call<PublicCurrency> load();
}
