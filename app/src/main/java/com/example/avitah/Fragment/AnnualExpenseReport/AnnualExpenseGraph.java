package com.example.avitah.Fragment.AnnualExpenseReport;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.avitah.DbHandler.DBHandler;
import com.example.avitah.Tables.TableUser;
import com.example.avitah.vms.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnnualExpenseGraph extends Fragment {


    public AnnualExpenseGraph() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_annual_expense_graph, container, false);

        DBHandler DB = new DBHandler(getContext());

        GraphView graph = (GraphView)view.findViewById(R.id.graphAnnualExpense);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, DB.GetInsuranceExpense(TableUser.userId)),
                new DataPoint(1, DB.GetFitnessExpense(TableUser.userId)),
                new DataPoint(2, DB.GetRoadTaxationExpense(TableUser.userId)),
        });

        //series.setTitle("Random Curve 1");
        series.setColor(Color.BLUE);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        series.setThickness(8);

        graph.setTitle("Annual expenses");
        graph.getGridLabelRenderer().setVerticalAxisTitle("Cost");
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Expenses");

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"Insurance", "Fitness", "Road Taxation" });
        //staticLabelsFormatter.setVerticalLabels(new String[] {"low", "middle", "high"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        graph.addSeries(series);

        return view;
    }

}
