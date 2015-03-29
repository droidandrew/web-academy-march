package com.webacademy.march.api.client;

import com.webacademy.march.api.model.Exchange;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by collos on 29.03.15.
 */
public class API {

    private static PrivatExchange privatExchange;

    private API() {
    }

    private static API getInstance() {
        API api = new API();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.privatbank.ua")
                .build();

        api.privatExchange = restAdapter.create(PrivatExchange.class);
        return api;
    }

    public static PrivatExchange get() {
        if (privatExchange == null) {
            return getInstance().privatExchange;
        }
        return privatExchange;
    }

    public interface PrivatExchange {
        @GET("/p24api/pubinfo")
        public List<Exchange> localExchange(@Query("json") String typeJson, @Query("exchange") String ex, @Query("coursid") int coursid);

        @GET("/p24api/pubinfo")
        public void localExchange(@Query("json") String typeJson, @Query("exchange") String ex, @Query("coursid") int coursid, Callback<List<Exchange>> callback);
    }


}
