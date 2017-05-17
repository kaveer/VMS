package com.example.avitah.Fragment.Fuel;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.avitah.DbHandler.DBHandler;
import com.example.avitah.Tables.TableFuel;
import com.example.avitah.Tables.TableUser;
import com.example.avitah.vms.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FuelGraphFragment extends Fragment {

    ArrayList<TableFuel.FuelNonStatic> fuelList;
    int counter = 0;

    public FuelGraphFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Fuel Graph");

        View view = inflater.inflate(R.layout.fragment_fuel_graph, container, false);

        GraphView graph = (GraphView)view.findViewById(R.id.graphExpense);

        if(GetFuel()){
            if (fuelList.size() > 1){
                String[] myStringArray = new String[fuelList.size()];
                DataPoint[] values = new DataPoint[fuelList.size()];

                LineGraphSeries<DataPoint> series;
                for (TableFuel.FuelNonStatic fuel:  fuelList) {
                    values[counter] = new DataPoint(counter,fuel.fuelTotalCost);
                    myStringArray[counter] = fuel.fuelDate;
                    counter++;
                }
                series = new LineGraphSeries<>(values);

                graph.setTitle("Fuel Graph");
                graph.getGridLabelRenderer().setVerticalAxisTitle("Cost");
                graph.getGridLabelRenderer().setHorizontalAxisTitle("Date");

                graph.addSeries(series);

                StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
                staticLabelsFormatter.setHorizontalLabels(myStringArray);
                graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            }
            else {
                Toast messageBox = Toast.makeText(getActivity() , "Only one expense cannot be display" , Toast.LENGTH_LONG);
                messageBox.show();
            }
        }
        else {
            Toast messageBox = Toast.makeText(getActivity() , "No Car Wash Expenses" , Toast.LENGTH_LONG);
            messageBox.show();
        }


        return  view;
    }

    public boolean GetFuel(){
        boolean result = true;
        TableFuel.userId = TableUser.userId;

        DBHandler DB = new DBHandler(getContext());
        fuelList = DB.GetFuel();

        if(fuelList.isEmpty()){
            result = false;
        }
        return result;
    }

}
