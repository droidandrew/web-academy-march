package com.webacademy.march.app.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.webacademy.march.R;
import com.webacademy.march.api.client.API;
import com.webacademy.march.api.model.Exchange;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PrivatBankActivity extends ListActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privat_bank);
        textView = (TextView) findViewById(R.id.text);


        API.get().localExchange("", "", 5, new Callback<List<Exchange>>() {
            @Override
            public void success(List<Exchange> exchanges, Response response) {
                if (exchanges != null && !exchanges.isEmpty()) {
                    textView.setText(exchanges.get(0).getCcy());
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

}
