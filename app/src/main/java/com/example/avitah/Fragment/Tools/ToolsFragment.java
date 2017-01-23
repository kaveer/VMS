package com.example.avitah.Fragment.Tools;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.avitah.vms.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToolsFragment extends Fragment {

    ListView listView;

    public ToolsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tools, container, false);
        listView = (ListView)view.findViewById(R.id.ListViewTools);

        String[] values = new String[] {
                "Calculator",
                "Calendar ",
                "Other tools",
        };

        GenerateListView(values);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast messageBox = Toast.makeText(getActivity() , +position+ " Tools : " +itemValue + " Coming soon" , Toast.LENGTH_SHORT);
                messageBox.show();
            }
        });

        return view;
    }

    public void GenerateListView(String[] values){
        ArrayAdapter adapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);
    }


}
