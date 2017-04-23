package com.example.avitah.Tables;

import android.provider.BaseColumns;

/**
 * Created by kaveer on 1/25/2017.
 */

public class TableRepair {
    public  static int repairId;
    public  static int userId;
    public  static String repairDate;
    public  static int repairMileage;
    public  static String repairServicing;
    public  static String repairDescription;
    public  static String repairAdded;
    public  static  float Cost;
    public  static String Status = "ACTIVE";
    public static int repairCount;

    public class RepairNonStatic{
        public int repairId;
        public int userId;
        public String repairDate;
        public int repairMileage;
        public String repairServicing;
        public String repairDescription;
        public String repairAdded;
        public float Cost;
        public String Status;
        public int repairCount;
    }

    public  static abstract class TableRepairDetails implements BaseColumns {
        public static final String tableName = "TableRepair";

        public  static String col_repairId = "RepairId";
        public  static String col_userId = "UserId";
        public  static String col_date = "RepairDate";
        public  static String col_mileage = "Mileage";
        public  static String col_servicing = "SelectedServicing";
        public  static String col_description = "Description";
        public  static String col_partAdded = "PartsAdded";
        public  static  String col_cost = "repairCost";
        public  static  String col_status = "repairStatus";
    }
}
