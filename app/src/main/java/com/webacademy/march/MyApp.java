package com.webacademy.march;

import android.app.Application;
import android.util.Log;

import com.webacademy.march.api.client.API;
import com.webacademy.march.api.model.Exchange;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by student on 3/28/15.
 */
public class MyApp extends Application {

    private static final String TAG = "{MyApp}";
    OnServiceHelperListener onServiceHelperListener;

    public void setOnServiceHelperListener(OnServiceHelperListener onServiceHelperListener) {
        this.onServiceHelperListener = onServiceHelperListener;
    }

    public void onUpdate(int counter) {
        if (onServiceHelperListener != null) {
            onServiceHelperListener.onDataUpdate(counter);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();


        API.get().localExchange("", "", 5, new Callback<List<Exchange>>() {
            @Override
            public void success(List<Exchange> exchanges, Response response) {
                Log.d(TAG, "exchanges = " + exchanges);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });


    }

    public interface OnServiceHelperListener {
        public void onDataUpdate(int counter);
    }
}
