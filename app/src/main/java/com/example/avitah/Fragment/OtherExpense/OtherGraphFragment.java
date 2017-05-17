package com.example.avitah.Fragment.OtherExpense;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.avitah.DbHandler.DBHandler;
import com.example.avitah.Tables.TableOtherExpense;
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
public class OtherGraphFragment extends Fragment {

    ArrayList<TableOtherExpense.OtherExpenseNonStatic> otherExpenseList;
    int counter = 0;

    public OtherGraphFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Other Expense Graph");

        View view = inflater.inflate(R.layout.fragment_other_graph, container, false);

        GraphView graph = (GraphView)view.findViewById(R.id.graphExpense);

        if(GetOtherExpense()){
            if (otherExpenseList.size() > 1){
                String[] myStringArray = new String[otherExpenseList.size()];
                DataPoint[] values = new DataPoint[otherExpenseList.size()];

                LineGraphSeries<DataPoint> series;
                for (TableOtherExpense.OtherExpenseNonStatic other:  otherExpenseList) {
                    values[counter] = new DataPoint(counter,other.Cost);
                    myStringArray[counter] = other.date;
                    counter++;
                }
                series = new LineGraphSeries<>(values);

                graph.setTitle("Other Expenses Graph");
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

    public boolean GetOtherExpense(){
        boolean result = true;
        TableOtherExpense.userId = TableUser.userId;

        DBHandler DB = new DBHandler(getContext());
        otherExpenseList = DB.GetOhterExpense();

        if(otherExpenseList.isEmpty()){
            result = false;
        }
        return result;
    }

}
