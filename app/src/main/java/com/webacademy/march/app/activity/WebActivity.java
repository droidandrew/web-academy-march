package com.webacademy.march.app.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.webacademy.march.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class WebActivity extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("http://google.com:80");


    }

}
