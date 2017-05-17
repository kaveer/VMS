package com.example.avitah.Fragment.RepairAndServicing;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.avitah.DbHandler.DBHandler;
import com.example.avitah.Tables.TableRepair;
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
public class RepairGraphFragment extends Fragment {

    ArrayList<TableRepair.RepairNonStatic> repairList;
    int counter = 0;

    public RepairGraphFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Repair & Servicing Graph");

        View view = inflater.inflate(R.layout.fragment_repair_graph, container, false);

        GraphView graph = (GraphView)view.findViewById(R.id.graphExpense);

        if(GetRepair()){
            if (repairList.size() > 1){
                String[] myStringArray = new String[repairList.size()];
                DataPoint[] values = new DataPoint[repairList.size()];

                LineGraphSeries<DataPoint> series;
                for (TableRepair.RepairNonStatic repair:  repairList) {
                    values[counter] = new DataPoint(counter,repair.Cost);
                    myStringArray[counter] = repair.repairDate;
                    counter++;
                }
                series = new LineGraphSeries<>(values);

                graph.setTitle("Repair And Repair Graph");
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

        return view;
    }

    private boolean GetRepair() {
        boolean result = true;
        TableRepair.userId = TableUser.userId;

        DBHandler DB = new DBHandler(getContext());
        repairList = DB.GetRepair();

        if(repairList.isEmpty()){
            result = false;
        }
        return result;
    }

}
