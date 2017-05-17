package com.example.avitah.Fragment.Parking;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.avitah.DbHandler.DBHandler;
import com.example.avitah.Tables.TableParking;
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
public class ParkingGraphFragment extends Fragment {

    ArrayList<TableParking.ParkingNonStatic> ParkingList;
    int counter = 0;

    public ParkingGraphFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Parking Graph");

        View view = inflater.inflate(R.layout.fragment_parking_graph, container, false);


        GraphView graph = (GraphView)view.findViewById(R.id.graphExpense);

        if(GetParking()){
            if (ParkingList.size() > 1){
                String[] myStringArray = new String[ParkingList.size()];
                DataPoint[] values = new DataPoint[ParkingList.size()];

                LineGraphSeries<DataPoint> series;
                for (TableParking.ParkingNonStatic parking:  ParkingList) {
                    values[counter] = new DataPoint(counter,parking.Cost);
                    myStringArray[counter] = parking.date;
                    counter++;
                }
                series = new LineGraphSeries<>(values);

                graph.setTitle("Parking Graph");
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

    public boolean GetParking(){
        boolean result = true;
        TableParking.userId = TableUser.userId;

        DBHandler DB = new DBHandler(getContext());
        ParkingList = DB.GetParking();

        if(ParkingList.isEmpty()){
            result = false;
        }
        return result;
    }

}
