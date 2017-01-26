package com.example.avitah.Fragment.ExpenseReport;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.avitah.DbHandler.DBHandler;
import com.example.avitah.Tables.TableUser;
import com.example.avitah.vms.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpenseReport extends Fragment {

    ListView listView;

    String fuel = "Total Fuel expenses :";
    String repairAndServicing = "Total repair and servicing expenses :";
    String fine = "Total fine expenses :";
    String carWash = "Total car wash expenses";
    String parking = "Total parking expenses :";
    String otherExpenses = "Total Other expenses :";

    public ExpenseReport() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense_report, container, false);
        listView = (ListView)view.findViewById(R.id.ListViewExpenseReport);

        GetExpenseReport();
        String[] values = new String[] { fuel, repairAndServicing, fine, carWash, parking, otherExpenses };
        GenerateListView(values);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert

                Toast messageBox = Toast.makeText(getActivity() , +itemPosition+ "Insurance removed" +itemValue  , Toast.LENGTH_SHORT);
                messageBox.show();

            }

        });

        Button buttonGraph   = (Button)view.findViewById(R.id.BtnGraphExpense);
        buttonGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigateToVehicleFragment();
            }
        });
        return view;
    }

    private void NavigateToVehicleFragment() {
        ExpenseGraph fragment = new ExpenseGraph();
        android.support.v4.app.FragmentTransaction fmTransaction = getFragmentManager().beginTransaction();
        fmTransaction.replace(R.id.Frame_container, fragment);
        fmTransaction.commit();
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

    public void GenerateListView(String[] values){
        ArrayAdapter adapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);
    }

}
