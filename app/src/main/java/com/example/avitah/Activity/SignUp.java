package com.example.avitah.Activity;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.avitah.DbHandler.DBHandler;
import com.example.avitah.Tables.TableUser;
import com.example.avitah.vms.R;
import com.example.avitah.vms.VMS;

public class SignUp extends AppCompatActivity {

    EditText email;
    EditText password;
    EditText confirmPassword;
    EditText firstName;
    EditText lastName;
    EditText address;
    EditText contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTitle("Sign up");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void OnSignUpButtonClick(View signUp){
        email = (EditText)findViewById(R.id.TextboxEmailSignUp);
        password = (EditText)findViewById(R.id.TextboxPasswordSignUp);
        confirmPassword = (EditText)findViewById(R.id.TextboxConfirmPasswordSignUp);
        firstName = (EditText)findViewById(R.id.TextboxFirstNameSignUp);
        lastName = (EditText)findViewById(R.id.TextboxLastNameSignUp);
        address = (EditText)findViewById(R.id.TextboxAddressSignUp);
        contact = (EditText)findViewById(R.id.TextboxContactSignUp);

        if(signUp.getId() == R.id.ButtonSignUp){
           if(ValidateEditText() && PostUser()){
                Intent main = new Intent(SignUp.this, VMS.class);
                startActivity(main);
                Toast messageBox = Toast.makeText(SignUp.this , "Sign up successful" , Toast.LENGTH_LONG);
                messageBox.show();
               SetNotification();
           }
        }
    }

   public boolean ValidateEditText(){
        boolean result = true;
        String getPassword = password.getText().toString().trim();
        String getConfirmPassword = confirmPassword.getText().toString().trim();

        if(email.getText().toString().trim().length() == 0){
            email.setError("Enter Email");
            return result = false;
        }

        /*if(!email.getText().toString().trim().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            email.setError("Invalid Email format");
            return result = false;
        }*/

        if(password.getText().toString().trim().length() <= 6){
            password.setError("Password must be greater than 6 char");
            return result = false;
        }

        if(!getPassword.equals(getConfirmPassword)){
            confirmPassword.setError("Password don't match");
            return result = false;
        }

        if(firstName.getText().toString().trim().length() == 0){
            firstName.setError("Enter Firstname");
            return result = false;
        }

        if(lastName.getText().toString().trim().length() == 0){
            lastName.setError("Enter Firstname");
            return result = false;
        }

        return result;
    }

    public boolean PostUser(){
        boolean result = false;

        GetEditText();

        DBHandler DB = new DBHandler(this);
        DB.PostUser();
        result = DB.GetUser();

        return result;
    }

    public void GetEditText(){
        TableUser.email = email.getText().toString().trim();
        TableUser.password = password.getText().toString().trim();
        TableUser.firstName = firstName.getText().toString().trim();
        TableUser.lastName = lastName.getText().toString().trim();
        TableUser.address = address.getText().toString().trim();
        if(contact.getText().toString().trim().length() == 0){
            contact.setText("0");
        }
        TableUser.contact = Integer.parseInt(contact.getText().toString().trim());
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void SetNotification(){

        Notification n  = new Notification.Builder(this)
                .setContentTitle("New mail from " + "test@gmail.com")
                .setContentText("Subject")
                .setSmallIcon(R.drawable.ic_logo_new)
                .build();


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(0, n);
    }
}
