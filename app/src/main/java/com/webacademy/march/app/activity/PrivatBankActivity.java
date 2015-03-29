package com.webacademy.march.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.webacademy.march.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PrivatBankActivity extends Activity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privat_bank);

        textView = (TextView) findViewById(R.id.text);

        update();
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
