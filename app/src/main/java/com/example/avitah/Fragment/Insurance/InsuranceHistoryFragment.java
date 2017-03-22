package com.example.avitah.Fragment.Insurance;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.avitah.DbHandler.DBHandler;
import com.example.avitah.Tables.TableInsurance;
import com.example.avitah.Tables.TableUser;
import com.example.avitah.vms.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class InsuranceHistoryFragment extends Fragment {

    ArrayList<TableInsurance.InsuranceNonStatic> InsuranceList;
    RadioGroup insuranceRadioGroup;

    public InsuranceHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Insurance History");

        int counter = 0;
        View view = inflater.inflate(R.layout.fragment_insurance_history, container, false);
        insuranceRadioGroup =(RadioGroup)view.findViewById(R.id.RadioGroupInsurance);

        if(!GetInsurance()) {
            Toast messageBox = Toast.makeText(getActivity() , "No Insurance!!" , Toast.LENGTH_SHORT);
            messageBox.show();
        }
        if(InsuranceList.size() > 0){
            GenerateRadioButton(counter);
        }

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            int counter = 0;
            @Override
            public void onClick(View view) {
                if(insuranceRadioGroup.getCheckedRadioButtonId() == -1){
                    Toast messageBox = Toast.makeText(getActivity() , "Select insurance to remove" , Toast.LENGTH_SHORT);
                    messageBox.show();
                }
                else {
                    DeleteInsurance();
                    insuranceRadioGroup.removeAllViews();
                    GetInsurance();
                    GenerateRadioButton(counter);
                    Toast messageBox = Toast.makeText(getActivity() , "Insurance removed" , Toast.LENGTH_SHORT);
                    messageBox.show();
                }
                return;
            }
        });
        return view;
    }

    public boolean GetInsurance(){
        boolean result = true;
        TableInsurance.userId = TableUser.userId;

        DBHandler DB = new DBHandler(getContext());
        InsuranceList = DB.GetInsurance();

        if(InsuranceList.isEmpty()){
            result = false;
        }
        return result;
    }

    public void DeleteInsurance(){
        TableInsurance.insuranceId = insuranceRadioGroup.getCheckedRadioButtonId();
        DBHandler DB = new DBHandler(getContext());
        DB.DeleteInsurance();
    }

    public void GenerateRadioButton(int counter){
        TableInsurance.insuranceCount = InsuranceList.size();

        RadioButton[] InsuranceRadioButton = new RadioButton[TableInsurance.insuranceCount];
        insuranceRadioGroup.setOrientation(RadioGroup.VERTICAL);

        for (TableInsurance.InsuranceNonStatic insurance:  InsuranceList) {
            InsuranceRadioButton[counter]  = new RadioButton(getContext());
            InsuranceRadioButton[counter].setText(String.format("%s type insurance obtained from %s with policy no : %s and certificate no: %s. More info: effective date: %s and expiry date: %s. A total cost of %s was paid", insurance.insuranceType, insurance.insuranceName, String.valueOf(insurance.policyNo), insurance.certificateNo, insurance.effectiveDate, insurance.expiryDate, String.valueOf(insurance.insuranceCost)));
            InsuranceRadioButton[counter].setId(insurance.insuranceId);
            insuranceRadioGroup.addView(InsuranceRadioButton[counter]);
        }
    }
}
