package com.example.avitah.Fragment.AnnualExpenseReport;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.avitah.DbHandler.DBHandler;
import com.example.avitah.Tables.TableUser;
import com.example.avitah.vms.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnnualExpenseReport extends Fragment {

    ListView listView;

    String insurance = "Error";
    String fitness = "Error";
    String roadTaxation = "Error";

    public AnnualExpenseReport() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_annual_expense_report, container, false);
        listView = (ListView)view.findViewById(R.id.ListViewAnnualExpenseReport);

        GetExpenseReport();
        String[] values = new String[] { insurance, fitness, roadTaxation };
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
        return view;
    }

    private void GetExpenseReport() {
        DBHandler DB = new DBHandler(getContext());

        insurance = DB.GetInsuranceExpense(TableUser.userId);
        fitness = DB.GetFitnessExpense(TableUser.userId);
        roadTaxation = DB.GetRoadTaxationExpense(TableUser.userId);
    }

    private void GenerateListView(String[] values) {
        ArrayAdapter adapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);
    }

}
