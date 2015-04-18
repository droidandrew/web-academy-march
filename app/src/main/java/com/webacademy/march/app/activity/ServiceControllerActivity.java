package com.webacademy.march.app.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.webacademy.march.R;
import com.webacademy.march.TestService;
import com.webacademy.march.app.service.TicTacService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ServiceControllerActivity extends Activity {

    @InjectView(R.id.message)
    TextView tvMessage;
    boolean isBinded;
    TicTacService.MyBinder binder;
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            tvMessage.setText("Connected");

            binder = (TicTacService.MyBinder) service;
            binder.setOnServiceChangeListener(new TicTacService.OnServiceChangeListener() {
                @Override
                public void onCounterChanged(final int counter) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvMessage.setText("Counter = " + counter);
                        }
                    });
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            binder = null;
            tvMessage.setText("Disconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_controller);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.start)
    protected void onStartClick(View v) {
        startService(TicTacService.getStartIntent(v.getContext()));
    }

    @OnClick(R.id.bind)
    protected void onBindClick(View v) {
        if (!isBinded) {
            bindService(TicTacService.getStartIntent(getBaseContext()), connection, 0);
            isBinded = true;
        }
    }

    @OnClick(R.id.unbind)
    protected void onUnbindClick(View v) {
        if (isBinded) {
            unbindService(connection);
            isBinded = false;
        }
    }

    @OnClick(R.id.stop)
    protected void onStopClick(View v) {
        if (binder != null) {
            binder.stopThisService();
        } else {
            stopService(TicTacService.getStartIntent(v.getContext()));
        }
    }


}
