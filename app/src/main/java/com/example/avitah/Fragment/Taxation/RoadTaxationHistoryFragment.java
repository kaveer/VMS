package com.example.avitah.Fragment.Taxation;


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
import com.example.avitah.Tables.TableRoadTaxation;
import com.example.avitah.Tables.TableUser;
import com.example.avitah.vms.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoadTaxationHistoryFragment extends Fragment {

    ArrayList<TableRoadTaxation.RoadTaxationNonStatic> taxList;
    RadioGroup taxRadioGroup;

    public RoadTaxationHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Tax History");

        View view = inflater.inflate(R.layout.fragment_road_taxation_history, container, false);
        int counter = 0;
        taxRadioGroup =(RadioGroup)view.findViewById(R.id.RadioGroupRoadTaxation);

        if(!GetTax()) {
            Toast messageBox = Toast.makeText(getActivity() , "No tax history!!" , Toast.LENGTH_SHORT);
            messageBox.show();
        }

        if(taxList.size() > 0){
            GenerateRadioButton(counter);
        }

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            int counter = 0;
            @Override
            public void onClick(View view) {
                if(taxRadioGroup.getCheckedRadioButtonId() == -1){
                    Toast messageBox = Toast.makeText(getActivity() , "Select tax to remove" , Toast.LENGTH_SHORT);
                    messageBox.show();
                }
                else {
                    DeleteTax();
                    taxRadioGroup.removeAllViews();
                    GetTax();
                    GenerateRadioButton(counter);
                    Toast messageBox = Toast.makeText(getActivity() , "Road taxation removed" , Toast.LENGTH_SHORT);
                    messageBox.show();
                }
                return;
            }
        });

        return view;
    }

    private void DeleteTax() {
        TableRoadTaxation.taxId = taxRadioGroup.getCheckedRadioButtonId();
        DBHandler DB = new DBHandler(getContext());
        DB.DeleteTax();
    }

    private void GenerateRadioButton(int counter) {
        TableRoadTaxation.taxCount = taxList.size();

        RadioButton[] taxRadioButton = new RadioButton[TableRoadTaxation.taxCount];
        taxRadioGroup.setOrientation(RadioGroup.VERTICAL);

        for (TableRoadTaxation.RoadTaxationNonStatic tax:  taxList) {
            taxRadioButton[counter]  = new RadioButton(getContext());
            taxRadioButton[counter].setText(tax.taxPaymentDate + "  " + tax.taxCost + " ,Expired on " + tax.expiryDate );
            taxRadioButton[counter].setId(tax.taxId);
            taxRadioGroup.addView(taxRadioButton[counter]);
        }
    }

    private boolean GetTax() {
        boolean result = true;
        TableRoadTaxation.userId = TableUser.userId;

        DBHandler DB = new DBHandler(getContext());
        taxList = DB.GetTax();

        if(taxList.isEmpty()){
            result = false;
        }
        return result;
    }

}
