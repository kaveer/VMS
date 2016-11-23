package com.example.avitah.vms;

import android.provider.BaseColumns;

/**
 * Created by kaveer on 11/18/2016.
 */

public class TableInsurance {
    public  static int insuranceId;
    public  static int userId;
    public  static String insuranceType;
    public  static int selectVehicleId;
    public  static  String insuranceName;
    public  static  int policyNo;
    public  static  String certificateNo;
    public  static  String policyHolder;
    public  static  String effectiveDate;
    public  static  String expiryDate;
    public  static  float insuranceCost;
    public  static String insuranceStatus = "ACTIVE";
    public static int insuranceCount;

    public class InsuranceNonStatic{
        public  int insuranceId;
        public  int userId;
        public  String insuranceType;
        public  int selectVehicle;
        public  String insuranceName;
        public   int policyNo;
        public    String certificateNo;
        public    String policyHolder;
        public    String effectiveDate;
        public    String expiryDate;
        public    float insuranceCost;
        public   String insuranceStatus;
    }

    public  static abstract class TableInsuranceDetails implements BaseColumns {
        public static final String tableName = "TableInsurance";

        public  static String col_insuranceId = "InsuranceId";
        public  static String col_userId = "UserId";
        public  static String col_insuranceType = "InsuranceType";
        public  static String col_selectVehicleId = "VehicleId";
        public  static  String col_insuranceName = "Name";
        public  static  String col_policyNo = "PolicyNo";
        public  static  String col_certificateNo = "CertificateNo";
        public  static  String col_policyHolder = "PolicyHolder";
        public  static  String col_effectiveDate = "EffectiveDate";
        public  static  String col_expiryDate = "ExpiryDate";
        public  static  String col_insuranceCost = "Cost";
        public  static String col_insuranceStatus = "status";
    }
}
