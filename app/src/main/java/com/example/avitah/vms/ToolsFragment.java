package com.example.avitah.vms;


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

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToolsFragment extends Fragment {

    ListView listView;
    ExpandableListView ex;
    public ToolsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tools, container, false);

       ArrayList<tool> t = new ArrayList<>();
        tool toool = new tool();
        toool.tool1 ="df";
        toool.tool2 = "tgtg";
        t.add(toool);
        toool.tool1 ="ddcdf";
        toool.tool2 = "tgdcdtg";
        t.add(toool);

        String[] listItems = new String[t.size()];
        for(int i = 0; i < t.size(); i++){
            //Recipe recipe = recipeList.get(i);
            //listItems[i] = recipe.title;
        }


        String[] values = new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };

        listView = (ListView)view.findViewById(R.id.listviewtest);
        ex = (ExpandableListView)view.findViewById(R.id.test);

        ArrayAdapter adapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_list_item_1, values);

       ArrayAdapter adapters = new ArrayAdapter(this.getContext(), android.R.layout.simple_expandable_list_item_1, values);


        listView.setAdapter(adapter);
        ex.setAdapter(adapters);

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

}
