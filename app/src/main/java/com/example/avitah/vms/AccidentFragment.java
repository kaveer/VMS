package com.example.avitah.vms;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccidentFragment extends Fragment {

    EditText date;
    EditText makeModel;
    EditText regNo;
    EditText name;
    EditText contactNo;
    EditText insurance;
    EditText policyNo;
    EditText description;


    public AccidentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_accident, container, false);
        InitializeEditText(view);
        date.setText(GetDateNow());

        Button buttonSave   = (Button)view.findViewById(R.id.BtnAccidentSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsValid()){
                    SetEditText();
                    InsertAccident();
                    Toast messageBox = Toast.makeText(getActivity() , "Accident record added" , Toast.LENGTH_LONG);
                    messageBox.show();
                }
            }
        });

        Button buttonViewAccidentHistory   = (Button)view.findViewById(R.id.BtnAccidentHistory);
        buttonViewAccidentHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccidentHistoryFragment fragment = new AccidentHistoryFragment();
                android.support.v4.app.FragmentTransaction fmTransaction = getFragmentManager().beginTransaction();
                fmTransaction.replace(R.id.Frame_container, fragment);
                fmTransaction.commit();
            }
        });

        return view;
    }

    public String GetDateNow(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //import java.text.SimpleDateFormat instead of android.icu.text.simpleDateFormat
        String currentDate = dateFormat.format(calendar.getTime());
        return currentDate;
    }

    public void InitializeEditText(View view){
        date = (EditText) view.findViewById(R.id.acciDate);
        makeModel = (EditText) view.findViewById(R.id.acciMakeModel);
        regNo = (EditText) view.findViewById(R.id.acciRegNo);
        name = (EditText) view.findViewById(R.id.acciName);
        contactNo = (EditText) view.findViewById(R.id.acciContactNo);
        insurance = (EditText) view.findViewById(R.id.acciInsurance);
        policyNo = (EditText) view.findViewById(R.id.acciPolicyNo);
        description = (EditText) view.findViewById(R.id.acciDescription);
    }

    public boolean IsValid(){
        boolean result = true;

        if(date.getText().toString().trim().length() == 0){
            date.setError("Enter date");
            return  result = false;
        }

        if(!date.getText().toString().trim().matches("(((19|20)([2468][048]|[13579][26]|0[48])|2000)[/-]02[/-]29|((19|20)[0-9]{2}[/-](0[4678]|1[02])[/-](0[1-9]|[12][0-9]|30)|(19|20)[0-9]{2}[/-](0[1359]|11)[/-](0[1-9]|[12][0-9]|3[01])|(19|20)[0-9]{2}[/-]02[/-](0[1-9]|1[0-9]|2[0-8])))")){
            date.setError("Invalid date(YYYY-MM-DD)");
            return  result = false;
        }

        if(makeModel.getText().toString().trim().length() == 0){
            makeModel.setError("Enter make and model vehicle");
            return  result = false;
        }

        if(regNo.getText().toString().trim().length() == 0){
            regNo.setError("Enter registration number of vehicle");
            return  result = false;
        }

        if(insurance.getText().toString().trim().length() == 0){
            insurance.setError("Enter insurance company name");
            return  result = false;
        }

        if(policyNo.getText().toString().trim().length() == 0){
            policyNo.setError("Enter policy number");
            return  result = false;
        }

        return result;
    }

    public void SetEditText(){
        TableAccident.acciDate = date.getText().toString().trim();
        TableAccident.makeModel = makeModel.getText().toString().trim();
        TableAccident.regNo = regNo.getText().toString().trim();
        TableAccident.acciName = name.getText().toString().trim();
        TableAccident.contactNo = Integer.parseInt(contactNo.getText().toString().trim());
        TableAccident.insurance = insurance.getText().toString().trim();
        TableAccident.policyNo = policyNo.getText().toString().trim();
        TableAccident.acciDescription = description.getText().toString().trim();
    }

    public void InsertAccident(){
        TableAccident.userId = TableUser.userId;

        DBHandler DB = new DBHandler(getContext());
        DB.PostAccident();
    }
}
