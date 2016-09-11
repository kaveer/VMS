package com.example.avitah.vms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public  void OnLoginButtonClick(View login){

        if(login.getId() == R.id.ButtonLogin){
            Intent i = new Intent(Login.this, VMS.class);
            startActivity(i);
        }
    }

    public void OnSignUpButtonClick(View signUp){
        if(signUp.getId() == R.id.ButtonSignUp){
            Intent i = new Intent(Login.this, SignUp.class);
            startActivity(i);
        }
    }
}
