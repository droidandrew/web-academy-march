package com.webacademy.march.app.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.webacademy.march.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ServiceControllerActivity extends ActionBarActivity {

    @InjectView(R.id.message)
    TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_controller);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.start)
    protected void onStartClick(View v) {

    }

    @OnClick(R.id.bind)
    protected void onBindClick(View v) {

    }

    @OnClick(R.id.unbind)
    protected void onUnbindClick(View v) {

    }

    @OnClick(R.id.stop)
    protected void onStopClick(View v) {

    }


}
