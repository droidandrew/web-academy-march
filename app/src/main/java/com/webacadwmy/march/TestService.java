package com.webacadwmy.march;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class TestService extends Service {

    public static final String TAG = "{TestService}";
    MyBinder binder = new MyBinder();

    public TestService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    int mCounter = 0;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    mCounter = i;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (onMyServiceListener != null) {
                            onMyServiceListener.onUpdate(mCounter);
                        }
                        Log.d(TAG, "Counter = " + i);
                }
            }
        }).start();



        return 0;
    }

    public class MyBinder extends Binder {
        public int getCurrent() {
            return mCounter;
        }

        public void setOnMyServiceListener(OnMyServiceListener listener) {
            onMyServiceListener = listener;
        }
    }

    public interface OnMyServiceListener {
        public void onUpdate(int counter);
    }

    OnMyServiceListener onMyServiceListener;
}
