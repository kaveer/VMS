package com.example.avitah.Fragment.CarWash;


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
import com.example.avitah.Tables.TableCarWash;
import com.example.avitah.Tables.TableUser;
import com.example.avitah.vms.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarWashHistoryFragment extends Fragment {

    ArrayList<TableCarWash.CarWashNonStatic> carWashList;
    RadioGroup carWashRadioGroup;

    public CarWashHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Car Wash History");

        int counter = 0;
        View view = inflater.inflate(R.layout.fragment_car_wash_history, container, false);
        carWashRadioGroup = (RadioGroup)view.findViewById(R.id.RadioGroupCarWash);

        if(!GetCarWash()) {
            Toast messageBox = Toast.makeText(getActivity() , "No Car wash record!!" , Toast.LENGTH_SHORT);
            messageBox.show();
        }

        if(carWashList.size() > 0){
            GenerateRadioButton(counter);
        }

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fabCarWash);
        fab.setOnClickListener(new View.OnClickListener() {
            int counter = 0;
            @Override
            public void onClick(View view) {
                if(carWashRadioGroup.getCheckedRadioButtonId() == -1){
                    Toast messageBox = Toast.makeText(getActivity() , "Select car wash record to remove" , Toast.LENGTH_SHORT);
                    messageBox.show();
                }
                else {
                    DeleteCarWash();
                    carWashRadioGroup.removeAllViews();
                    GetCarWash();
                    GenerateRadioButton(counter);
                    Toast messageBox = Toast.makeText(getActivity() , "car wash record removed" , Toast.LENGTH_SHORT);
                    messageBox.show();
                }
                return;
            }
        });

        return view;
    }

    public boolean GetCarWash(){
        boolean result = true;
        TableCarWash.userId = TableUser.userId;

        DBHandler DB = new DBHandler(getContext());
        carWashList = DB.GetCarWash();

        if(carWashList.isEmpty()){
            result = false;
        }
        return result;
    }

    public void GenerateRadioButton(int counter){
        TableCarWash.carWashCount = carWashList.size();

        RadioButton[] washRadioButton = new RadioButton[TableCarWash.carWashCount];
        carWashRadioGroup.setOrientation(RadioGroup.VERTICAL);

        for (TableCarWash.CarWashNonStatic wash:  carWashList) {
            washRadioButton[counter]  = new RadioButton(getContext());
            washRadioButton[counter].setText(String.format("Car washed on %s at %s with a total cost of %s. More info %s", wash.date, wash.Location, String.valueOf(wash.Cost), wash.Description));
            washRadioButton[counter].setId(wash.carWashId);
            carWashRadioGroup.addView(washRadioButton[counter]);
        }
    }

    public void DeleteCarWash(){
        TableCarWash.carWashId = carWashRadioGroup.getCheckedRadioButtonId();
        DBHandler DB = new DBHandler(getContext());
        DB.DeleteCarWash();
    }

}
