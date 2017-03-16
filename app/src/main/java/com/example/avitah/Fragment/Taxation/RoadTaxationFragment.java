package com.example.avitah.Fragment.Taxation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.avitah.DbHandler.DBHandler;
import com.example.avitah.Tables.TableRoadTaxation;
import com.example.avitah.Tables.TableUser;
import com.example.avitah.Tables.TableVehicle;
import com.example.avitah.vms.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RoadTaxationFragment extends Fragment {

    int counter = 0;
    ArrayList<TableVehicle.VehicleNonStatic> vehicleList;

    RadioGroup vehicleRadioGroup;
    EditText taxDescription;
    EditText taxPaymentDate;
    EditText taxCost;
    EditText taxExpiryDate;


    public RoadTaxationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Tax");

        View view = inflater.inflate(R.layout.fragment_road_taxation, container, false);
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

        Button buttonSave   = (Button)view.findViewById(R.id.BtnSaveTax);
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
                    InsertRoadTaxation();

                    Toast messageBox = Toast.makeText(getActivity() , "New Road Taxation added" , Toast.LENGTH_SHORT);
                    messageBox.show();
                }

            }
        });

        Button buttonViewHistory   = (Button)view.findViewById(R.id.BtnViewTaxHistory);
        buttonViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RoadTaxationHistoryFragment fragment = new RoadTaxationHistoryFragment();
                android.support.v4.app.FragmentTransaction fmTransaction = getFragmentManager().beginTransaction();
                fmTransaction.replace(R.id.Frame_container, fragment);
                fmTransaction.commit();


            }
        });

        return view;
    }

    private void InsertRoadTaxation() {
        DBHandler DB = new DBHandler(getContext());
        DB.PostRoadTaxation();
    }

    private void SetText() {
        TableRoadTaxation.userId = TableUser.userId;
        TableRoadTaxation.vehicleId = vehicleRadioGroup.getCheckedRadioButtonId();
        TableRoadTaxation.taxDescription = taxDescription.getText().toString().trim();
        TableRoadTaxation.taxPaymentDate = taxPaymentDate.getText().toString().trim();
        TableRoadTaxation.taxCost =Float.parseFloat(taxCost.getText().toString().trim()) ;
        TableRoadTaxation.expiryDate = taxExpiryDate.getText().toString().trim();
    }

    private boolean IsValid() {
        boolean result = true;

        if(vehicleRadioGroup.getCheckedRadioButtonId() == -1){
            Toast messageBox = Toast.makeText(getActivity() , "Select a vehicle to update" , Toast.LENGTH_SHORT);
            messageBox.show();
            return false;
        }

        if(taxPaymentDate.getText().toString().trim().length() == 0){
            taxPaymentDate.setError("Enter Payment date");
            return false;
        }

        if(!taxPaymentDate.getText().toString().trim().matches("(((19|20)([2468][048]|[13579][26]|0[48])|2000)[/-]02[/-]29|((19|20)[0-9]{2}[/-](0[4678]|1[02])[/-](0[1-9]|[12][0-9]|30)|(19|20)[0-9]{2}[/-](0[1359]|11)[/-](0[1-9]|[12][0-9]|3[01])|(19|20)[0-9]{2}[/-]02[/-](0[1-9]|1[0-9]|2[0-8])))")){
            taxPaymentDate.setError("Invalid date(YYYY-MM-DD)");
            return false;
        }

        if(taxCost.getText().toString().trim().length() == 0){
            taxCost.setError("Enter cost");
            return false;
        }

        if(taxExpiryDate.getText().toString().trim().length() == 0){
            taxExpiryDate.setError("Enter expiry date");
            return false;
        }

        if(!taxExpiryDate.getText().toString().trim().matches("(((19|20)([2468][048]|[13579][26]|0[48])|2000)[/-]02[/-]29|((19|20)[0-9]{2}[/-](0[4678]|1[02])[/-](0[1-9]|[12][0-9]|30)|(19|20)[0-9]{2}[/-](0[1359]|11)[/-](0[1-9]|[12][0-9]|3[01])|(19|20)[0-9]{2}[/-]02[/-](0[1-9]|1[0-9]|2[0-8])))")){
            taxExpiryDate.setError("Invalid date(YYYY-MM-DD)");
            return false;
        }

        return  result;
    }

    private boolean GetVehicle() {
        boolean result = true;
        TableVehicle.userId = TableUser.userId;

        DBHandler DB = new DBHandler(getContext());
        vehicleList = DB.GetVehicle();

        if(vehicleList.isEmpty()){
            result = false;
        }
        return result;
    }

    private void InitializeText(View view) {
        vehicleRadioGroup =(RadioGroup)view.findViewById(R.id.TaxVehicleRadioButton);
        taxDescription = (EditText)view.findViewById(R.id.TaxDescription);
        taxPaymentDate = (EditText)view.findViewById(R.id.TaxPayDate);
        taxCost = (EditText)view.findViewById(R.id.TaxCost);
        taxExpiryDate = (EditText)view.findViewById(R.id.TaxExpiryDate);
    }

}
