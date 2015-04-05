package com.webacademy.march.app.activity;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.webacademy.march.R;
import com.webacademy.march.ui.widget.CustomView;

public class CustomViewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

        CustomView customView = (CustomView) findViewById(R.id.custom);
        customView.setIcon(R.drawable.rect_normal);
        customView.setTittle("Это работает!");

    }


}
