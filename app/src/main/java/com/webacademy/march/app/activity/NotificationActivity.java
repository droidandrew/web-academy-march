package com.webacademy.march.app.activity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;

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

                NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(getBaseContext());
                notifyBuilder.setSmallIcon(R.mipmap.ic_launcher);
                notifyBuilder.setContentText("Hello Android");
                notifyBuilder.setSubText("Sub");

                Intent intent = new Intent(getBaseContext(), DBActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 1234, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                notifyBuilder.setContentIntent(pendingIntent);

                notifyBuilder.addAction(new NotificationCompat.Action(R.mipmap.ic_launcher, "Cancel", pendingIntent));

                Notification notification = notifyBuilder.build();

                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.notify(id++, notification);
            }
        });

    }
}
