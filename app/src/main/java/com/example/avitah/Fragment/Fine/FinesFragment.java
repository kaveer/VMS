package com.example.avitah.Fragment.Fine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.avitah.DbHandler.DBHandler;
import com.example.avitah.Tables.TableFines;
import com.example.avitah.Tables.TableUser;
import com.example.avitah.vms.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class FinesFragment extends Fragment {

    EditText court;
    EditText date;
    EditText charge;
    EditText breachOfArticle;
    EditText ordinance;
    EditText issuedBy;

    public FinesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fines, container, false);

        InitializeEditText(view);
        date.setText(GetDateNow());

        Button buttonSave   = (Button)view.findViewById(R.id.BtnSaveFuel);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsValid()){
                    SetEditText();
                    InsertFine();

                    Toast messageBox = Toast.makeText(getActivity() , "Fine record added successfully" , Toast.LENGTH_LONG);
                    messageBox.show();
                }
            }
        });

        Button buttonViewHistory   = (Button)view.findViewById(R.id.BtnFuelHistory);
        buttonViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigateToVehicleFragment();
            }
        });

        Button buttonFineGraph   = (Button)view.findViewById(R.id.BtnFineGraph);
        buttonFineGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FineGraphFragment fragment = new FineGraphFragment();
                android.support.v4.app.FragmentTransaction fmTransaction = getFragmentManager().beginTransaction();
                fmTransaction.replace(R.id.Frame_container, fragment);
                fmTransaction.commit();
            }
        });

        return view;
    }

    public boolean IsValid(){
        boolean result = true;

        if(court.getText().toString().trim().length() == 0){
            court.setError("Enter district court to be attended");
            return false;
        }
        if(date.getText().toString().trim().length() == 0){
            date.setError("Enter date");
            return false;
        }
        if(!date.getText().toString().trim().matches("(((19|20)([2468][048]|[13579][26]|0[48])|2000)[/-]02[/-]29|((19|20)[0-9]{2}[/-](0[4678]|1[02])[/-](0[1-9]|[12][0-9]|30)|(19|20)[0-9]{2}[/-](0[1359]|11)[/-](0[1-9]|[12][0-9]|3[01])|(19|20)[0-9]{2}[/-]02[/-](0[1-9]|1[0-9]|2[0-8])))")){
            date.setError("Invalid date(YYYY-MM-DD)");
            return false;
        }
        if(charge.getText().toString().trim().length() == 0){
            charge.setError("Enter charge");
            return false;
        }
        if(breachOfArticle.getText().toString().trim().length() == 0){
            breachOfArticle.setError("Enter breach description");
            return false;
        }
        if(ordinance.getText().toString().trim().length() == 0){
            ordinance.setError("Enter ordinance description");
            return false;
        }
        if(issuedBy.getText().toString().trim().length() == 0){
            issuedBy.setError("Enter police officer name");
            return false;
        }

        return  result;
    }

    public void InitializeEditText(View view){
        court = (EditText) view.findViewById(R.id.TxtCourtFine);
        date = (EditText) view.findViewById(R.id.TxtDateFine);
        charge = (EditText) view.findViewById(R.id.TxtChargeFine);
        breachOfArticle = (EditText) view.findViewById(R.id.TxtBreachOfArticleFine);
        ordinance = (EditText) view.findViewById(R.id.TxtOrdinanceFine);
        issuedBy = (EditText) view.findViewById(R.id.TxtIssuedByFine);
    }

    public String GetDateNow(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //import java.text.SimpleDateFormat instead of android.icu.text.simpleDateFormat
        String currentDate = dateFormat.format(calendar.getTime());
        return currentDate;
    }

    private void NavigateToVehicleFragment() {
        FineHistoryFragment fragment = new FineHistoryFragment();
        android.support.v4.app.FragmentTransaction fmTransaction = getFragmentManager().beginTransaction();
        fmTransaction.replace(R.id.Frame_container, fragment);
        fmTransaction.commit();
    }

    public void SetEditText(){

        TableFines.userId = TableUser.userId;
        TableFines.court = court.getText().toString().trim();
        TableFines.fineDate = date.getText().toString().trim();
        TableFines.charge = Float.parseFloat(charge.getText().toString().trim());
        TableFines.breachOfArticle = breachOfArticle.getText().toString().trim();
        TableFines.ordinace = ordinance.getText().toString().trim();
        TableFines.issuedBy = issuedBy.getText().toString().trim();
        TableFines.fineStatus = "ACTIVE";
    }

    public void InsertFine(){
        DBHandler DB = new DBHandler(getContext());
        DB.PostFine();
    }

}
