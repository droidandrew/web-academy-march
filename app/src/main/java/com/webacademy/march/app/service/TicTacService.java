package com.webacademy.march.app.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;

public class TicTacService extends IntentService {

    boolean isRun = false;
    OnServiceChangeListener onServiceChangeListener;

    public TicTacService() {
        super("TicTacService");
    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, TicTacService.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int counter = 0;
        isRun = true;
        while (isRun) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            doSomething(counter++);
        }
        stopSelf();
    }

    private void doSomething(int i) {
        if (onServiceChangeListener != null) {
            onServiceChangeListener.onCounterChanged(i);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public interface OnServiceChangeListener {
        void onCounterChanged(int counter);
    }

    class MyBinder extends Binder {

        public void setOnServiceChangeListener(OnServiceChangeListener onServiceChangeListener) {
            TicTacService.this.onServiceChangeListener = onServiceChangeListener;
        }

        public void stopThisService() {
            isRun = false;
        }

    }

}
