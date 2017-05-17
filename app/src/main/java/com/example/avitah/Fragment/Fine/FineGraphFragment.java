package com.example.avitah.Fragment.Fine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.avitah.DbHandler.DBHandler;
import com.example.avitah.Tables.TableFines;
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
public class FineGraphFragment extends Fragment {

    ArrayList<TableFines.FineNonStatic> fineList;
    int counter = 0;

    public FineGraphFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Fine Graph");

        View view =  inflater.inflate(R.layout.fragment_fine_graph, container, false);

        GraphView graph = (GraphView)view.findViewById(R.id.graphExpense);

        if(GetFine()){
            if (fineList.size() > 1){
                String[] myStringArray = new String[fineList.size()];
                DataPoint[] values = new DataPoint[fineList.size()];

                LineGraphSeries<DataPoint> series;
                for (TableFines.FineNonStatic fine:  fineList) {
                    values[counter] = new DataPoint(counter,fine.charge);
                    myStringArray[counter] = fine.fineDate;
                    counter++;
                }
                series = new LineGraphSeries<>(values);

                graph.setTitle("Fine Graph");
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
            Toast messageBox = Toast.makeText(getActivity() , "No fine Expenses" , Toast.LENGTH_LONG);
            messageBox.show();
        }

        return view;
    }

    public boolean GetFine(){
        boolean result = true;
        TableFines.userId = TableUser.userId;

        DBHandler DB = new DBHandler(getContext());
        fineList = DB.GetFine();

        if(fineList.isEmpty()){
            result = false;
        }
        return result;
    }

}
