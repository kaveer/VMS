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

            //EditText email = (EditText)findViewById(R.id.TextboxEmail);
           // String emailVariable = email.getText().toString();
           // EditText password = (EditText)findViewById(R.id.TextboxPassword);
           // String passwordVariable = password.getText().toString();

            Intent loginActivity = new Intent(Login.this, VMS.class);
           // loginActivity.putExtra("email" , emailVariable);
            //loginActivity.putExtra("password" , passwordVariable);
            startActivity(loginActivity);
        }
    }

    public void OnSignUpButtonClick(View signUp){
        if(signUp.getId() == R.id.ButtonSignUp){
            Intent signupActivity = new Intent(Login.this, SignUp.class);
            startActivity(signupActivity);
        }
    }
}
