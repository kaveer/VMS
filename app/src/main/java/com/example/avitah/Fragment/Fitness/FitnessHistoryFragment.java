package com.example.avitah.Fragment.Fitness;


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
import com.example.avitah.Tables.TableFitness;
import com.example.avitah.Tables.TableUser;
import com.example.avitah.vms.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FitnessHistoryFragment extends Fragment {

    ArrayList<TableFitness.FitnessNonStatic> fitnessList;
    RadioGroup fitnessRadioGroup;

    public FitnessHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Fitness History");

        View view = inflater.inflate(R.layout.fragment_fitness_history, container, false);

        int counter = 0;
        fitnessRadioGroup =(RadioGroup)view.findViewById(R.id.RadioGroupFitness);

        if(!GetFitness()) {
            Toast messageBox = Toast.makeText(getActivity() , "No Insurance!!" , Toast.LENGTH_SHORT);
            messageBox.show();
        }

        if(fitnessList.size() > 0){
            GenerateRadioButton(counter);
        }

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fabFitness);
        fab.setOnClickListener(new View.OnClickListener() {
            int counter = 0;
            @Override
            public void onClick(View view) {
                if(fitnessRadioGroup.getCheckedRadioButtonId() == -1){
                    Toast messageBox = Toast.makeText(getActivity() , "Select fitness to remove" , Toast.LENGTH_SHORT);
                    messageBox.show();
                }
                else {
                    DeleteFitness();
                    fitnessRadioGroup.removeAllViews();
                    GetFitness();
                    GenerateRadioButton(counter);
                    Toast messageBox = Toast.makeText(getActivity() , "fitness removed" , Toast.LENGTH_SHORT);
                    messageBox.show();
                }
                return;
            }
        });

        return view;
    }

    private void DeleteFitness() {
        TableFitness.fitnessId = fitnessRadioGroup.getCheckedRadioButtonId();
        DBHandler DB = new DBHandler(getContext());
        DB.DeleteFitness();
    }

    private void GenerateRadioButton(int counter) {
        TableFitness.count = fitnessList.size();

        RadioButton[] fitnessRadioButton = new RadioButton[TableFitness.count];
        fitnessRadioGroup.setOrientation(RadioGroup.VERTICAL);

        for (TableFitness.FitnessNonStatic fitness:  fitnessList) {
            fitnessRadioButton[counter]  = new RadioButton(getContext());
            fitnessRadioButton[counter].setText(fitness.expiryDate + "  " + fitness.Location + " ,Expired on " + fitness.expiryDate );
            fitnessRadioButton[counter].setId(fitness.fitnessId);
            fitnessRadioGroup.addView(fitnessRadioButton[counter]);
        }

    }

    private boolean GetFitness() {
        boolean result = true;
        TableFitness.userId = TableUser.userId;

        DBHandler DB = new DBHandler(getContext());
        fitnessList = DB.GetFitness();

        if(fitnessList.isEmpty()){
            result = false;
        }
        return result;
    }

}
