package com.example.avitah.vms;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;



public class OwnerDetailsFragment extends Fragment {



    public OwnerDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_owner_details, container, false);
    }

    /*public void OnUpdateOwnerDetailsClick(View ownerDetails){
       // Toast messageBox = Toast.makeText(getActivity() , "Login successful" , Toast.LENGTH_LONG);
      // messageBox.show();
    }*/




}
