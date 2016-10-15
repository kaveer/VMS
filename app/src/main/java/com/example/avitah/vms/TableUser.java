package com.example.avitah.vms;

/**
 * Created by kaveer on 10/15/2016.
 */

public class TableUser {

    int userId;
    String email;
    String password;
    String firstName;
    String lastName;
    String address;
    int contact;

    public TableUser(){

    }

    public void SetUserId(int userId){
        this.userId = userId;
    }
    public void SetEmail(String email){
        this.email = email;
    }
    public void SetPassword(String password){
        this.password = password;
    }
    public void SetFirstName(String firstName){
        this.firstName = firstName;
    }
    public void SetLastName(String lastName){
        this.lastName = lastName;
    }
    public void SetAddress(String address){
        this.address = address;
    }
    public void SetContact(int contact){
        this.contact = contact;
    }

    public int GetUserId(){
       return this.userId;
    }
    public String GetEmail(){
       return this.email;
    }
    public String GetPassword(){
       return this.password;
    }
    public String GetFirstName(){
       return this.firstName;
    }
    public String GetLastName(){
        return this.lastName;
    }
    public String GetAddress(){
        return this.address;
    }
    public int GetContact(){
       return this.contact;
    }



}
