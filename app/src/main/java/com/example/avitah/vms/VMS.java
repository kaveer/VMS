package com.example.avitah.vms;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.avitah.Activity.MainFragment;
import com.example.avitah.Fragment.Accident.AccidentFragment;
import com.example.avitah.Fragment.AnnualExpenseReport.AnnualExpenseReport;
import com.example.avitah.Fragment.CarWash.CarWashFragment;
import com.example.avitah.Fragment.ExpenseReport.ExpenseReport;
import com.example.avitah.Fragment.Fine.FinesFragment;
import com.example.avitah.Fragment.Fitness.FitnessFragment;
import com.example.avitah.Fragment.Fuel.FuelFragment;
import com.example.avitah.Fragment.Insurance.InsuranceFragment;
import com.example.avitah.Fragment.OtherExpense.OtherExpensesFragment;
import com.example.avitah.Fragment.Owner.OwnerDetailsFragment;
import com.example.avitah.Fragment.Parking.ParkingFragment;
import com.example.avitah.Fragment.RepairAndServicing.RepairServicingFragment;
import com.example.avitah.Fragment.Share.ShareFragment;
import com.example.avitah.Fragment.Taxation.RoadTaxationFragment;
import com.example.avitah.Fragment.Vehicle.SelectVehicleFragment;

public class VMS extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vms);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //change general fragment to main fragment created
        MainFragment main = new MainFragment();
        android.support.v4.app.FragmentTransaction fmTransaction = getSupportFragmentManager().beginTransaction();
        fmTransaction.replace(R.id.Frame_container, main);
        fmTransaction.commit();
        //==============================================

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

      //  USERID = (EditText)findViewById(R.id.ID_Main);
      //  USERID.setText("HNJND");



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.vm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.NavVehicleDetails) {
            SelectVehicleFragment fragment = new  SelectVehicleFragment();
            android.support.v4.app.FragmentTransaction fmTransaction = getSupportFragmentManager().beginTransaction();
            fmTransaction.replace(R.id.Frame_container, fragment);
            fmTransaction.commit();
        } else if (id == R.id.NavHome) {
            MainFragment fragment = new MainFragment();
            android.support.v4.app.FragmentTransaction fmTransaction = getSupportFragmentManager().beginTransaction();
            fmTransaction.replace(R.id.Frame_container, fragment);
            fmTransaction.commit();
        } else if (id == R.id.NavOwnerDetails) {
            OwnerDetailsFragment fragment = new OwnerDetailsFragment();
            android.support.v4.app.FragmentTransaction fmTransaction = getSupportFragmentManager().beginTransaction();
            fmTransaction.replace(R.id.Frame_container, fragment);
            fmTransaction.commit();
        }else if (id == R.id.NavAccidentRecords) {
            AccidentFragment fragment = new AccidentFragment();
            android.support.v4.app.FragmentTransaction fmTransaction = getSupportFragmentManager().beginTransaction();
            fmTransaction.replace(R.id.Frame_container, fragment);
            fmTransaction.commit();
        }

//        else if (id == R.id.NavTools) {
//            ToolsFragment fragment = new ToolsFragment();
//            android.support.v4.app.FragmentTransaction fmTransaction = getSupportFragmentManager().beginTransaction();
//            fmTransaction.replace(R.id.Frame_container, fragment);
//            fmTransaction.commit();
//        }

        else if (id == R.id.NavFuel) {
            FuelFragment fragment = new FuelFragment();
            android.support.v4.app.FragmentTransaction fmTransaction = getSupportFragmentManager().beginTransaction();
            fmTransaction.replace(R.id.Frame_container, fragment);
            fmTransaction.commit();
        } else if (id == R.id.NavRepairServicing) {
            RepairServicingFragment fragment = new RepairServicingFragment();
            android.support.v4.app.FragmentTransaction fmTransaction = getSupportFragmentManager().beginTransaction();
            fmTransaction.replace(R.id.Frame_container, fragment);
            fmTransaction.commit();
        } else if (id == R.id.NavFines) {
            FinesFragment fragment = new FinesFragment();
            android.support.v4.app.FragmentTransaction fmTransaction = getSupportFragmentManager().beginTransaction();
            fmTransaction.replace(R.id.Frame_container, fragment);
            fmTransaction.commit();
        } else if (id == R.id.NavCarWash) {
            CarWashFragment fragment = new CarWashFragment();
            android.support.v4.app.FragmentTransaction fmTransaction = getSupportFragmentManager().beginTransaction();
            fmTransaction.replace(R.id.Frame_container, fragment);
            fmTransaction.commit();
        } else if (id == R.id.NavParking) {
            ParkingFragment fragment = new ParkingFragment();
            android.support.v4.app.FragmentTransaction fmTransaction = getSupportFragmentManager().beginTransaction();
            fmTransaction.replace(R.id.Frame_container, fragment);
            fmTransaction.commit();
        } else if (id == R.id.NavOtherExpenses) {
            OtherExpensesFragment fragment = new OtherExpensesFragment();
            android.support.v4.app.FragmentTransaction fmTransaction = getSupportFragmentManager().beginTransaction();
            fmTransaction.replace(R.id.Frame_container, fragment);
            fmTransaction.commit();
        } else if (id == R.id.NavInsurance) {
            InsuranceFragment fragment = new InsuranceFragment();
            android.support.v4.app.FragmentTransaction fmTransaction = getSupportFragmentManager().beginTransaction();
            fmTransaction.replace(R.id.Frame_container, fragment);
            fmTransaction.commit();
        } else if (id == R.id.NavFitness) {
            FitnessFragment fragment = new FitnessFragment();
            android.support.v4.app.FragmentTransaction fmTransaction = getSupportFragmentManager().beginTransaction();
            fmTransaction.replace(R.id.Frame_container, fragment);
            fmTransaction.commit();
        } else if (id == R.id.NavRoadTaxation) {
            RoadTaxationFragment fragment = new RoadTaxationFragment();
            android.support.v4.app.FragmentTransaction fmTransaction = getSupportFragmentManager().beginTransaction();
            fmTransaction.replace(R.id.Frame_container, fragment);
            fmTransaction.commit();
        } else if (id == R.id.NavShare) {
            ShareFragment fragment = new ShareFragment();
            android.support.v4.app.FragmentTransaction fmTransaction = getSupportFragmentManager().beginTransaction();
            fmTransaction.replace(R.id.Frame_container, fragment);
            fmTransaction.commit();
        } else if (id == R.id.NavFacebook) {
            if(isNetworkConnected()){
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/VMS-Avi-406670713021198/?__mref=message_bubble"));
                startActivity(browserIntent);
            }
            else {
                Toast messageBox = Toast.makeText(this , "No internet connection" , Toast.LENGTH_LONG);
                messageBox.show();
            }
        }else if(id == R.id.ExpenseReport){
            ExpenseReport fragment = new ExpenseReport();
            android.support.v4.app.FragmentTransaction fmTransaction = getSupportFragmentManager().beginTransaction();
            fmTransaction.replace(R.id.Frame_container, fragment);
            fmTransaction.commit();
        }else  if(id == R.id.AnnualExpenseReport){
            AnnualExpenseReport fragment = new AnnualExpenseReport();
            android.support.v4.app.FragmentTransaction fmTransaction = getSupportFragmentManager().beginTransaction();
            fmTransaction.replace(R.id.Frame_container, fragment);
            fmTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected boolean isNetworkConnected() {
        try {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) this.getSystemService(this.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            return (mNetworkInfo == null) ? false : true;

        }catch (NullPointerException e){
            return false;

        }
    }
}
