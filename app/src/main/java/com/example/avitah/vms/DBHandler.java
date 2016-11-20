package com.example.avitah.vms;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

import java.util.ArrayList;

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
        String createTableAccident;
        String createTableInsurance;

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
                        + TableVehicle.TableVehicleDetails.col_load + "  REAL,"
                        + TableVehicle.TableVehicleDetails.col_status + "  TEXT"
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

        createTableAccident =
                "CREATE TABLE " + TableAccident.TableAccidentDetails.tableName +
                        " ("
                        + TableAccident.TableAccidentDetails.col_accidentId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + TableAccident.TableAccidentDetails.col_userId + "  INT,"
                        + TableAccident.TableAccidentDetails.col_acciDate +  "  DATE DEFAULT CURRENT_DATE,"
                        + TableAccident.TableAccidentDetails.col_makeModel + "  TEXT,"
                        + TableAccident.TableAccidentDetails.col_regNo + "  TEXT,"
                        + TableAccident.TableAccidentDetails.col_acciName + "  TEXT,"
                        + TableAccident.TableAccidentDetails.col_contactNo + "  INT,"
                        + TableAccident.TableAccidentDetails.col_insurance + "  TEXT,"
                        + TableAccident.TableAccidentDetails.col_policyNo + "  TEXT,"
                        + TableAccident.TableAccidentDetails.col_acciDescription + "  TEXT,"
                        + TableAccident.TableAccidentDetails.col_status + "  TEXT"
                        + " )";

        createTableInsurance =
                "CREATE TABLE " + TableInsurance.TableInsuranceDetails.tableName +
                        " ("
                        + TableInsurance.TableInsuranceDetails.col_insuranceId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + TableInsurance.TableInsuranceDetails.col_userId + "  INT,"
                        + TableInsurance.TableInsuranceDetails.col_insuranceType +  "  TEXT,"
                        + TableInsurance.TableInsuranceDetails.col_selectVehicleId + "  INT,"
                        + TableInsurance.TableInsuranceDetails.col_insuranceName + "  TEXT,"
                        + TableInsurance.TableInsuranceDetails.col_policyNo + "  TEXT,"
                        + TableInsurance.TableInsuranceDetails.col_certificateNo + "  TEXT,"
                        + TableInsurance.TableInsuranceDetails.col_policyHolder + "  TEXT,"
                        + TableInsurance.TableInsuranceDetails.col_effectiveDate + "  DATE DEFAULT CURRENT_DATE,"
                        + TableInsurance.TableInsuranceDetails.col_expiryDate + "  DATE DEFAULT CURRENT_DATE,"
                        + TableInsurance.TableInsuranceDetails.col_insuranceCost + "  REAL,"
                        + TableInsurance.TableInsuranceDetails.col_insuranceStatus + "  TEXT"
                        + " )";

        db.execSQL(createTableUserQuery);
        db.execSQL(createTableVehicleQuery);
        db.execSQL(createTableFuelQuery);
        db.execSQL(createTableAccident);
        db.execSQL(createTableInsurance);
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
    public ArrayList<TableVehicle.VehicleNonStatic> GetVehicle(){

        ArrayList<TableVehicle.VehicleNonStatic> vehicleList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query;
        query  = "SELECT * FROM "
                + TableVehicle.TableVehicleDetails.tableName  +
                " WHERE " + TableVehicle.TableVehicleDetails.col_userId + " = " + TableVehicle.userId +
                " AND "
                + TableVehicle.TableVehicleDetails.col_status + " = '" + TableVehicle.vehicleStatus + "'";

        Cursor cursor = db.rawQuery(query , null);
        if(cursor.getCount() > 0){
            for(cursor.moveToFirst(); !cursor.isAfterLast() ; cursor.moveToNext()){
                TableVehicle.VehicleNonStatic vehicle = new TableVehicle().new VehicleNonStatic();

                vehicle.vehicleId = Integer.parseInt(cursor.getString(0));
                vehicle.userId =  Integer.parseInt(cursor.getString(1));
                vehicle.regNo = cursor.getString(2);
                vehicle.make = cursor.getString(3);
                vehicle.model = cursor.getString(4);
                vehicle.classType = cursor.getString(5);
                vehicle.type = cursor.getString(6);
                vehicle.VehicleColor = cursor.getString(7);
                vehicle.chassisNo = cursor.getString(8);
                vehicle.engineNo = cursor.getString(9);
                vehicle.engineCapacity = Integer.parseInt(cursor.getString(10));
                vehicle.fuel = cursor.getString(11);
                vehicle.load = Float.parseFloat(cursor.getString(12));
                vehicle.vehicleStatus = cursor.getString(13);

                vehicleList.add(vehicle);
            }




        }
        db.close();

        return  vehicleList;


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
        values.put(TableVehicle.TableVehicleDetails.col_status , TableVehicle.vehicleStatus);


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

        c= db.update(TableVehicle.TableVehicleDetails.tableName, values , TableVehicle.TableVehicleDetails.col_vehicleId + " = ? " ,
                new String[]{String.valueOf(TableVehicle.vehicleId)} );

        return c;
    }

    public void GetVehicleByVehicleId(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query;
        query  = "SELECT * FROM "
                + TableVehicle.TableVehicleDetails.tableName  +
                " WHERE " + TableVehicle.TableVehicleDetails.col_userId + " = " + TableVehicle.userId +
                " AND "
                + TableVehicle.TableVehicleDetails.col_vehicleId + " = " + TableVehicle.vehicleId ;

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
            TableVehicle.vehicleStatus = cursor.getString(13);

        }
        db.close();
    }

    public void DeleteVehicle(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TableVehicle.TableVehicleDetails.col_status , "DEACTIVE");

        db.update(TableVehicle.TableVehicleDetails.tableName, values , TableVehicle.TableVehicleDetails.col_vehicleId + " = ? " ,
                new String[]{String.valueOf(TableVehicle.vehicleId)} );

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

    //============== DB Operation for accident =======================//
    public void PostAccident(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TableAccident.TableAccidentDetails.col_userId , TableAccident.userId);
        values.put(TableAccident.TableAccidentDetails.col_acciDate , TableAccident.acciDate);
        values.put(TableAccident.TableAccidentDetails.col_makeModel , TableAccident.makeModel);
        values.put(TableAccident.TableAccidentDetails.col_regNo , TableAccident.regNo);
        values.put(TableAccident.TableAccidentDetails.col_acciName , TableAccident.acciName);
        values.put(TableAccident.TableAccidentDetails.col_contactNo , TableAccident.contactNo);
        values.put(TableAccident.TableAccidentDetails.col_insurance , TableAccident.insurance);
        values.put(TableAccident.TableAccidentDetails.col_policyNo , TableAccident.policyNo);
        values.put(TableAccident.TableAccidentDetails.col_acciDescription , TableAccident.acciDescription);
        values.put(TableAccident.TableAccidentDetails.col_status , TableAccident.status);


        db.insert(TableFuel.TableFuelDetails.tableName , null , values);
        db.close();
    }
    //============== END DB Operation for accident =======================//

    //============== DB Operation for insurance =======================//
    public void PostInsurance(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TableInsurance.TableInsuranceDetails.col_userId , TableInsurance.userId);
        values.put(TableInsurance.TableInsuranceDetails.col_insuranceType, TableInsurance.insuranceType);
        values.put(TableInsurance.TableInsuranceDetails.col_selectVehicleId, TableInsurance.selectVehicleId);
        values.put(TableInsurance.TableInsuranceDetails.col_insuranceName, TableInsurance.insuranceName);
        values.put(TableInsurance.TableInsuranceDetails.col_policyNo, TableInsurance.policyNo);
        values.put(TableInsurance.TableInsuranceDetails.col_certificateNo, TableInsurance.certificateNo);
        values.put(TableInsurance.TableInsuranceDetails.col_policyHolder, TableInsurance.policyHolder);
        values.put(TableInsurance.TableInsuranceDetails.col_effectiveDate, TableInsurance.effectiveDate);
        values.put(TableInsurance.TableInsuranceDetails.col_expiryDate, TableInsurance.expiryDate);
        values.put(TableInsurance.TableInsuranceDetails.col_insuranceCost, TableInsurance.insuranceCost);
        values.put(TableInsurance.TableInsuranceDetails.col_insuranceStatus, TableInsurance.insuranceStatus);

        db.insert(TableInsurance.TableInsuranceDetails.tableName , null , values);
        db.close();
    }

    public ArrayList<TableInsurance.InsuranceNonStatic> GetInsurance(){
        ArrayList<TableInsurance.InsuranceNonStatic> insuranceList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query;
        query  = "SELECT * FROM "
                + TableInsurance.TableInsuranceDetails.tableName  +
                " WHERE " + TableInsurance.TableInsuranceDetails.col_userId + " = " + TableUser.userId +
                " AND "
                + TableInsurance.TableInsuranceDetails.col_insuranceStatus + " = '" + TableInsurance.insuranceStatus + "'";

        Cursor cursor = db.rawQuery(query , null);
        if(cursor.getCount() > 0){
            for(cursor.moveToFirst(); !cursor.isAfterLast() ; cursor.moveToNext()){
                TableInsurance.InsuranceNonStatic insurance = new TableInsurance().new InsuranceNonStatic();

                insurance.insuranceId = Integer.parseInt(cursor.getString(0));
                insurance.userId =  Integer.parseInt(cursor.getString(1));
                insurance.insuranceType = cursor.getString(2);
                insurance.selectVehicle = Integer.parseInt(cursor.getString(3)) ;
                insurance.insuranceName = cursor.getString(4);
                insurance.policyNo = cursor.getString(5);
                insurance.certificateNo = cursor.getString(6);
                insurance.policyHolder = cursor.getString(7);
                insurance.effectiveDate = cursor.getString(8);
                insurance.expiryDate = cursor.getString(9);
                insurance.insuranceCost = Float.parseFloat(cursor.getString(10));
                insurance.insuranceStatus = cursor.getString(11);

                insuranceList.add(insurance);
            }
        }
        db.close();

        return  insuranceList;
    }

    public void DeleteInsurance(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TableInsurance.TableInsuranceDetails.tableName, TableInsurance.TableInsuranceDetails.col_insuranceId + " = ? ",
                new String[]{String.valueOf(TableInsurance.insuranceId)});
        db.close();
    }
    //============== END DB Operation for insurance =======================//
}
