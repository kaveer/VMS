package com.example.avitah.vms;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FuelHistoryFragment extends Fragment {

    ArrayList<TableFuel.FuelNonStatic> fuelList;
    RadioGroup fuelRadioGroup;

    public FuelHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int counter = 0;
        View view = inflater.inflate(R.layout.fragment_fuel_history, container, false);
        fuelRadioGroup =(RadioGroup)view.findViewById(R.id.RadioGroupFuel);

        if(!GetFuel()) {
            Toast messageBox = Toast.makeText(getActivity() , "No Fuel record!!" , Toast.LENGTH_SHORT);
            messageBox.show();
        }

        if(fuelList.size() > 0){
            GenerateRadioButton(counter);
        }

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.FloatRemoveFuel);
        fab.setOnClickListener(new View.OnClickListener() {
            int counter = 0;
            @Override
            public void onClick(View view) {
                if(fuelRadioGroup.getCheckedRadioButtonId() == -1){
                    Toast messageBox = Toast.makeText(getActivity() , "Select fuel to remove" , Toast.LENGTH_SHORT);
                    messageBox.show();
                }
                else {
                    DeleteInsurance();
                    fuelRadioGroup.removeAllViews();
                    GetFuel();
                    GenerateRadioButton(counter);
                    Toast messageBox = Toast.makeText(getActivity() , "Insurance removed" , Toast.LENGTH_SHORT);
                    messageBox.show();
                }
                return;
            }
        });

        return view;
    }

    public boolean GetFuel(){
        boolean result = true;
        TableFuel.userId = TableUser.userId;

        DBHandler DB = new DBHandler(getContext());
        fuelList = DB.GetFuel();

        if(fuelList.isEmpty()){
            result = false;
        }
        return result;
    }

    public void DeleteInsurance(){
        TableFuel.fuelId = fuelRadioGroup.getCheckedRadioButtonId();
        DBHandler DB = new DBHandler(getContext());
        DB.DeleteFuel();
    }

    public void GenerateRadioButton(int counter){
        TableFuel.fuelCount = fuelList.size();

        RadioButton[] fuelRadioButton = new RadioButton[TableFuel.fuelCount];
        fuelRadioGroup.setOrientation(RadioGroup.VERTICAL);

        for (TableFuel.FuelNonStatic fuel:  fuelList) {
            fuelRadioButton[counter]  = new RadioButton(getContext());
            fuelRadioButton[counter].setText("Added at " + fuel.fuelLocation + " ,cost :" + fuel.fuelTotalCost);
            fuelRadioButton[counter].setId(fuel.fuelId);
            fuelRadioGroup.addView(fuelRadioButton[counter]);
        }
    }

}
