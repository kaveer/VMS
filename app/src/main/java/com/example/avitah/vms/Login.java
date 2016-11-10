package com.example.avitah.vms;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public  void OnLoginButtonClick(View login){
        email = (EditText)findViewById(R.id.TextboxEmailLogin);
        password = (EditText)findViewById(R.id.TextboxPasswordLogin);

        if(login.getId() == R.id.ButtonLogin){
            if(ValidateEditText() && GetUser()){
                Intent main = new Intent(Login.this, VMS.class);
                startActivity(main);
                Toast messageBox = Toast.makeText(Login.this , "Login successful" , Toast.LENGTH_LONG);
                messageBox.show();
            }
            else {
                Toast messageBox = Toast.makeText(Login.this , "Login Fail!" , Toast.LENGTH_SHORT);
                messageBox.show();
            }
        }
    }

    public boolean ValidateEditText(){
        boolean result = true;

        if(email.getText().toString().trim().length() == 0){
            email.setError("Enter Email");
            return result = false;
        }

        if (!email.getText().toString().trim().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            email.setError("Invalid Email Format");
            return result = false;
        }

        if(password.getText().toString().trim().length() <= 6){
            password.setError( "must be greater that 6 characters");
            return result = false;
        }
        return  result;
    }

    public boolean GetUser(){
        boolean result = false;
        TableUser.email = email.getText().toString().trim();
        TableUser.password = password.getText().toString();

      //  TableUser.email = "kaveer.rajcoomar@gmail.com";
      //  TableUser.password = "kaveer3009";

        DBHandler DB = new DBHandler(this);
        result = DB.GetUser();

        return result;
    }

    public void OnSignUpButtonClick(View signUp){
        if(signUp.getId() == R.id.ButtonSignUp){
            Intent signupActivity = new Intent(Login.this, SignUp.class);
            startActivity(signupActivity);
        }
    }
}
