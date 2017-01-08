package com.example.avitah.Tables;

import android.provider.BaseColumns;

/**
 * Created by kaveer on 1/8/2017.
 */

public class TableFines {
    public  static int FineId;
    public  static int userId;
    public  static  String court;
    public  static  String fineDate;
    public  static String charge;
    public  static String breachOfArticle;
    public  static String ordinace;
    public  static String issuedBy;
    public  static String fineStatus = "ACTIVE";
    public static int fineCount;

    public class FineNonStatic {
        public int FineId;
        public int userId;
        public  String court;
        public  String fineDate;
        public String charge;
        public String breachOfArticle;
        public String ordinace;
        public String issuedBy;
        public String fineStatus;
    }

    public  static abstract class TableFineDetails implements BaseColumns {
        public static final String tableName = "TableFine";

        public  static  String col_FineId = "FineId";
        public static String col_userId = "UserId";
        public static String col_Court = "Court";
        public  static  String col_FineDate = "FineDate";
        public  static  String col_Charge = "Charge";
        public  static  String col_BreachOfArticle = "BreachOfArticle";
        public  static  String col_Ordinance = "Ordinance";
        public  static  String col_IssuedBy = "IssuedBy";
        public  static  String col_Status = "Status";
    }
}
