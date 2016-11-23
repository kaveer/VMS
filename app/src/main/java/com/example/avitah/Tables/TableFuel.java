package com.example.avitah.Tables;

import android.provider.BaseColumns;

import java.util.Date;

/**
 * Created by kaveer on 10/23/2016.
 */

public class TableFuel {
    public  static int fuelId;
    public  static int userId;
    public  static  String fuelDescription;
    public  static  String fuelLocation;
    public  static String fuelDate;
    public  static float fuelAmount;
    public  static float fuelTotalCost;
    public  static String fuelStatus = "ACTIVE";
    public static int fuelCount;

    public class FuelNonStatic {
        public int fuelId;
        public int userId;
        public String fuelDescription;
        public String fuelLocation;
        public String fuelDate;
        public float fuelAmount;
        public float fuelTotalCost;
        public String fuelStatus;
    }

    public  static abstract class TableFuelDetails implements BaseColumns {
        public static final String tableName = "TableFuel";

        public  static  String col_FuelId = "fuelId";
        public static String col_userId = "UserId";
        public static String col_FuelDescription = "Description";
        public  static  String col_Location = "Location";
        public  static  String col_FuelDate = "FuelDate";
        public  static  String col_Amount = "Amount";
        public  static  String col_totalCost = "TotalCost";
        public  static  String col_Status = "Status";
    }

}
