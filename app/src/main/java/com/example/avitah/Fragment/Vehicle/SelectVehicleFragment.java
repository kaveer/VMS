package com.example.avitah.Fragment.Vehicle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.avitah.DbHandler.DBHandler;
import com.example.avitah.Tables.TableUser;
import com.example.avitah.Tables.TableVehicle;
import com.example.avitah.vms.R;

import java.util.ArrayList;




/**
 * A simple {@link Fragment} subclass.
 */
public class SelectVehicleFragment extends Fragment {

    ArrayList<TableVehicle.VehicleNonStatic> vehicleList;
    RadioGroup vehicleRadioGroup;

    public SelectVehicleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int counter = 0;
        View view = inflater.inflate(R.layout.fragment_select_vehicle, container, false);
        vehicleRadioGroup =(RadioGroup)view.findViewById(R.id.vehicleRadioGroup);

        if(!GetVehicle()) {
            TableVehicle.actionType = "insert";

            Toast messageBox = Toast.makeText(getActivity() , "No Vehicle!!" , Toast.LENGTH_SHORT);
            messageBox.show();

            NavigateToVehicleFragment();
        }
        if(vehicleList.size() > 0){
            TableVehicle.vehicleCount = vehicleList.size();

            RadioButton[] vehicleRadioButton = new RadioButton[TableVehicle.vehicleCount];
            vehicleRadioGroup.setOrientation(RadioGroup.VERTICAL);

            for (TableVehicle.VehicleNonStatic vehicle:  vehicleList) {
                vehicleRadioButton[counter]  = new RadioButton(getContext());
                vehicleRadioButton[counter].setText(vehicle.make + "  " + vehicle.model);
                vehicleRadioButton[counter].setId(vehicle.vehicleId);
                vehicleRadioGroup.addView(vehicleRadioButton[counter]);
            }
        }

        Button buttonUpdate   = (Button)view.findViewById(R.id.BtnUpdateVehicle);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableVehicle.actionType = "update";

                if(vehicleRadioGroup.getCheckedRadioButtonId() == -1){
                    Toast messageBox = Toast.makeText(getActivity() , "Select a vehicle to update" , Toast.LENGTH_SHORT);
                    messageBox.show();
                    return;
                }

                GetVehicleByVehicleId(vehicleRadioGroup);
                NavigateToVehicleFragment();
            }


        });

        Button buttonNewVehicle   = (Button)view.findViewById(R.id.Btn_addVehicle);
        buttonNewVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableVehicle.actionType = "insert";

                NavigateToVehicleFragment();
            }
        });

        return view;
    }

    public boolean GetVehicle(){
        boolean result = true;
        TableVehicle.userId = TableUser.userId;

        DBHandler DB = new DBHandler(getContext());
        vehicleList = DB.GetVehicle();

        if(vehicleList.isEmpty()){
            result = false;
        }
        return result;
    }

    private void GetVehicleByVehicleId(RadioGroup vehicleRadioGroup) {
        TableVehicle.vehicleId = vehicleRadioGroup.getCheckedRadioButtonId();
        TableVehicle.userId = TableUser.userId;

        DBHandler DB = new DBHandler(getContext());
        DB.GetVehicleByVehicleId();
    }

    private void NavigateToVehicleFragment() {
        VehicleDetailsFragment fragment = new VehicleDetailsFragment();
        android.support.v4.app.FragmentTransaction fmTransaction = getFragmentManager().beginTransaction();
        fmTransaction.replace(R.id.Frame_container, fragment);
        fmTransaction.commit();
    }
}
