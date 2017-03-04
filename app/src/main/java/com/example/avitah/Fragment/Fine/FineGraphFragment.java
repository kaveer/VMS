package com.example.avitah.Fragment.Fine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.avitah.DbHandler.DBHandler;
import com.example.avitah.Tables.TableFines;
import com.example.avitah.Tables.TableUser;
import com.example.avitah.vms.R;
import com.jjoe64.graphview.GraphView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FineGraphFragment extends Fragment {

    ArrayList<TableFines.FineNonStatic> fineList;

    public FineGraphFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_fine_graph, container, false);

        GraphView graph = (GraphView)view.findViewById(R.id.graphExpense);

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
