package com.example.avitah.vms;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

/**
 * Created by kaveer on 10/15/2016.
 */

public class DBHandler extends SQLiteOpenHelper {

    public static final String databaseName = "VMSDB";
    public static final int databaseVersion = 1;

    public DBHandler(Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableUserQuery;
        createTableUserQuery =
                "CREATE TABLE "+ TableUser.TableUserDetails.tableName +
                " ("
                        + TableUser.TableUserDetails.col_userId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + TableUser.TableUserDetails.col_email + "  TEXT,"
                        + TableUser.TableUserDetails.col_password + "  TEXT,"
                        + TableUser.TableUserDetails.col_firstName + "  TEXT,"
                        + TableUser.TableUserDetails.col_lastName + "  TEXT,"
                        + TableUser.TableUserDetails.col_address + "  TEXT,"
                        + TableUser.TableUserDetails.col_contact + "  INT"
                + " )";

        db.execSQL(createTableUserQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS" + TableUser.TableUserDetails.tableName);
        onCreate(db);
    }

    public void PostUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TableUser.TableUserDetails.col_email , TableUser.email);
        values.put(TableUser.TableUserDetails.col_password, TableUser.password);
        values.put(TableUser.TableUserDetails.col_firstName, TableUser.firstName);
        values.put(TableUser.TableUserDetails.col_lastName, TableUser.lastName);
        values.put(TableUser.TableUserDetails.col_address, TableUser.address);
        values.put(TableUser.TableUserDetails.col_contact, TableUser.contact);

        db.insert(TableUser.TableUserDetails.tableName , null , values);
        db.close();
    }

    public boolean GetUser(){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String query;
        query  = "SELECT * FROM "
                + TableUser.TableUserDetails.tableName +
                " WHERE " + TableUser.TableUserDetails.col_email + " = '" + TableUser.email +
                "' AND " + TableUser.TableUserDetails.col_password + " = '" + TableUser.password + "'" ;

        Cursor cursor = db.rawQuery(query , null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            TableUser.userId = Integer.parseInt(cursor.getString(0));
            TableUser.email = cursor.getString(1);
            TableUser.password = cursor.getString(2);
            TableUser.firstName = cursor.getString(3);
            TableUser.lastName = cursor.getString(4);
            TableUser.address = cursor.getString(5);
            TableUser.contact = Integer.parseInt(cursor.getString(6));

            return result = true;
        }
        db.close();

        return  result;
    }
}
