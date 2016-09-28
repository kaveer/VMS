package com.example.avitah.vms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public  void OnLoginButtonClick(View login){

        if(login.getId() == R.id.ButtonLogin){

            EditText email = (EditText)findViewById(R.id.TextboxEmail);
            String emailVariable = email.getText().toString();
            EditText password = (EditText)findViewById(R.id.TextboxPassword);
            String passwordVariable = password.getText().toString();

            if (emailVariable.isEmpty() == true){
                email.setError("Enter Email");
                return;
            }
            if(passwordVariable.isEmpty() == true){
                password.setError("Enter Password");
                return;
            }

            if(passwordVariable.length() <= 6){
                password.setError( "must be greater that 6 characters");
                return;
            }

            Intent loginActivity = new Intent(Login.this, VMS.class);
            startActivity(loginActivity);

            Toast msgbox = Toast.makeText(Login.this , "Login successful" , Toast.LENGTH_SHORT);
            msgbox.show();
        }
    }

    public void OnSignUpButtonClick(View signUp){
        if(signUp.getId() == R.id.ButtonSignUp){
            Intent signupActivity = new Intent(Login.this, SignUp.class);
            startActivity(signupActivity);
        }
    }
}
