package com.example.avitah.Tables;

import android.provider.BaseColumns;

/**
 * Created by kaveer on 10/23/2016.
 */

public class TableAccident {
    public static int accidentId;
    public  static  int userId;
    public static String acciDate;
    public static String makeModel;
    public static String regNo;
    public static String acciName;
    public static int contactNo;
    public static String insurance;
    public static String policyNo;
    public static String acciDescription;
    public static String status = "ACTIVE";
    public static int count;

    public class AccidentNonStatic{
        public  int accidentId;
        public  int userId;
        public  String acciDate;
        public  String makeModel;
        public  String regNo;
        public  String acciName;
        public  int contactNo;
        public  String insurance;
        public  String policyNo;
        public  String acciDescription;
        public  String status ;
    }

    public  static abstract class TableAccidentDetails implements BaseColumns {
        public static final String tableName = "TableAccident";

        public static String col_accidentId = "AccidentId";
        public static String col_userId = "UserId";
        public static String col_acciDate ="AccidentDate";
        public static String col_makeModel = "MakeAndModel";
        public static String col_regNo = "RegNo";
        public static String col_acciName = "AccidentName";
        public static String col_contactNo = "Contact";
        public static String col_insurance = "InsuranceName";
        public static String col_policyNo = "PolicyNo";
        public static String col_acciDescription = "Description";
        public static String col_status = "Status";
    }


}
