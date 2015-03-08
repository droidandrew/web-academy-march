package com.webacadwmy.march;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NavActivity extends Activity {
    public static final String TAG = "{NavActivity}";
    EditText editText;
    TestService.MyBinder mBinder;
    Button mButtonStartService;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        mButtonStartService = (Button) findViewById(R.id.button2);
        mButtonStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startService(getServiceIntent());
                 bindService(getServiceIntent() , connection , Service.BIND_AUTO_CREATE);
            }
        });
    }

    private Intent getServiceIntent(){
       return new Intent(NavActivity.this , TestService.class);
    }
}
