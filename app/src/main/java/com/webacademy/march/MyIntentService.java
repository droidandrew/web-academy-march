package com.webacademy.march;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

public class MyIntentService extends IntentService {


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        for (int i = 0; i < 1000; i++) {

            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ((MyApp) getApplication()).onUpdate(i);

        }

    }


}
