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
public class FuelFragment extends Fragment {

    EditText description;
    EditText location;
    EditText Fueldate;
    EditText amount;
    EditText totalCost;

    public FuelFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fuel, container, false);

        InitializeEditText(view);
        Fueldate.setText(GetDateNow());

        Button buttonSave   = (Button)view.findViewById(R.id.BtnSaveFuel);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsValid()){
                    SetEditText();

                    InsertFuel();

                    Toast messageBox = Toast.makeText(getActivity() , "Fuel record added successfully" , Toast.LENGTH_LONG);
                    messageBox.show();
                }
            }
        });


        return view;
    }

    public void InitializeEditText(View view){
        description = (EditText) view.findViewById(R.id.TxtDescriptionFuel);
        location = (EditText) view.findViewById(R.id.TxtLocationFuel);
        Fueldate = (EditText) view.findViewById(R.id.TxtDateFuel);
        amount = (EditText) view.findViewById(R.id.TxtAmountFuel);
        totalCost = (EditText) view.findViewById(R.id.TxtTotalCostFuel);
    }

    public String GetDateNow(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //import java.text.SimpleDateFormat instead of android.icu.text.simpleDateFormat
        String currentDate = dateFormat.format(calendar.getTime());
        return currentDate;
    }

    public void SetEditText(){

        TableFuel.userId = TableUser.userId;
        TableFuel.fuelDescription = description.getText().toString().trim();
        TableFuel.fuelLocation = location.getText().toString().trim();
        TableFuel.fuelDate =  Fueldate.getText().toString().trim();
        TableFuel.fuelAmount = Float.parseFloat(amount.getText().toString().trim());
        TableFuel.fuelTotalCost = Float.parseFloat(totalCost.getText().toString().trim());
        TableFuel.fuelStatus = "active";
    }

    public boolean IsValid(){
        boolean result = true;

        if(location.getText().toString().trim().length() == 0){
            location.setError("Enter location");
            return  result = false;
        }
        if(Fueldate.getText().toString().trim().length() == 0){
            Fueldate.setError("Enter date");
            return  result = false;
        }
        if(!Fueldate.getText().toString().trim().matches("(((19|20)([2468][048]|[13579][26]|0[48])|2000)[/-]02[/-]29|((19|20)[0-9]{2}[/-](0[4678]|1[02])[/-](0[1-9]|[12][0-9]|30)|(19|20)[0-9]{2}[/-](0[1359]|11)[/-](0[1-9]|[12][0-9]|3[01])|(19|20)[0-9]{2}[/-]02[/-](0[1-9]|1[0-9]|2[0-8])))")){
            Fueldate.setError("Invalid date(YYYY-MM-DD)");
            return  result = false;
        }
        if(amount.getText().toString().trim().length() == 0){
            amount.setError("Enter Amount");
            return  result = false;
        }
        if(totalCost.getText().toString().trim().length() == 0){
            totalCost.setError("Enter Total Cost");
            return  result = false;
        }
        return  result;
    }

    public void InsertFuel(){
        DBHandler DB = new DBHandler(getContext());
        DB.PostFuel();
    }
}
