package com.webacademy.march.app.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.webacademy.march.R;

/**
 * Created by student on 4/4/15.
 */
public class NotificationActivity extends Activity {
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        findViewById(R.id.btnNotify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ObjectAnimator animatorX = ObjectAnimator.ofFloat(v, View.SCALE_X, 1, 0.7f, 1);
                ObjectAnimator animatorY = ObjectAnimator.ofFloat(v, View.SCALE_Y, 1, 0.7f, 1);
                ObjectAnimator animatorTY = ObjectAnimator.ofFloat(v, View.TRANSLATION_Y, 0, -100);

                final AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(animatorX, animatorY, animatorTY);
                animatorSet.setInterpolator(new AnticipateOvershootInterpolator());
                animatorSet.setDuration(3000);

                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animatorSet.start();
                    }
                }, 800L);


            }
        });

    }
}
