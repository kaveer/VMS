package com.example.avitah.Tables;

import android.provider.BaseColumns;

/**
 * Created by kaveer on 1/18/2017.
 */

public class TableFitness {

    public  static int fitnessId;
    public  static int userId;
    public  static int vehicleId;
    public  static String renewalMonth;
    public  static String duration;
    public  static String expiryDate;
    public  static String Location;
    public  static float cost;
    public  static String fitnessStatus = "ACTIVE";
    public  static int fuelCount;

    public class FitnessNonStatic {
        public int fitnessId;
        public int userId;
        public int vehicleId;
        public String renewalMonth;
        public String duration;
        public String expiryDate;
        public String Location;
        public float cost;
        public String fitnessStatus;
    }

    public  static abstract class TableFitnessDetails implements BaseColumns {
        public static final String tableName = "TableFitness";

        public  static String col_fitnessId = "fitnessId";
        public  static String col_userId = "userId";
        public  static String col_vehicleId = "vehicleId";
        public  static String col_renewalMonth = "renewalMonth";
        public  static String col_duration = "duration";
        public  static String col_expiryDate = "expiryDate";
        public  static String col_Location = "location";
        public  static String col_cost = "cost";
        public  static String col_fitnessStatus = "status";
    }

}
