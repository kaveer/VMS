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
        String createTableVehicleQuery;
        String createTableFuelQuery;

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

        createTableVehicleQuery =
                "CREATE TABLE "+ TableVehicle.TableVehicleDetails.tableName +
                        " ("
                        + TableVehicle.TableVehicleDetails.col_vehicleId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + TableVehicle.TableVehicleDetails.col_userId + "  INT,"
                        + TableVehicle.TableVehicleDetails.col_RegNo + "  TEXT,"
                        + TableVehicle.TableVehicleDetails.col_make + "  TEXT,"
                        + TableVehicle.TableVehicleDetails.col_model + "  TEXT,"
                        + TableVehicle.TableVehicleDetails.col_classType + "  TEXT,"
                        + TableVehicle.TableVehicleDetails.col_type + "  TEXT,"
                        + TableVehicle.TableVehicleDetails.col_VehicleColor + "  TEXT,"
                        + TableVehicle.TableVehicleDetails.col_chassisNo + "  TEXT,"
                        + TableVehicle.TableVehicleDetails.col_engineNo + "  TEXT,"
                        + TableVehicle.TableVehicleDetails.col_engineCapacity + "  INT,"
                        + TableVehicle.TableVehicleDetails.col_fuel + "  TEXT,"
                        + TableVehicle.TableVehicleDetails.col_load + "  REAL"
                        + " )";

        createTableFuelQuery =
                "CREATE TABLE " + TableFuel.TableFuelDetails.tableName +
                        " ("
                        + TableFuel.TableFuelDetails.col_FuelId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + TableFuel.TableFuelDetails.col_userId + "  INT,"
                        + TableFuel.TableFuelDetails.col_FuelDescription + "  TEXT,"
                        + TableFuel.TableFuelDetails.col_Location + "  TEXT,"
                        + TableFuel.TableFuelDetails.col_FuelDate + "  DATE DEFAULT CURRENT_DATE,"
                        + TableFuel.TableFuelDetails.col_Amount + "  REAL,"
                        + TableFuel.TableFuelDetails.col_totalCost + "  REAL,"
                        + TableFuel.TableFuelDetails.col_Status + "  TEXT"
                        + " )";

        db.execSQL(createTableUserQuery);
        db.execSQL(createTableVehicleQuery);
        db.execSQL(createTableFuelQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS" + TableUser.TableUserDetails.tableName);
        db.execSQL("DROP TABLE IF EXISTS" + TableVehicle.TableVehicleDetails.tableName);
        onCreate(db);
    }

    // ============ DB Operation for user =====================//
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

    public  int UpdateUserDetails(){
        int c;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TableUser.TableUserDetails.col_email, TableUser.email);
        values.put(TableUser.TableUserDetails.col_password, TableUser.password);
        values.put(TableUser.TableUserDetails.col_firstName, TableUser.firstName);
        values.put(TableUser.TableUserDetails.col_lastName, TableUser.lastName);
        values.put(TableUser.TableUserDetails.col_address, TableUser.address);
        values.put(TableUser.TableUserDetails.col_contact, TableUser.contact);

       c= db.update(TableUser.TableUserDetails.tableName, values , TableUser.TableUserDetails.col_userId + " = ? " ,
               new String[]{String.valueOf(TableUser.userId)} );

        return c;
    }
    // ============ END DB Operation for user =====================//

    // ============= DB Operation for vehicle ==================//
    public boolean GetVehicle(){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String query;
        query  = "SELECT * FROM "
                + TableVehicle.TableVehicleDetails.tableName +
                " WHERE " + TableVehicle.TableVehicleDetails.col_userId + " = " + TableVehicle.userId ;

        Cursor cursor = db.rawQuery(query , null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            TableVehicle.vehicleId = Integer.parseInt(cursor.getString(0));
            TableVehicle.userId =  Integer.parseInt(cursor.getString(1));
            TableVehicle.regNo = cursor.getString(2);
            TableVehicle.make = cursor.getString(3);
            TableVehicle.model = cursor.getString(4);
            TableVehicle.classType = cursor.getString(5);
            TableVehicle.type = cursor.getString(6);
            TableVehicle.VehicleColor = cursor.getString(7);
            TableVehicle.chassisNo = cursor.getString(8);
            TableVehicle.engineNo = cursor.getString(9);
            TableVehicle.engineCapacity = Integer.parseInt(cursor.getString(10));
            TableVehicle.fuel = cursor.getString(11);
            TableVehicle.load = Float.parseFloat(cursor.getString(12));

            return result = true;
        }
        db.close();

        return  result;
    }

    public void PostVehicle(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TableVehicle.TableVehicleDetails.col_userId , TableVehicle.userId);
        values.put(TableVehicle.TableVehicleDetails.col_RegNo , TableVehicle.regNo);
        values.put(TableVehicle.TableVehicleDetails.col_make , TableVehicle.make);
        values.put(TableVehicle.TableVehicleDetails.col_model , TableVehicle.model);
        values.put(TableVehicle.TableVehicleDetails.col_classType , TableVehicle.classType);
        values.put(TableVehicle.TableVehicleDetails.col_type , TableVehicle.type);
        values.put(TableVehicle.TableVehicleDetails.col_VehicleColor , TableVehicle.VehicleColor);
        values.put(TableVehicle.TableVehicleDetails.col_chassisNo , TableVehicle.chassisNo);
        values.put(TableVehicle.TableVehicleDetails.col_engineNo , TableVehicle.engineNo);
        values.put(TableVehicle.TableVehicleDetails.col_engineCapacity , TableVehicle.engineCapacity);
        values.put(TableVehicle.TableVehicleDetails.col_fuel , TableVehicle.fuel);
        values.put(TableVehicle.TableVehicleDetails.col_load , TableVehicle.load);


        db.insert(TableVehicle.TableVehicleDetails.tableName , null , values);
        db.close();
    }

    public int UpdateVehicle(){
        int c;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TableVehicle.TableVehicleDetails.col_userId , TableVehicle.userId);
        values.put(TableVehicle.TableVehicleDetails.col_RegNo , TableVehicle.regNo);
        values.put(TableVehicle.TableVehicleDetails.col_make , TableVehicle.make);
        values.put(TableVehicle.TableVehicleDetails.col_model , TableVehicle.model);
        values.put(TableVehicle.TableVehicleDetails.col_classType , TableVehicle.classType);
        values.put(TableVehicle.TableVehicleDetails.col_type , TableVehicle.type);
        values.put(TableVehicle.TableVehicleDetails.col_VehicleColor , TableVehicle.VehicleColor);
        values.put(TableVehicle.TableVehicleDetails.col_chassisNo , TableVehicle.chassisNo);
        values.put(TableVehicle.TableVehicleDetails.col_engineNo , TableVehicle.engineNo);
        values.put(TableVehicle.TableVehicleDetails.col_engineCapacity , TableVehicle.engineCapacity);
        values.put(TableVehicle.TableVehicleDetails.col_fuel , TableVehicle.fuel);
        values.put(TableVehicle.TableVehicleDetails.col_load , TableVehicle.load);

        c= db.update(TableVehicle.TableVehicleDetails.tableName, values , TableVehicle.TableVehicleDetails.col_userId + " = ? " ,
                new String[]{String.valueOf(TableVehicle.userId)} );

        return c;
    }
    // ============= DB Operation for vehicle ==================//

    //============== DB Operation for fuel =======================//
    public void PostFuel(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TableFuel.TableFuelDetails.col_userId , TableFuel.userId);
        values.put(TableFuel.TableFuelDetails.col_FuelDescription, TableFuel.fuelDescription);
        values.put(TableFuel.TableFuelDetails.col_Location, TableFuel.fuelLocation);
        values.put(TableFuel.TableFuelDetails.col_FuelDate, TableFuel.fuelDate);
        values.put(TableFuel.TableFuelDetails.col_Amount, TableFuel.fuelAmount);
        values.put(TableFuel.TableFuelDetails.col_totalCost, TableFuel.fuelTotalCost);
        values.put(TableFuel.TableFuelDetails.col_Status, TableFuel.fuelStatus);

        db.insert(TableFuel.TableFuelDetails.tableName , null , values);
        db.close();
    }
    //============== END DB Operation for fuel =======================//
}
