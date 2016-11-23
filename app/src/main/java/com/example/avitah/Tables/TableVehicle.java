package com.example.avitah.Tables;

import android.provider.BaseColumns;

/**
 * Created by kaveer on 10/19/2016.
 */

public class TableVehicle {
    public  static int vehicleId;
    public  static int userId;
    public  static String regNo;
    public  static  String make;
    public  static  String model;
    public  static  String classType;
    public  static  String type;
    public  static  String VehicleColor;
    public  static  String chassisNo;
    public  static  String engineNo;
    public  static  int engineCapacity;
    public  static  String fuel;
    public  static  float load;
    public  static String vehicleStatus = "ACTIVE";

    public static String actionType;
    public static int vehicleCount;

    public class VehicleNonStatic{
        public   int vehicleId;
        public   int userId;
        public   String regNo;
        public    String make;
        public    String model;
        public    String classType;
        public    String type;
        public    String VehicleColor;
        public    String chassisNo;
        public    String engineNo;
        public    int engineCapacity;
        public    String fuel;
        public    float load;
        public    String vehicleStatus;
    }

    public  static abstract class TableVehicleDetails implements BaseColumns {
        public static final String tableName = "TableVehicle";

        public  static  String col_vehicleId = "VehicleId";
        public static String col_userId = "UserId";
        public static String col_RegNo = "RegNo";
        public  static  String col_make = "Make";
        public  static  String col_model = "Model";
        public  static  String col_classType = "Class";
        public  static  String col_type = "Type";
        public  static  String col_VehicleColor = "Color";
        public  static  String col_chassisNo = "ChassisNo";
        public  static  String col_engineNo = "EngineNo";
        public  static  String col_engineCapacity = "EngineCapacity";
        public  static  String col_fuel = "Fuel";
        public  static  String col_load = "Load";
        public static String col_status = "Status";
    }
}
