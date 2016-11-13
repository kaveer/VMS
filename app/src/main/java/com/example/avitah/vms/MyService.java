package com.example.avitah.vms;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by kaveer on 11/10/2016.
 */

public class MyService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        DefaultNotification();

        Toast.makeText(this, "Welcome to VMS", Toast.LENGTH_LONG).show();
        return START_STICKY;

        //return super.onStartCommand(intent, flags, startId);
    }

    public void DefaultNotification(){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.ic_logo_new)
                        .setContentTitle("VMS Notification")
                        .setContentText("VMS is running in background process");

        NotificationManager mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }

    @Override
    public void onDestroy() {
        // STOP YOUR TASKS
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
