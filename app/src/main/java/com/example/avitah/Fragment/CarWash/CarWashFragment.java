package com.example.avitah.Fragment.CarWash;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.avitah.DbHandler.DBHandler;
import com.example.avitah.Tables.TableUser;
import com.example.avitah.vms.R;
import com.example.avitah.Tables.TableCarWash;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarWashFragment extends Fragment {

    EditText date;
    EditText location;
    EditText description;
    EditText cost;

    public CarWashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_wash, container, false);
        InitializeEDitText(view);
        date.setText(GetDateNow());

        Button buttonSave   = (Button)view.findViewById(R.id.Btn_carWashSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsValid()){
                    SetEditText();
                    InsertCarWash();

                    Toast messageBox = Toast.makeText(getActivity() , "Fuel record added successfully" , Toast.LENGTH_LONG);
                    messageBox.show();
                }
            }
        });

        Button buttonHistory   = (Button)view.findViewById(R.id.BtnCarWashHistory);
        buttonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigateToVehicleFragment();
            }
        });

        return view;
    }

    private void NavigateToVehicleFragment() {
        CarWashHistoryFragment fragment = new CarWashHistoryFragment();
        android.support.v4.app.FragmentTransaction fmTransaction = getFragmentManager().beginTransaction();
        fmTransaction.replace(R.id.Frame_container, fragment);
        fmTransaction.commit();
    }

    public void InitializeEDitText(View view){
        date = (EditText)view.findViewById(R.id.washDate);
        location = (EditText)view.findViewById(R.id.washLocation);
        description = (EditText)view.findViewById(R.id.washDescription);
        cost = (EditText)view.findViewById(R.id.washCost);
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

        if(description.getText().toString().trim().length() == 0){
            description.setError("Enter description");
            return  result = false;
        }

        if(cost.getText().toString().trim().length() == 0){
            cost.setError("Enter car wash cost");
            return  result = false;
        }

        return result;
    }

    public void SetEditText(){
        TableCarWash.date = date.getText().toString().trim();
        TableCarWash.Location = location.getText().toString().trim();
        TableCarWash.Description = description.getText().toString().trim();
        TableCarWash.Cost = Float.parseFloat(cost.getText().toString().trim());
    }

    public void InsertCarWash(){
        TableCarWash.userId = TableUser.userId;
        DBHandler DB = new DBHandler(getContext());
        DB.PostCarWash();
    }


}
