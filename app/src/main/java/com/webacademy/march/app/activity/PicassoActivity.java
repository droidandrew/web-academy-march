package com.webacademy.march.app.activity;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.picasso.Picasso;
import com.webacademy.march.R;

public class PicassoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);
        Picasso
                .with(this)
                .load("http://img.desktopwallpapers.ru/flowers/pics/wide/" +
                        "1920x1080/ffd52215b64fbeae70a507c043965396.jpg")
                .into((android.widget.ImageView) findViewById(R.id.imageView));

    }


}
