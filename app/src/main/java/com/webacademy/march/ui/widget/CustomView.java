package com.webacademy.march.ui.widget;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webacademy.march.R;

/**
 * Created by student on 4/5/15.
 */
public class CustomView extends LinearLayout {

    TextView tittle;
    ImageView icon;
    ObjectAnimator animator;

    public CustomView(Context context) {
        super(context);
        init();

    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.custom_view, this);
        tittle = (TextView) findViewById(R.id.title);
        icon = (ImageView) findViewById(R.id.icon);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showAnim();
            }
        });
    }

    public void setTittle(CharSequence tittleStr) {
        this.tittle.setText(tittleStr);
    }

    public void setIcon(@DrawableRes int resId) {
        this.icon.setImageResource(resId);
    }

    public void showAnim() {
        if (animator != null && animator.isRunning()) {
            return;
        }
        animator = ObjectAnimator.ofFloat(icon, View.ALPHA, 0, 1, 0);
        animator.setInterpolator(new AnticipateInterpolator());
        animator.setDuration(500);
        animator.setRepeatCount(100);
        animator.start();
    }
}
