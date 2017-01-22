package com.example.avitah.Tables;

import android.provider.BaseColumns;

/**
 * Created by kaveer on 1/22/2017.
 */

public class TableRoadTaxation {
    public  static int taxId;
    public  static int userId;
    public  static int vehicleId;
    public  static String taxDescription;
    public  static String taxPaymentDate;
    public  static float taxCost;
    public  static String expiryDate;
    public  static String taxStatus = "ACTIVE";
    public static int taxCount;

    public class RoadTaxationNonStatic{
        public int taxId;
        public int userId;
        public int vehicleId;
        public String taxDescription;
        public String taxPaymentDate;
        public float taxCost;
        public String expiryDate;
        public String taxStatus;
    }

    public  static abstract class TableRoadTaxationDetails implements BaseColumns {
        public static final String tableName = "TableRoadTaxation";

        public  static String col_taxId = "TaxId";
        public  static String col_userId = "UserId";
        public  static String col_vehicleId = "VehicleId";
        public  static String col_taxDescription = "TaxDescription";
        public  static String col_taxPaymentDate = "PaymentDate";
        public  static  String col_taxCost = "Cost";
        public  static  String col_expiryDate = "ExpiryDate";
        public  static String col_taxStatus = "status";
    }

}
