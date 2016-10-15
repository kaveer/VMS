package com.example.avitah.vms;

import android.content.Intent;
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
        email = (EditText)findViewById(R.id.TextboxEmail);
        password = (EditText)findViewById(R.id.TextboxPassword);

        if(login.getId() == R.id.ButtonLogin){
            if(ValidateEditText()){
                Intent main = new Intent(Login.this, VMS.class);
                startActivity(main);
                Toast messageBox = Toast.makeText(Login.this , "Login successful" , Toast.LENGTH_LONG);
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

    public void OnSignUpButtonClick(View signUp){
        if(signUp.getId() == R.id.ButtonSignUp){
            Intent signupActivity = new Intent(Login.this, SignUp.class);
            startActivity(signupActivity);
        }
    }
}
