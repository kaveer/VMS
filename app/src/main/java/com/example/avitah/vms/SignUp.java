package com.example.avitah.vms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void OnSignUpButtonClick(View signUp){
        if(signUp.getId() == R.id.ButtonSignUp){

            EditText email = (EditText)findViewById(R.id.TextboxEmail);
            String emailVariable = email.getText().toString();
            EditText password = (EditText)findViewById(R.id.TextboxPassword);
            String passwordVariable = password.getText().toString();
            EditText confirmPassword = (EditText)findViewById(R.id.TextboxConfirmPassword);
            String confirmPasswordVariable = confirmPassword.getText().toString();
            EditText firstname = (EditText)findViewById(R.id.TextboxFirstName);
            String firstnameVariable = firstname.getText().toString();
            EditText lastname = (EditText)findViewById(R.id.TextboxLastName);
            String lastnameVariable = lastname.getText().toString();
            EditText address = (EditText)findViewById(R.id.TextboxAddress);
            String addressVariable = address.getText().toString();
            EditText contact = (EditText)findViewById(R.id.TextboxContact);
            String contactVariable = contact.getText().toString();

            if(ValidatePassword(passwordVariable , confirmPasswordVariable)== true){
                Intent i = new Intent(SignUp.this, VMS.class);
                startActivity(i);
            }
        }
    }

    public boolean ValidatePassword(String password , String confirmPassword)
    {

        if (!password.equals(confirmPassword)) {

            Toast msgbox = Toast.makeText(SignUp.this , "Password don't match" , Toast.LENGTH_SHORT);
            msgbox.show();

            return false;
        }
        return true;
    }

}
