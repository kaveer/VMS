package com.example.avitah.Fragment.Parking;


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
import com.example.avitah.Tables.TableParking;
import com.example.avitah.Tables.TableUser;
import com.example.avitah.vms.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ParkingHistoryFragment extends Fragment {
    ArrayList<TableParking.ParkingNonStatic> ParkingList;
    RadioGroup parkingRadioGroup;

    public ParkingHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Parking History");

        int counter = 0;
        View view = inflater.inflate(R.layout.fragment_parking_history, container, false);
        parkingRadioGroup = (RadioGroup)view.findViewById(R.id.RadioGroupParking);

        if(!GetParking()) {
            Toast messageBox = Toast.makeText(getActivity() , "No parking record!!" , Toast.LENGTH_SHORT);
            messageBox.show();
        }

        if(ParkingList.size() > 0){
            GenerateRadioButton(counter);
        }

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fabParking);
        fab.setOnClickListener(new View.OnClickListener() {
            int counter = 0;
            @Override
            public void onClick(View view) {
                if(parkingRadioGroup.getCheckedRadioButtonId() == -1){
                    Toast messageBox = Toast.makeText(getActivity() , "Select parking record to remove" , Toast.LENGTH_SHORT);
                    messageBox.show();
                }
                else {
                    DeleteParking();
                    parkingRadioGroup.removeAllViews();
                    GetParking();
                    GenerateRadioButton(counter);
                    Toast messageBox = Toast.makeText(getActivity() , "parking record removed" , Toast.LENGTH_SHORT);
                    messageBox.show();
                }
                return;
            }
        });

        return view;
    }

    public boolean GetParking(){
        boolean result = true;
        TableParking.userId = TableUser.userId;

        DBHandler DB = new DBHandler(getContext());
        ParkingList = DB.GetParking();

        if(ParkingList.isEmpty()){
            result = false;
        }
        return result;
    }

    public void GenerateRadioButton(int counter){
        TableParking.parkingCount = ParkingList.size();

        RadioButton[] parkingRadioButton = new RadioButton[TableParking.parkingCount];
        parkingRadioGroup.setOrientation(RadioGroup.VERTICAL);

        for (TableParking.ParkingNonStatic parking:  ParkingList) {
            parkingRadioButton[counter]  = new RadioButton(getContext());
            parkingRadioButton[counter].setText("Car parked at " + parking.Location + " for " +parking.duration + " on " +parking.date);
            parkingRadioButton[counter].setId(parking.parkingId);
            parkingRadioGroup.addView(parkingRadioButton[counter]);
        }
    }

    public void DeleteParking(){
        TableParking.parkingId = parkingRadioGroup.getCheckedRadioButtonId();
        DBHandler DB = new DBHandler(getContext());
        DB.DeleteParking();
    }

}
