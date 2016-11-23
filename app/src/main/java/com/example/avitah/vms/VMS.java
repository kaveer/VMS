package com.example.avitah.vms;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.avitah.Fragment.Accident.AccidentFragment;
import com.example.avitah.Fragment.Fuel.FuelFragment;
import com.example.avitah.Fragment.Insurance.InsuranceFragment;
import com.example.avitah.Fragment.Owner.OwnerDetailsFragment;
import com.example.avitah.Fragment.Vehicle.SelectVehicleFragment;

public class VMS extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //EditText USERID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
            super.onBackPressed();
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
        } else if (id == R.id.NavTools) {
            ToolsFragment fragment = new ToolsFragment();
            android.support.v4.app.FragmentTransaction fmTransaction = getSupportFragmentManager().beginTransaction();
            fmTransaction.replace(R.id.Frame_container, fragment);
            fmTransaction.commit();
        } else if (id == R.id.NavFuel) {
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
            FacebookFragment fragment = new FacebookFragment();
            android.support.v4.app.FragmentTransaction fmTransaction = getSupportFragmentManager().beginTransaction();
            fmTransaction.replace(R.id.Frame_container, fragment);
            fmTransaction.commit();
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
}
