package com.example.avitah.Fragment.Accident;


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
import com.example.avitah.Tables.TableAccident;
import com.example.avitah.Tables.TableUser;
import com.example.avitah.vms.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccidentHistoryFragment extends Fragment {

    ArrayList<TableAccident.AccidentNonStatic> accidentList;
    RadioGroup accidentRadioGroup;

    public AccidentHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Accident History");

        int counter = 0;
        View view = inflater.inflate(R.layout.fragment_accident_history, container, false);
        accidentRadioGroup =(RadioGroup)view.findViewById(R.id.RadioGroupAccident);

        if(!GetAccident()) {
            Toast messageBox = Toast.makeText(getActivity() , "No Accident!!" , Toast.LENGTH_SHORT);
            messageBox.show();
        }

        if(accidentList.size() > 0){
            GenerateRadioButton(counter);
        }

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fabAccident);
        fab.setOnClickListener(new View.OnClickListener() {
            int counter = 0;
            @Override
            public void onClick(View view) {
                if(accidentRadioGroup.getCheckedRadioButtonId() == -1){
                    Toast messageBox = Toast.makeText(getActivity() , "Select accident to remove" , Toast.LENGTH_SHORT);
                    messageBox.show();
                }
                else {
                    DeleteAccident();
                    accidentRadioGroup.removeAllViews();
                    GetAccident();
                    GenerateRadioButton(counter);
                    Toast messageBox = Toast.makeText(getActivity() , "accident removed" , Toast.LENGTH_SHORT);
                    messageBox.show();
                }
                return;
            }
        });

        return view;
    }

    public boolean GetAccident(){
        boolean result = true;
        TableAccident.userId = TableUser.userId;

        DBHandler DB = new DBHandler(getContext());
        accidentList = DB.GetAccident();

        if(accidentList.isEmpty()){
            result = false;
        }
        return result;
    }

    public void GenerateRadioButton(int counter){
        TableAccident.count = accidentList.size();

        RadioButton[] accidentRadioButton = new RadioButton[TableAccident.count];
        accidentRadioGroup.setOrientation(RadioGroup.VERTICAL);

        for (TableAccident.AccidentNonStatic accident:  accidentList) {
            accidentRadioButton[counter]  = new RadioButton(getContext());
            accidentRadioButton[counter].setText("Accident with " + accident.acciName + " owned  " + accident.makeModel + " ,with " + accident.regNo );
            accidentRadioButton[counter].setId(accident.accidentId);
            accidentRadioGroup.addView(accidentRadioButton[counter]);
        }
    }

    public void DeleteAccident(){
        TableAccident.accidentId = accidentRadioGroup.getCheckedRadioButtonId();
        DBHandler DB = new DBHandler(getContext());
        DB.DeleteAccident();
    }

}
