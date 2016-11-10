package com.example.avitah.vms;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by kaveer on 11/10/2016.
 */

public class MyService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){



        Toast.makeText(this, "Service Started of vms", Toast.LENGTH_LONG).show();
        return START_STICKY;

        //return super.onStartCommand(intent, flags, startId);
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
