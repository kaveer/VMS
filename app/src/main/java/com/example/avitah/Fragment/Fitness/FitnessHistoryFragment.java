package com.example.avitah.Fragment.Fitness;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.avitah.vms.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FitnessHistoryFragment extends Fragment {


    public FitnessHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fitness_history, container, false);
    }

}
