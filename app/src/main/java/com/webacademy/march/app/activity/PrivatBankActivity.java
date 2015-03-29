package com.webacademy.march.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.webacademy.march.R;
import com.webacademy.march.api.model.Exchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class PrivatBankActivity extends Activity {

    TextView textView;
    private String JSON =
            "{\n" +
                    "\"ccy\":\"RUR\",\n" +
                    "\"base_ccy\":\"UAH\",\n" +
                    "\"buy\":\"0.28000\",\n" +
                    "\"sale\":\"0.32000\"\n" +
                    "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privat_bank);

        textView = (TextView) findViewById(R.id.text);

        Gson gson = new Gson();

        Exchange exchanges = gson.fromJson(JSON, Exchange.class);

        String json = gson.toJson(exchanges);

        Log.d("GSON", "exchanges = " + json);

        textView.setText(json);
    }

    private void update() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL urlGoogle = new URL("http://google.com:80");
                    HttpURLConnection connection = (HttpURLConnection) urlGoogle.openConnection();
                    InputStream is = connection.getInputStream();
                    InputStreamReader isw = new InputStreamReader(is);

                    final StringBuffer stringBuffer = new StringBuffer();
                    int data = isw.read();
                    while (data != -1) {
                        char current = (char) data;
                        data = isw.read();
                        stringBuffer.append(current);
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(stringBuffer.toString());
                        }
                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });


        thread.start();

    }

}
