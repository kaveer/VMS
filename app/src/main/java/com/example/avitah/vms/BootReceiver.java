package com.example.avitah.vms;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by kaveer on 11/10/2016.
 */

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast messageBox = Toast.makeText(context , "Notification from VMS reciver" , Toast.LENGTH_LONG);
        messageBox.show();
    }


}
