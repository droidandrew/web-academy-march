package com.webacademy.march;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NavActivity extends Activity {
    public static final String TAG = "{NavActivity}";
    EditText editText;
    TestService.MyBinder mBinder;
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (TestService.MyBinder) service;
            mBinder.setOnMyServiceListener(new TestService.OnMyServiceListener() {
                @Override
                public void onUpdate(final int counter) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mButtonStartService.setText("Counter = " + counter);
                            mButtonStartService.invalidate();
                        }
                    });

                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };
    Button mButtonStartService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        mButtonStartService = (Button) findViewById(R.id.button2);
        mButtonStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(getServiceIntent());
                bindService(getServiceIntent(), connection, Service.BIND_AUTO_CREATE);
            }
        });

        editText = (EditText) findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "New text =  " + s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            int color = getResources().getColor(R.color.red_color);
            mButtonStartService.setTextColor(color);
            String strFromRes = getString(R.string.app_name);
            mButtonStartService.setText(R.string.app_name);
            mButtonStartService.setBackgroundResource(R.drawable.button1);
        } else {
            int color = getResources().getColor(R.color.yellow_color);
            mButtonStartService.setTextColor(color);
            String strFromRes = getString(R.string.start_service);
            mButtonStartService.setText(strFromRes);
            mButtonStartService.setBackgroundResource(R.drawable.morda);
        }


    }

    private Intent getServiceIntent() {
        return new Intent(NavActivity.this, TestService.class);
    }
}
