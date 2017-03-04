package com.example.avitah.Fragment.CarWash;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.avitah.DbHandler.DBHandler;
import com.example.avitah.Tables.TableCarWash;
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
public class CarWashGraphFragment extends Fragment {

    ArrayList<TableCarWash.CarWashNonStatic> carWashList;
    int counter = 0;

    public CarWashGraphFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_wash_graph, container, false);

        GraphView graph = (GraphView)view.findViewById(R.id.graphExpense);

        if(GetCarWash()){
            if (carWashList.size() > 1){
                String[] myStringArray = new String[carWashList.size()];
                DataPoint[] values = new DataPoint[carWashList.size()];

                LineGraphSeries<DataPoint> series;
                for (TableCarWash.CarWashNonStatic wash:  carWashList) {
                    values[counter] = new DataPoint(counter,wash.Cost);
                    myStringArray[counter] = wash.date;
                    counter++;
                }
                series = new LineGraphSeries<>(values);

                graph.setTitle("Car Wash");
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

    public boolean GetCarWash(){
        boolean result = true;
        TableCarWash.userId = TableUser.userId;

        DBHandler DB = new DBHandler(getContext());
        carWashList = DB.GetCarWash();

        if(carWashList.isEmpty()){
            result = false;
        }
        return result;
    }

}
