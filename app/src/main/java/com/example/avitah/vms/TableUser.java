package com.example.avitah.vms;

import android.provider.BaseColumns;

/**
 * Created by kaveer on 10/15/2016.
 */

public class TableUser {

    public static int userId;
    public static String email;
    public static String password;
    public static String firstName;
    public static String lastName;
    public static String address;
    public static int contact;

    public  static abstract class TableUserDetails implements BaseColumns{
        public static final String tableName = "TableUser";

        public static String col_userId = "UserId";
        public static String col_email = "Email";
        public static String col_password = "Password";
        public static String col_firstName = "FirstName";
        public static String col_lastName = "LastName";
        public static String col_address = "Address";
        public static String col_contact = "ContactNumber";
    }
}
