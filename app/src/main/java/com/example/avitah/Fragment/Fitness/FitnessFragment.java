package com.example.avitah.Fragment.Fitness;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.avitah.DbHandler.DBHandler;
import com.example.avitah.Tables.TableFitness;
import com.example.avitah.Tables.TableUser;
import com.example.avitah.Tables.TableVehicle;
import com.example.avitah.vms.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FitnessFragment extends Fragment {

    int counter = 0;
    ArrayList<TableVehicle.VehicleNonStatic> vehicleList;

    RadioGroup vehicleRadioGroup;
    Spinner spinnerFitnessRenewal;
    Spinner SpinnerFitnessDuration;
    EditText ExpiryDate;
    EditText Location;
    EditText Cost;

    public FitnessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Fitness");

        View view = inflater.inflate(R.layout.fragment_fitness, container, false);
        InitializeText(view);

        if(!GetVehicle()){
            Toast messageBox = Toast.makeText(getActivity() , "No Vehicle!! add in vehicle section" , Toast.LENGTH_SHORT);
            messageBox.show();
        }

        if(vehicleList.size() > 0){
            TableVehicle.vehicleCount = vehicleList.size();

            RadioButton[] vehicleRadioButton = new RadioButton[TableVehicle.vehicleCount];
            vehicleRadioGroup.setOrientation(RadioGroup.VERTICAL);

            for (TableVehicle.VehicleNonStatic vehicle:  vehicleList) {
                vehicleRadioButton[counter]  = new RadioButton(getContext());
                vehicleRadioButton[counter].setText(vehicle.make + " " + vehicle.model);
                vehicleRadioButton[counter].setId(vehicle.vehicleId);
                vehicleRadioGroup.addView(vehicleRadioButton[counter]);
            }
        }

        Button buttonSave   = (Button)view.findViewById(R.id.BtnSaveFitness);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!GetVehicle()){
                    Toast messageBox = Toast.makeText(getActivity() , "No Vehicle!! add in vehicle section" , Toast.LENGTH_SHORT);
                    messageBox.show();
                    return;
                }
                if(IsValid()){
                    SetText();
                    InsertInsurance();

                    Toast messageBox = Toast.makeText(getActivity() , "New fitness added" , Toast.LENGTH_SHORT);
                    messageBox.show();
                }
            }
        });

        Button buttonViewHistory   = (Button)view.findViewById(R.id.BtnViewFitnessHistory);
        buttonViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FitnessHistoryFragment fragment = new FitnessHistoryFragment();
                android.support.v4.app.FragmentTransaction fmTransaction = getFragmentManager().beginTransaction();
                fmTransaction.replace(R.id.Frame_container, fragment);
                fmTransaction.commit();


            }


        });

        return view;
    }

    private void InsertInsurance() {
        DBHandler DB = new DBHandler(getContext());
        DB.PostFitness();
    }

    public void InitializeText(View view){
        vehicleRadioGroup =(RadioGroup)view.findViewById(R.id.FitnessVehicleRadioButton);
        spinnerFitnessRenewal = (Spinner)view.findViewById(R.id.spinnerFitnessMonth);
        SpinnerFitnessDuration = (Spinner) view.findViewById(R.id.SpinnerFitnessDuration);
        ExpiryDate = (EditText) view.findViewById(R.id.FitnessExpiryDate);
        Location = (EditText)view.findViewById(R.id.FitnessLocation);
        Cost = (EditText)view.findViewById(R.id.FitnessCost);
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

    public boolean IsValid(){
        boolean result = true;

        if(vehicleRadioGroup.getCheckedRadioButtonId() == -1){
            Toast messageBox = Toast.makeText(getActivity() , "Select a vehicle to update" , Toast.LENGTH_SHORT);
            messageBox.show();
            return false;
        }

        if(!ExpiryDate.getText().toString().trim().matches("(((19|20)([2468][048]|[13579][26]|0[48])|2000)[/-]02[/-]29|((19|20)[0-9]{2}[/-](0[4678]|1[02])[/-](0[1-9]|[12][0-9]|30)|(19|20)[0-9]{2}[/-](0[1359]|11)[/-](0[1-9]|[12][0-9]|3[01])|(19|20)[0-9]{2}[/-]02[/-](0[1-9]|1[0-9]|2[0-8])))")){
            ExpiryDate.setError("Invalid date(YYYY-MM-DD)");
            return false;
        }

        if(ExpiryDate.getText().toString().trim().length() == 0){
            ExpiryDate.setError("Enter expiry date");
            return false;
        }

        if(Location.getText().toString().trim().length() == 0){
            Location.setError("Enter Fitness location");
            return false;
        }

        if(Cost.getText().toString().trim().length() == 0){
            Cost.setError("Enter Fitness cost");
            return false;
        }
        return  result;
    }

    public void SetText(){
        TableFitness.userId = TableUser.userId;
        TableFitness.vehicleId = vehicleRadioGroup.getCheckedRadioButtonId();
        TableFitness.renewalMonth = spinnerFitnessRenewal.getSelectedItem().toString();
        TableFitness.duration = SpinnerFitnessDuration.getSelectedItem().toString();
        TableFitness.expiryDate = ExpiryDate.getText().toString().trim() ;
        TableFitness.Location = Location.getText().toString().trim();
        TableFitness.cost = Float.parseFloat(Cost.getText().toString().trim());
    }

}
