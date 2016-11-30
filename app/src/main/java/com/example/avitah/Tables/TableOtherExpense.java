package com.example.avitah.Tables;

import android.provider.BaseColumns;

/**
 * Created by kaveer on 11/23/2016.
 */

public class TableOtherExpense {
    public  static int otherExpenseId;
    public  static int userId;
    public  static String date;
    public  static String Description;
    public  static  float Cost;
    public  static String Status = "ACTIVE";
    public static int otherExpenseCount;

    public class OtherExpenseNonStatic{
        public   int otherExpenseId;
        public   int userId;
        public   String date;
        public   String Description;
        public    float Cost;
        public   String Status ;
    }

    public  static abstract class TableOtherExpenseDetails implements BaseColumns {
        public static final String tableName = "TableOtherExpense";

        public  static String col_OtherExpenseId = "OtherExpenseId";
        public  static String col_userId = "UserId";
        public  static String col_date = "Date";
        public  static String col_description = "Description";
        public  static String col_cost = "cost";
        public  static String col_status = "Status";
    }
}
