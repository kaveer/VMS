package com.example.avitah.Fragment.OtherExpense;


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
import com.example.avitah.Tables.TableOtherExpense;
import com.example.avitah.Tables.TableUser;
import com.example.avitah.vms.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherExpenseHistoryFragment extends Fragment {

    ArrayList<TableOtherExpense.OtherExpenseNonStatic> otherExpenseList;
    RadioGroup otherExpenseRadioGroup;

    public OtherExpenseHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Other Expense History");

        int counter = 0;
        View view = inflater.inflate(R.layout.fragment_other_expense_history, container, false);
        otherExpenseRadioGroup = (RadioGroup)view.findViewById(R.id.RadioGroupOtherExpense);

        if(!GetOtherExpense()) {
            Toast messageBox = Toast.makeText(getActivity() , "No expense record!!" , Toast.LENGTH_SHORT);
            messageBox.show();
        }

        if(otherExpenseList.size() > 0){
            GenerateRadioButton(counter);
        }

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fabOtherExpense);
        fab.setOnClickListener(new View.OnClickListener() {
            int counter = 0;
            @Override
            public void onClick(View view) {
                if(otherExpenseRadioGroup.getCheckedRadioButtonId() == -1){
                    Toast messageBox = Toast.makeText(getActivity() , "Select expense record to remove" , Toast.LENGTH_SHORT);
                    messageBox.show();
                }
                else {
                    DeleteParking();
                    otherExpenseRadioGroup.removeAllViews();
                    GetOtherExpense();
                    GenerateRadioButton(counter);
                    Toast messageBox = Toast.makeText(getActivity() , "expense record removed" , Toast.LENGTH_SHORT);
                    messageBox.show();
                }
                return;
            }
        });
        return view;
    }

    public boolean GetOtherExpense(){
        boolean result = true;
        TableOtherExpense.userId = TableUser.userId;

        DBHandler DB = new DBHandler(getContext());
        otherExpenseList = DB.GetOhterExpense();

        if(otherExpenseList.isEmpty()){
            result = false;
        }
        return result;
    }

    public void GenerateRadioButton(int counter){
        TableOtherExpense.otherExpenseCount = otherExpenseList.size();

        RadioButton[] otherExpenseRadioButton = new RadioButton[TableOtherExpense.otherExpenseCount];
        otherExpenseRadioGroup.setOrientation(RadioGroup.VERTICAL);

        for (TableOtherExpense.OtherExpenseNonStatic otherExpense:  otherExpenseList) {
            otherExpenseRadioButton[counter]  = new RadioButton(getContext());
            otherExpenseRadioButton[counter].setText(String.format("Other expense occurred on %s with a total cost of %s. More info: %s", otherExpense.date, String.valueOf(otherExpense.Cost), otherExpense.Description) );
            otherExpenseRadioButton[counter].setId(otherExpense.otherExpenseId);
            otherExpenseRadioGroup.addView(otherExpenseRadioButton[counter]);
        }
    }

    public void DeleteParking(){
        TableOtherExpense.otherExpenseId = otherExpenseRadioGroup.getCheckedRadioButtonId();
        DBHandler DB = new DBHandler(getContext());
        DB.DeleteOtherExpense();
    }

}
