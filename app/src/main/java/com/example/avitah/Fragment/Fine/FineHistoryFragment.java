package com.example.avitah.Fragment.Fine;


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
import com.example.avitah.Tables.TableFines;
import com.example.avitah.Tables.TableUser;
import com.example.avitah.vms.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FineHistoryFragment extends Fragment {

    ArrayList<TableFines.FineNonStatic> fineList;
    RadioGroup fineRadioGroup;

    public FineHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fine_history, container, false);

        int counter = 0;
        fineRadioGroup =(RadioGroup)view.findViewById(R.id.RadioGroupFine);

        if(!GetFine()) {
            Toast messageBox = Toast.makeText(getActivity() , "No fine record!!" , Toast.LENGTH_SHORT);
            messageBox.show();
        }

        if(fineList.size() > 0){
            GenerateRadioButton(counter);
        }


        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.FloatRemoveFine);
        fab.setOnClickListener(new View.OnClickListener() {
            int counter = 0;
            @Override
            public void onClick(View view) {
                if(fineRadioGroup.getCheckedRadioButtonId() == -1){
                    Toast messageBox = Toast.makeText(getActivity() , "Select fine to remove" , Toast.LENGTH_SHORT);
                    messageBox.show();
                }
                else {
                    DeleteInsurance();
                    fineRadioGroup.removeAllViews();
                    GetFine();
                    GenerateRadioButton(counter);
                    Toast messageBox = Toast.makeText(getActivity() , "Fine removed" , Toast.LENGTH_SHORT);
                    messageBox.show();
                }
                return;
            }
        });

        return view;
    }

    public boolean GetFine(){
        boolean result = true;
        TableFines.userId = TableUser.userId;

        DBHandler DB = new DBHandler(getContext());
        fineList = DB.GetFine();

        if(fineList.isEmpty()){
            result = false;
        }
        return result;
    }

    public void GenerateRadioButton(int counter){
        TableFines.fineCount = fineList.size();

        RadioButton[] fineRadioButton = new RadioButton[TableFines.fineCount];
        fineRadioGroup.setOrientation(RadioGroup.VERTICAL);

        for (TableFines.FineNonStatic fine:  fineList) {
            fineRadioButton[counter]  = new RadioButton(getContext());
            fineRadioButton[counter].setText("Added at " + fine.fineDate + " ,cost :" + fine.charge);
            fineRadioButton[counter].setId(fine.FineId);
            fineRadioGroup.addView(fineRadioButton[counter]);
        }
    }

    public void DeleteInsurance(){
        TableFines.FineId = fineRadioGroup.getCheckedRadioButtonId();
        DBHandler DB = new DBHandler(getContext());
        DB.DeleteFine();
    }

}
