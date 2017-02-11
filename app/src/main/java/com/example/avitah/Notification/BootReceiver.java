package com.example.avitah.Notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.avitah.DbHandler.DBHandler;
import com.example.avitah.Tables.TableFitness;
import com.example.avitah.Tables.TableInsurance;
import com.example.avitah.Tables.TableRoadTaxation;
import com.example.avitah.vms.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by kaveer on 11/10/2016.
 */

public class BootReceiver extends BroadcastReceiver {
    String notifTitle = "Notification Title";
    String notifSubject = "Notification Subject";

    ArrayList<TableInsurance.InsuranceNonStatic> insuranceList;
    ArrayList<TableFitness.FitnessNonStatic> fitnessList;
    ArrayList<TableRoadTaxation.RoadTaxationNonStatic> taxList;

    int notificationId =1;
    @Override
    public void onReceive(Context context, Intent intent) {

        String dateNow = GetDateNow();
        String addedDate = AddDateToCurrentDate(GetDateNow(), 3);

        DBHandler DB = new DBHandler(context);

        insuranceList = DB.GetNotificationInsurance(dateNow, addedDate);
        fitnessList = DB.GetNotificationFitness(dateNow, addedDate);
        taxList = DB.GetNotificationTaxation(dateNow,addedDate);

        if (insuranceList.size() > 0){
            for (TableInsurance.InsuranceNonStatic insurance:  insuranceList) {
                notifTitle = "Insurance " + insurance.insuranceName;
                notifSubject = insurance.insuranceName + " expires on : " + insurance.expiryDate;
                Notification(notifTitle , notifSubject , context);
            }
        }
        if (fitnessList.size() > 0){
            for (TableFitness.FitnessNonStatic fitness:  fitnessList) {
                notifTitle = "Fitness is going to expire soon ";
                notifSubject = "Fitness expires on : " + fitness.expiryDate;
                Notification(notifTitle , notifSubject , context);
            }
        }
        if (taxList.size()> 0){
            for (TableRoadTaxation.RoadTaxationNonStatic tax:  taxList) {
                notifTitle = "Road taxation is going to expire soon ";
                notifSubject = "Taxation expires on : " + tax.expiryDate;
                Notification(notifTitle , notifSubject , context);
            }
        }

        OnBootCompleteNotification(context);
        Toast.makeText(context, "VSM is running in background", Toast.LENGTH_LONG).show();

    }

    public void Notification(String title, String subject , Context context){

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_logo_new)
                        .setContentTitle(title)
                        .setContentText(subject);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(++notificationId, mBuilder.build());
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


    public void OnBootCompleteNotification(Context context){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_logo_new)
                        .setContentTitle("VMS")
                        .setContentText("VSM is running in background");

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }


}
