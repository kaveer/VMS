package com.example.avitah.Fragment.Share;


import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.avitah.vms.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShareFragment extends Fragment {

    EditText recipient;

    public ShareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share, container, false);

        recipient = (EditText) view.findViewById(R.id.txt_recipient) ;

        Button buttonSave   = (Button)view.findViewById(R.id.BtnShare);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsValid()){

                    if(isNetworkConnected()){
                        SentMail();
                    }
                    else {
                        Toast messageBox = Toast.makeText(getActivity() , "No internet connection" , Toast.LENGTH_LONG);
                        messageBox.show();
                    }
                   
                }
            }
        });
        
        return view;
    }

    private boolean IsValid() {
        boolean result = true;
        if(recipient.getText().toString().trim().length() == 0){
            recipient.setError("Enter Email");
            return false;
        }

        if (!recipient.getText().toString().trim().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            recipient.setError("Invalid Email Format");
            return false;
        }
        return result;
    }

    private void SentMail() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{recipient.getText().toString().trim()});
        i.putExtra(Intent.EXTRA_SUBJECT, "Try VMS. Track your cars!!");
        i.putExtra(Intent.EXTRA_TEXT   ,
                "Hello,\n\n" +
                "I'm using vms app to track my vehicles on my Android phone/tablet and I would like to share my experience with you.\n\n" +
                "You can keep records of your cars and have all of them under control. Vms will help you to keep track of your fuel mileage and costs, maintenance, trips and general expenses of your vehicles.\n\n" +
                "You should try vms too!");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    protected boolean isNetworkConnected() {
        try {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) getActivity().getSystemService(getContext().CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            return (mNetworkInfo == null) ? false : true;

        }catch (NullPointerException e){
            return false;

        }
    }

}
