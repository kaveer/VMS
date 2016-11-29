package com.example.avitah.Fragment.Parking;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.avitah.DbHandler.DBHandler;
import com.example.avitah.Tables.TableCarWash;
import com.example.avitah.Tables.TableParking;
import com.example.avitah.Tables.TableUser;
import com.example.avitah.vms.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class ParkingFragment extends Fragment {

    EditText date;
    EditText location;
    EditText duration;
    EditText description;
    EditText cost;

    public ParkingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parking, container, false);
        InitializeEDitText(view);
        date.setText(GetDateNow());

        Button buttonSave   = (Button)view.findViewById(R.id.BtnSaveParking);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsValid()){
                    SetEditText();
                    InsertParking();

                    Toast messageBox = Toast.makeText(getActivity() , "parking record added successfully" , Toast.LENGTH_LONG);
                    messageBox.show();
                }
            }
        });

        Button buttonHistory   = (Button)view.findViewById(R.id.BtnViewParkingHistory);
        buttonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigateToVehicleFragment();
            }
        });

        return view;
    }

    public void InitializeEDitText(View view){
        date = (EditText)view.findViewById(R.id.ParkingDate);
        location = (EditText)view.findViewById(R.id.ParkingLocation);
        duration = (EditText)view.findViewById(R.id.ParkingDuration);
        description = (EditText)view.findViewById(R.id.ParkingDescription);
        cost = (EditText)view.findViewById(R.id.ParkingCost);
    }

    public String GetDateNow(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //import java.text.SimpleDateFormat instead of android.icu.text.simpleDateFormat
        String currentDate = dateFormat.format(calendar.getTime());
        return currentDate;
    }

    public boolean IsValid(){
        boolean result = true;

        if(date.getText().toString().trim().length() == 0){
            date.setError("Enter date");
            return  result = false;
        }

        if(!date.getText().toString().trim().matches("(((19|20)([2468][048]|[13579][26]|0[48])|2000)[/-]02[/-]29|((19|20)[0-9]{2}[/-](0[4678]|1[02])[/-](0[1-9]|[12][0-9]|30)|(19|20)[0-9]{2}[/-](0[1359]|11)[/-](0[1-9]|[12][0-9]|3[01])|(19|20)[0-9]{2}[/-]02[/-](0[1-9]|1[0-9]|2[0-8])))")){
            date.setError("Invalid date(YYYY-MM-DD)");
            return  result = false;
        }

        if(location.getText().toString().trim().length() == 0){
            location.setError("Enter Location");
            return  result = false;
        }

        if(duration.getText().toString().trim().length() == 0){
            duration.setError("Enter duration");
            return  result = false;
        }

        if(cost.getText().toString().trim().length() == 0){
            cost.setError("Enter parking cost");
            return  result = false;
        }

        return result;
    }

    private void NavigateToVehicleFragment() {
        ParkingHistoryFragment fragment = new ParkingHistoryFragment();
        android.support.v4.app.FragmentTransaction fmTransaction = getFragmentManager().beginTransaction();
        fmTransaction.replace(R.id.Frame_container, fragment);
        fmTransaction.commit();
    }

    public void SetEditText(){
        TableParking.date = date.getText().toString().trim();
        TableParking.Location = location.getText().toString().trim();
        TableParking.duration = Float.parseFloat(duration.getText().toString().trim());
        TableParking.Description = description.getText().toString().trim();
        TableParking.Cost = Float.parseFloat(cost.getText().toString().trim());
    }

    public void InsertParking(){
        TableParking.userId = TableUser.userId;
        DBHandler DB = new DBHandler(getContext());
        DB.PostParking();
    }

}
