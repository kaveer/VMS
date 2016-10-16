package com.example.avitah.vms;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.attr.id;


public class OwnerDetailsFragment extends Fragment {

    EditText email;
    EditText password;
    EditText firstName;
    EditText lastName;
    EditText address;
    EditText contact;


    public OwnerDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_owner_details, container, false);

        InitializeTextEdit(view);
        SetTextEditor();
        Button buttonUpdate   = (Button)view.findViewById(R.id.BtnUpdateOwnerDetails);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ValidateEditText()){
                    UpdateUserDetails();
                }
                Toast messageBox = Toast.makeText(getActivity() , "Login successful" , Toast.LENGTH_LONG);
                messageBox.show();
            }
        });

        return view;
    }

    public void InitializeTextEdit(View view){
        email = (EditText)view.findViewById(R.id.TxtEmailOwnerDetails);
        password = (EditText)view.findViewById(R.id.TxtPasswordOwnerDetails);
        firstName = (EditText)view.findViewById(R.id.TxtFirstnameOwnerDetails);
        lastName = (EditText)view.findViewById(R.id.TxtLastnameOwnerDetails);
        address = (EditText)view.findViewById(R.id.TxtAddressOwnerDetails);
        contact = (EditText)view.findViewById(R.id.TxtContactOwnerDetails);
    }

    public void SetTextEditor(){
        email.setText(TableUser.email);
        password.setText(TableUser.password);
        firstName.setText(TableUser.firstName);
        lastName.setText(TableUser.lastName);
        address.setText(TableUser.address);
        contact.setText(String.valueOf(TableUser.contact));
    }

    public void  UpdateUserDetails(){
        TableUser.email = email.getText().toString().trim();
        TableUser.password = password.getText().toString().trim();
        TableUser.firstName = firstName.getText().toString().trim();
        TableUser.lastName = lastName.getText().toString().trim();
        TableUser.address = address.getText().toString().trim();
        TableUser.contact = Integer.parseInt(contact.getText().toString().trim());

        DBHandler DB = new DBHandler(getContext());
        DB.UpdateUserDetails();
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

        if(contact.getText().toString().trim().length() == 0){
            contact.setText("0");
        }

        return  result;
    }
}
