package com.example.avitah.Fragment.RepairAndServicing;


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
import com.example.avitah.Tables.TableRepair;
import com.example.avitah.Tables.TableUser;
import com.example.avitah.vms.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RepairAndServicingHistoryFragment extends Fragment {
    ArrayList<TableRepair.RepairNonStatic> repairList;
    RadioGroup repairRadioGroup;

    public RepairAndServicingHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Repair & Servicing History");

        View view = inflater.inflate(R.layout.fragment_repair_and_servicing_history, container, false);
        int counter = 0;
        repairRadioGroup = (RadioGroup)view.findViewById(R.id.RadioGroupRepair);

        if(!GetRepair()) {
            Toast messageBox = Toast.makeText(getActivity() , "No repair and servicing record!!" , Toast.LENGTH_SHORT);
            messageBox.show();
        }

        if(repairList.size() > 0){
            GenerateRadioButton(counter);
        }

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.FloatRemoveRepair);
        fab.setOnClickListener(new View.OnClickListener() {
            int counter = 0;
            @Override
            public void onClick(View view) {
                if(repairRadioGroup.getCheckedRadioButtonId() == -1){
                    Toast messageBox = Toast.makeText(getActivity() , "Select repair and servicing record to remove" , Toast.LENGTH_SHORT);
                    messageBox.show();
                }
                else {
                    DeleteRepair();
                    repairRadioGroup.removeAllViews();
                    GetRepair();
                    GenerateRadioButton(counter);
                    Toast messageBox = Toast.makeText(getActivity() , "Repair and servicing record removed" , Toast.LENGTH_SHORT);
                    messageBox.show();
                }
                return;
            }
        });

        return view;
    }

    private void DeleteRepair() {
        TableRepair.repairId = repairRadioGroup.getCheckedRadioButtonId();
        DBHandler DB = new DBHandler(getContext());
        DB.DeleteRepair();
    }

    private void GenerateRadioButton(int counter) {
        TableRepair.repairCount = repairList.size();

        RadioButton[] repairRadioButton = new RadioButton[TableRepair.repairCount];
        repairRadioGroup.setOrientation(RadioGroup.VERTICAL);

        for (TableRepair.RepairNonStatic repair:  repairList) {
            repairRadioButton[counter]  = new RadioButton(getContext());
            repairRadioButton[counter].setText(String.format("Repaired on %s with a total cost of %s. More info: %s, parts added: %s. Mileage: %s and servicing selected: %s", repair.repairDate, String.valueOf(repair.Cost), repair.repairDescription, repair.repairAdded, String.valueOf(repair.repairMileage) , repair.repairServicing));
            repairRadioButton[counter].setId(repair.repairId);
            repairRadioGroup.addView(repairRadioButton[counter]);
        }
    }

    private boolean GetRepair() {
        boolean result = true;
        TableRepair.userId = TableUser.userId;

        DBHandler DB = new DBHandler(getContext());
        repairList = DB.GetRepair();

        if(repairList.isEmpty()){
            result = false;
        }
        return result;
    }

}
