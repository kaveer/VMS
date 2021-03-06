package com.example.avitah.Fragment.Vehicle;

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
import com.example.avitah.Tables.TableUser;
import com.example.avitah.Tables.TableVehicle;
import com.example.avitah.vms.R;


public class VehicleDetailsFragment extends Fragment {

    EditText regNo;
    Spinner make;
    EditText model;
    EditText classType;
    EditText type;
    EditText color;
    EditText chassisNo;
    EditText engineNo;
    EditText engineCapacity;
    RadioGroup fuel;
    EditText load;

    public VehicleDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Vehicle Details");

        final View view = inflater.inflate(R.layout.fragment_vehicle_details, container, false);
        InitializeEditText(view);

        if(TableVehicle.actionType == "update"){
            GetText();
        }

        if(TableVehicle.actionType == "insert"){
            Toast messageBox = Toast.makeText(getActivity() , "Please enter new vehicle details" , Toast.LENGTH_LONG);
            messageBox.show();
        }

        Button buttonSaveUpdate   = (Button)view.findViewById(R.id.BtnSaveVehicleDetails);
        buttonSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid() && TableVehicle.actionType == "update"){
                    SetEditText(view);
                    UpdateVehicle();
                    Toast messageBox = Toast.makeText(getActivity() , "Vehicle updated" , Toast.LENGTH_LONG);
                    messageBox.show();
               }
                if(isValid() && TableVehicle.actionType == "insert"){
                    SetEditText(view);
                    InsertVehicle();
                    Toast messageBox = Toast.makeText(getActivity() , "New vehicle added" , Toast.LENGTH_LONG);
                    messageBox.show();
                }
            }
        });

        Button buttonDelete   = (Button)view.findViewById(R.id.BtnDeleteVehicle);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TableVehicle.actionType == "update"){
                    DeleteVehicle();
                    Toast messageBox = Toast.makeText(getActivity() , "Vehicle deleted" , Toast.LENGTH_LONG);
                    messageBox.show();
                }
                if(TableVehicle.actionType == "insert"){

                    Toast messageBox = Toast.makeText(getActivity() , "Cannot delete when adding new record" , Toast.LENGTH_LONG);
                    messageBox.show();
                }
            }
        });



        return view;
    }

    public  void UpdateVehicle(){
        DBHandler DB = new DBHandler(getContext());
        DB.UpdateVehicle();
    }

    public  void DeleteVehicle(){
        DBHandler DB = new DBHandler(getContext());
        DB.DeleteVehicle();
    }

    public void InsertVehicle(){
        DBHandler DB = new DBHandler(getContext());
        DB.PostVehicle();
    }

    public void GetText(){
        regNo.setText(TableVehicle.regNo);
        make.getSelectedItem().toString();
        model.setText(TableVehicle.model);
        classType.setText(TableVehicle.classType);
        type.setText(TableVehicle.type);
        color.setText(TableVehicle.VehicleColor);
        chassisNo.setText(TableVehicle.chassisNo);
        engineNo.setText(TableVehicle.engineNo);
        engineCapacity.setText(String.valueOf(TableVehicle.engineCapacity));
        load.setText(String.valueOf(TableVehicle.load));

        if(TableVehicle.fuel.equals("Petrol") ){
            fuel.check(R.id.RbtPetrol);
        }
        else {
            fuel.check(R.id.RbtDiesel);
        }
    }

    public void SetEditText(View view){

        if(engineCapacity.getText().toString().trim().length() == 0){
            engineCapacity.setText("0");
        }
        if(load.getText().toString().trim().length() == 0){
            load.setText("0");
        }

        TableVehicle.regNo = regNo.getText().toString().trim();
        TableVehicle.make = make.getSelectedItem().toString();
        TableVehicle.model = model.getText().toString().trim();
        TableVehicle.classType = classType.getText().toString().trim();
        TableVehicle.type = type.getText().toString().trim();
        TableVehicle.VehicleColor = color.getText().toString().trim();
        TableVehicle.chassisNo = chassisNo.getText().toString().trim();
        TableVehicle.engineNo = engineNo.getText().toString().trim();
        TableVehicle.engineCapacity = Integer.parseInt(engineCapacity.getText().toString().trim());
        TableVehicle.fuel = ((RadioButton)view.findViewById(fuel.getCheckedRadioButtonId())).getText().toString();
        TableVehicle.load = Float.parseFloat(load.getText().toString().trim());
    }

    public  void InitializeEditText(View view){
        regNo = (EditText)view.findViewById(R.id.TxtRegNoVehicle);
        make = (Spinner) view.findViewById(R.id.spinnerCarMake);
        model = (EditText)view.findViewById(R.id.TxtModelVehicle);
        classType = (EditText)view.findViewById(R.id.TxtClassVehicle);
        type = (EditText)view.findViewById(R.id.TxtTypeVehicle);
        color = (EditText)view.findViewById(R.id.TxtColorVehicle);
        chassisNo = (EditText)view.findViewById(R.id.TxtChassisVehicle);
        engineNo = (EditText)view.findViewById(R.id.TxtEngineNoVehicle);
        engineCapacity = (EditText)view.findViewById(R.id.TxtEngineCapacityVehicle);
        fuel = (RadioGroup)view.findViewById(R.id.radioGroupFuel);
        load = (EditText)view.findViewById(R.id.TxtLoadVehicle);
    }

    public boolean isValid(){
        boolean result = true ;

        if (regNo.getText().toString().trim().length() == 0){
            regNo.setError("Enter Registration Number");
            return result = false;
        }
//        if(make.getText().toString().trim().length() == 0){
//            make.setError("Enter Make");
//            return result = false;
//        }
        if(model.getText().toString().trim().length() ==0){
            model.setError("Enter Model");
            return result = false;
        }
        if(classType.getText().toString().trim().length() ==0){
            classType.setError("Enter vehicle Class");
            return result = false;
        }
        if(type.getText().toString().trim().length() ==0){
            type.setError("Enter vehicle type");
            return result = false;
        }
        if(color.getText().toString().trim().length() ==0){
            color.setError("Enter vehicle color");
            return result = false;
        }
        if(chassisNo.getText().toString().trim().length() ==0){
            chassisNo.setError("Enter chassis number");
            return result = false;
        }
        if(engineCapacity.getText().toString().trim().length() ==0){
            engineCapacity.setError("Enter engine capacity in term of CC");
            return result = false;
        }
        if(fuel.getCheckedRadioButtonId() == -1){
            Toast messageBox = Toast.makeText(getActivity() , "Select fuel type" , Toast.LENGTH_SHORT);
            messageBox.show();
            return result = false;
        }
        return result;
    }

    public boolean GetVehicle(){
        boolean result = false;
        TableVehicle.userId = TableUser.userId;

        DBHandler DB = new DBHandler(getContext());
        DB.GetVehicle();
        return result;
    }

}
