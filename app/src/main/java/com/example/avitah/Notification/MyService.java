package com.example.avitah.Notification;

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

import com.example.avitah.DbHandler.DBHandler;
import com.example.avitah.Tables.TableInsurance;
import com.example.avitah.vms.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kaveer on 11/10/2016.
 */

public class MyService extends Service {
    String notifTitle = "Notification Title";
    String notifSubject = "Notification Subject";
    ArrayList<TableInsurance.InsuranceNonStatic> insuranceList;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        String dateNow = GetDateNow();
        String addedDate = AddDateToCurrentDate(GetDateNow(), 3);
        DBHandler DB = new DBHandler(this);

        insuranceList = DB.GetNotificationInsurance(dateNow, addedDate);

        if (insuranceList.size() > 0){
            for (TableInsurance.InsuranceNonStatic insurance:  insuranceList) {
                notifTitle = "Insurance " + insurance.insuranceName;
                notifSubject = insurance.insuranceName + " expires on : " + insurance.expiryDate;
                Notification(notifTitle , notifSubject);
            }
        }

        Toast.makeText(this, "Welcome to VMS in class MS in method OnstartCommand", Toast.LENGTH_LONG).show();
        return START_STICKY;
        //return super.onStartCommand(intent, flags, startId);
    }

    public String GetDateNow(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //import java.text.SimpleDateFormat instead of android.icu.text.simpleDateFormat
        String currentDate = dateFormat.format(calendar.getTime());
        return currentDate;
    }

    public String AddDateToCurrentDate(String dateNow, int dayToAdd){
        String result = "2017-01-03";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(dateNow));
            c.add(Calendar.DATE, dayToAdd);

            sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date resultDate = new Date(c.getTimeInMillis());
            result = sdf.format(resultDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void Notification(String title, String subject){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.ic_logo_new)
                        .setContentTitle(title)
                        .setContentText(subject);

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
