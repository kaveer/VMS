package com.example.avitah.Tables;

import android.provider.BaseColumns;

/**
 * Created by kaveer on 11/28/2016.
 */

public class TableParking {
    public  static int parkingId;
    public  static int userId;
    public  static String date;
    public  static String Location;
    public static float duration;
    public  static String Description;
    public  static  float Cost;
    public  static String Status = "ACTIVE";
    public static int parkingCount;

    public class ParkingNonStatic{
        public   int parkingId;
        public   int userId;
        public   String date;
        public   String Location;
        public float duration;
        public   String Description;
        public    float Cost;
        public   String Status;
    }

    public  static abstract class TableParkingDetails implements BaseColumns {
        public static final String tableName = "TableParking";

        public  static String col_parkingId = "ParkingId";
        public  static String col_userId = "UserId";
        public  static String col_date = "Date";
        public  static String col_location = "Location";
        public static  String col_duration = "Duration";
        public  static String col_description = "Description";
        public  static  String col_cost = "cost";
        public  static  String col_status = "Status";
    }
}
