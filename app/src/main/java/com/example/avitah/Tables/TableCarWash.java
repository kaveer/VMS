package com.example.avitah.Tables;

import android.provider.BaseColumns;

/**
 * Created by kaveer on 11/26/2016.
 */

public class TableCarWash {
    public  static int carWashId;
    public  static int userId;
    public  static String date;
    public  static String Location;
    public  static String Description;
    public  static  float Cost;
    public  static String Status = "ACTIVE";
    public static int carWashCount;

    public class CarWashNonStatic{
        public   int carWashId;
        public   int userId;
        public   String date;
        public   String Location;
        public   String Description;
        public    float Cost;
        public   String Status;
    }

    public  static abstract class TableCarWashDetails implements BaseColumns {
        public static final String tableName = "TableCarWash";

        public  static String col_carWashId = "carWashId";
        public  static String col_userId = "UserId";
        public  static String col_date = "Date";
        public  static String col_location = "Location";
        public  static String col_description = "Description";
        public  static  String col_cost = "cost";
        public  static  String col_status = "Status";
    }
}
