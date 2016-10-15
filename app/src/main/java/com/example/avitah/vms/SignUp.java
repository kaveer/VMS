package com.example.avitah.vms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void OnSignUpButtonClick(View signUp){
        email = (EditText)findViewById(R.id.TextboxEmail);
        password = (EditText)findViewById(R.id.TextboxPassword);
        confirmPassword = (EditText)findViewById(R.id.TextboxConfirmPassword);
        firstName = (EditText)findViewById(R.id.TextboxFirstName);
        lastName = (EditText)findViewById(R.id.TextboxLastName);
        address = (EditText)findViewById(R.id.TextboxAddress);
        contact = (EditText)findViewById(R.id.TextboxContact);

        if(signUp.getId() == R.id.ButtonSignUp){
           if(ValidateEditText()){
                Intent main = new Intent(SignUp.this, VMS.class);
                startActivity(main);
                Toast messageBox = Toast.makeText(SignUp.this , "Sign up successful" , Toast.LENGTH_LONG);
                messageBox.show();
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

        if(!email.getText().toString().trim().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            email.setError("Invalid Email format");
            return result = false;
        }

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
}
