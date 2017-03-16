package com.example.avitah.Fragment.RepairAndServicing;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.avitah.DbHandler.DBHandler;
import com.example.avitah.Tables.TableRepair;
import com.example.avitah.Tables.TableUser;
import com.example.avitah.vms.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class RepairServicingFragment extends Fragment {

    EditText repairDate;
    EditText repairDescription;
    EditText repairAdded;
    EditText repairCost;

    public RepairServicingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Repair & Servicing");

        View view = inflater.inflate(R.layout.fragment_repair_servicing, container, false);
        InitializeEDitText(view);

        repairDate.setText(GetDateNow());

        Button buttonSave   = (Button)view.findViewById(R.id.BtnSaveRepair);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsValid()){
                    SetEditText();
                    InsertRepair();

                    Toast messageBox = Toast.makeText(getActivity() , "Repair and servicing record added successfully" , Toast.LENGTH_LONG);
                    messageBox.show();
                }
            }
        });

        Button buttonHistory   = (Button)view.findViewById(R.id.BtnRepairHistory);
        buttonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigateToVehicleFragment();
            }
        });

        Button buttonRepairGraph   = (Button)view.findViewById(R.id.BtnRepairGraph);
        buttonRepairGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RepairGraphFragment fragment = new RepairGraphFragment();
                android.support.v4.app.FragmentTransaction fmTransaction = getFragmentManager().beginTransaction();
                fmTransaction.replace(R.id.Frame_container, fragment);
                fmTransaction.commit();
            }
        });

        return view;
    }

    private void NavigateToVehicleFragment() {
        RepairAndServicingHistoryFragment fragment = new RepairAndServicingHistoryFragment();
        android.support.v4.app.FragmentTransaction fmTransaction = getFragmentManager().beginTransaction();
        fmTransaction.replace(R.id.Frame_container, fragment);
        fmTransaction.commit();
    }

    private void InsertRepair() {
        TableRepair.userId = TableUser.userId;
        DBHandler DB = new DBHandler(getContext());
        DB.PostRepair();
    }

    private void SetEditText() {
        TableRepair.repairDate = repairDate.getText().toString().trim();
        TableRepair.repairDescription = repairDescription.getText().toString().trim();
        TableRepair.repairAdded = repairAdded.getText().toString().trim();
        TableRepair.Cost = Float.parseFloat(repairCost.getText().toString().trim());
    }

    private boolean IsValid() {
        boolean result = true;

        if(repairDate.getText().toString().trim().length() == 0){
            repairDate.setError("Enter date");
            return false;
        }

        if(!repairDate.getText().toString().trim().matches("(((19|20)([2468][048]|[13579][26]|0[48])|2000)[/-]02[/-]29|((19|20)[0-9]{2}[/-](0[4678]|1[02])[/-](0[1-9]|[12][0-9]|30)|(19|20)[0-9]{2}[/-](0[1359]|11)[/-](0[1-9]|[12][0-9]|3[01])|(19|20)[0-9]{2}[/-]02[/-](0[1-9]|1[0-9]|2[0-8])))")){
            repairDate.setError("Invalid date(YYYY-MM-DD)");
            return false;
        }

        if(repairDescription.getText().toString().trim().length() == 0){
            repairDescription.setError("Enter description");
            return false;
        }

        if(repairCost.getText().toString().trim().length() == 0){
            repairCost.setError("Enter cost");
            return false;
        }

        return result;
    }

    private String GetDateNow() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //import java.text.SimpleDateFormat instead of android.icu.text.simpleDateFormat
        String currentDate = dateFormat.format(calendar.getTime());
        return currentDate;
    }

    private void InitializeEDitText(View view) {
        repairDate = (EditText)view.findViewById(R.id.repairDate);
        repairDescription = (EditText)view.findViewById(R.id.RepairDescription);
        repairAdded = (EditText)view.findViewById(R.id.RepairParts);
        repairCost = (EditText)view.findViewById(R.id.repairCost);
    }

}
