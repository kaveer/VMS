package com.example.avitah.Fragment.Insurance;


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
import com.example.avitah.Tables.TableInsurance;
import com.example.avitah.Tables.TableUser;
import com.example.avitah.Tables.TableVehicle;
import com.example.avitah.vms.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class InsuranceFragment extends Fragment {

    int counter = 0;
    ArrayList<TableVehicle.VehicleNonStatic> vehicleList;

    RadioGroup vehicleRadioGroup;
    Spinner spinnerInsuranceType;
    EditText name;
    EditText policyNo;
    EditText certificateNo;
    EditText policyHolder;
    EditText effictiveDate;
    EditText expiryDate;
    EditText cost;

    public InsuranceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        getActivity().setTitle("Insurance");

        View view = inflater.inflate(R.layout.fragment_insurance, container, false);
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

        Button buttonSave   = (Button)view.findViewById(R.id.BtnSaveInsurance);
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

                    Toast messageBox = Toast.makeText(getActivity() , "New insurance added" , Toast.LENGTH_SHORT);
                    messageBox.show();
                }

            }
        });

        Button buttonViewHistory   = (Button)view.findViewById(R.id.BtnViewInsuranceHistory);
        buttonViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InsuranceHistoryFragment fragment = new InsuranceHistoryFragment();
                android.support.v4.app.FragmentTransaction fmTransaction = getFragmentManager().beginTransaction();
                fmTransaction.replace(R.id.Frame_container, fragment);
                fmTransaction.commit();


            }


        });

        return view;


    }

    public void SetText(){
        TableInsurance.userId = TableUser.userId;
        TableInsurance.insuranceType = spinnerInsuranceType.getSelectedItem().toString();
        TableInsurance.selectVehicleId = vehicleRadioGroup.getCheckedRadioButtonId();
        TableInsurance.insuranceName = name.getText().toString().trim();
        TableInsurance.policyNo =Integer.parseInt(policyNo.getText().toString().trim()) ;
        TableInsurance.certificateNo = certificateNo.getText().toString().trim();
        TableInsurance.policyHolder = policyHolder.getText().toString().trim();
        TableInsurance.effectiveDate = effictiveDate.getText().toString().trim();
        TableInsurance.expiryDate = expiryDate.getText().toString().trim();
        TableInsurance.insuranceCost = Float.parseFloat(cost.getText().toString().trim()) ;
    }

    public void InitializeText(View view){
        vehicleRadioGroup =(RadioGroup)view.findViewById(R.id.insuranceVehicleRadioButton);
        spinnerInsuranceType = (Spinner)view.findViewById(R.id.spinnerInsuranceType);
        name = (EditText)view.findViewById(R.id.InsuranceName);
        policyNo = (EditText)view.findViewById(R.id.InsurancePolicyNo);
        certificateNo = (EditText)view.findViewById(R.id.InsuranceCertificateNo);
        policyHolder = (EditText)view.findViewById(R.id.InsurancePolicyHolder);
        effictiveDate = (EditText)view.findViewById(R.id.InsuranceEffectiveDate);
        expiryDate = (EditText)view.findViewById(R.id.InsuranceExpiryDate);
        cost = (EditText)view.findViewById(R.id.InsuranceCost);
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

        if(name.getText().toString().trim().length() == 0){
            name.setError("Enter insurance company name");
            return false;
        }

        if(policyNo.getText().toString().trim().length() == 0){
            policyNo.setError("Enter policy no");
            return false;
        }

        if(policyHolder.getText().toString().trim().length() == 0){
            policyHolder.setError("Enter name of policy holder");
            return false;
        }

        if(effictiveDate.getText().toString().trim().length() == 0){
            effictiveDate.setError("Enter Effective date");
            return false;
        }

        if(!effictiveDate.getText().toString().trim().matches("(((19|20)([2468][048]|[13579][26]|0[48])|2000)[/-]02[/-]29|((19|20)[0-9]{2}[/-](0[4678]|1[02])[/-](0[1-9]|[12][0-9]|30)|(19|20)[0-9]{2}[/-](0[1359]|11)[/-](0[1-9]|[12][0-9]|3[01])|(19|20)[0-9]{2}[/-]02[/-](0[1-9]|1[0-9]|2[0-8])))")){
            effictiveDate.setError("Invalid date(YYYY-MM-DD)");
            return false;
        }

        if(expiryDate.getText().toString().trim().length() == 0){
            expiryDate.setError("Enter expiry date");
            return false;
        }



        if(!expiryDate.getText().toString().trim().matches("(((19|20)([2468][048]|[13579][26]|0[48])|2000)[/-]02[/-]29|((19|20)[0-9]{2}[/-](0[4678]|1[02])[/-](0[1-9]|[12][0-9]|30)|(19|20)[0-9]{2}[/-](0[1359]|11)[/-](0[1-9]|[12][0-9]|3[01])|(19|20)[0-9]{2}[/-]02[/-](0[1-9]|1[0-9]|2[0-8])))")){
            expiryDate.setError("Invalid date(YYYY-MM-DD)");
            return false;
        }

        if(!CompareDate(expiryDate.getText().toString().trim(), GetDateNow())){
            expiryDate.setError("Expiry date cannot be before actual date");
            return false;
        }

        if(cost.getText().toString().trim().length() == 0){
            cost.setError("Enter insurance cost");
            return false;
        }
        return  result;
    }

    private boolean CompareDate(String expiry, String actual) {
        boolean result = true;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date expiryDate = sdf.parse(expiry);
            Date actualDate = sdf.parse(actual);

            if (expiryDate.before(actualDate)){
                return false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void InsertInsurance(){
        DBHandler DB = new DBHandler(getContext());
        DB.PostInsurance();
    }

    public String GetDateNow(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //import java.text.SimpleDateFormat instead of android.icu.text.simpleDateFormat
        String currentDate = dateFormat.format(calendar.getTime());
        return currentDate;
    }



}
