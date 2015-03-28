package com.webacademy.march;

import android.app.Application;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by student on 3/28/15.
 */
public class MyApp extends Application {

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

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(new Runnable() {
            @Override
            public void run() {

            }
        }, 1, TimeUnit.SECONDS);

        executorService.shutdown();


    }

    public interface OnServiceHelperListener {
        public void onDataUpdate(int counter);
    }
}
