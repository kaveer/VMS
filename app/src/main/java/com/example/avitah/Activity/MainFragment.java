package com.example.avitah.Activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.avitah.DbHandler.DBHandler;
import com.example.avitah.Tables.TableUser;
import com.example.avitah.Tables.TableVehicle;
import com.example.avitah.vms.R;

import java.util.ArrayList;


public class MainFragment extends Fragment {

    ListView listViewVehicle;
   // ListView listViewOwnerDetails;
    ListView listViewAnnualExp;
    ListView listViewExp;

    TextView ownerDetails;

    String fuel = "Total Fuel expenses :";
    String repairAndServicing = "Total repair and servicing expenses :";
    String fine = "Total fine expenses :";
    String carWash = "Total car wash expenses";
    String parking = "Total parking expenses :";
    String otherExpenses = "Total Other expenses :";

    String insurance = "Total insurance expenses :";
    String fitness = "Total fitness expenses :";
    String roadTaxation = "Total road taxation expenses :";

    ArrayList<TableVehicle.VehicleNonStatic> vehicleList;
    String[] vehicleArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Dashboard");

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        InitializeListView(view);

        ownerDetails.setText("Name: " + TableUser.firstName + "\t" + TableUser.lastName);
        GetVehicle();
        GetExpenseReport();
        GetAnnualExpenseReport();
        GenerateListView();

        return view;
    }

    public void GetVehicle(){
        TableVehicle.userId = TableUser.userId;

        DBHandler DB = new DBHandler(getContext());
        vehicleList = DB.GetVehicle();

        if(vehicleList.isEmpty()){
            vehicleArray = new String[1];
            vehicleArray[0] = "No vehicle Recorded";
        }
        else {
            GenerateVehicleList();
        }
    }

    private void GenerateVehicleList() {
        vehicleArray = new String[vehicleList.size()];
        int counter = 0;
        for (TableVehicle.VehicleNonStatic vehicle:  vehicleList) {
            vehicleArray[counter] = vehicle.make + "\t" + vehicle.model;
            counter ++;
        }
    }

    private void GetAnnualExpenseReport() {
        DBHandler DB = new DBHandler(getContext());

        insurance = "Total insurance expenses :" + DB.GetInsuranceExpense(TableUser.userId);
        fitness = "Total fitness expenses :" + DB.GetFitnessExpense(TableUser.userId);
        roadTaxation = "Total road taxation expenses :" + DB.GetRoadTaxationExpense(TableUser.userId);
    }

    private void InitializeListView(View view) {
        listViewVehicle = (ListView)view.findViewById(R.id.listViewVehicle);
       // listViewOwnerDetails = (ListView)view.findViewById(R.id.listViewOwner);
        listViewAnnualExp = (ListView)view.findViewById(R.id.listViewAnnualExp);
        listViewExp = (ListView)view.findViewById(R.id.listViewExp);

        ownerDetails = (TextView) view.findViewById(R.id.ownerDetails);
    }

    public void GenerateListView(){


        String[] valuesExp = new String[] { fuel, repairAndServicing, fine, carWash, parking, otherExpenses };
        String[] valuesAnnualExp = new String[] { insurance, fitness, roadTaxation };

        ArrayAdapter adapterExp = new ArrayAdapter(this.getContext(), android.R.layout.simple_list_item_1, valuesExp);
        ArrayAdapter adapterAnnualExp = new ArrayAdapter(this.getContext(), android.R.layout.simple_list_item_1, valuesAnnualExp);
        ArrayAdapter adapterVehicle = new ArrayAdapter(this.getContext(), android.R.layout.simple_list_item_1, vehicleArray);


        listViewVehicle.setAdapter(adapterVehicle);
        //listViewOwnerDetails.setAdapter(adapterExp);
        listViewAnnualExp.setAdapter(adapterAnnualExp);
        listViewExp.setAdapter(adapterExp);

    }

    private void GetExpenseReport() {
        DBHandler DB = new DBHandler(getContext());

        fuel = "Total Fuel expenses :" + DB.GetFuelExpense(TableUser.userId);
        repairAndServicing = "Total repair and servicing expenses :" + DB.GetRepairAndServicingExpense(TableUser.userId);
        fine = "Total fine expenses :" + DB.GetFineExpense(TableUser.userId);
        carWash = "Total car wash expenses" + DB.GetCarWashExpenses(TableUser.userId);
        parking = "Total parking expenses :" + DB.GetParkingExpense(TableUser.userId);
        otherExpenses = "Total Other expenses :" + DB.GetOtherExpenses(TableUser.userId);
    }

    //==================== GENERATED METHOD ===========================================

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
}
