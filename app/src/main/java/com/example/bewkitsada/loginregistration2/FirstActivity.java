package com.example.bewkitsada.loginregistration2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.bewkitsada.loginregistration2.fragment.MachineNameFragment;
import com.example.bewkitsada.loginregistration2.fragment.ProfileFragment;
import com.example.bewkitsada.loginregistration2.string.Constants;


public class FirstActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView tv_name, tv_username;
    private SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        tv_name = (TextView) header.findViewById(R.id.tv_name);
        tv_username = (TextView) header.findViewById(R.id.tv_username);
        SharedPreferences share = getSharedPreferences("first", Context.MODE_PRIVATE);
        tv_name.setText(share.getString(Constants.NAME, ""));
        tv_username.setText(share.getString(Constants.USERNAME, ""));
//        if (savedInstanceState == null) {
//            syncFragment();
//        }

    }

    public void syncFragment() {
        Fragment machineNameFragment = new MachineNameFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.recyclerView, machineNameFragment).commit();
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
        getMenuInflater().inflate(R.menu.first, menu);
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

    /*use if*/
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;
        if (id == R.id.nav_cp) {
            fragmentClass = ProfileFragment.class;
            setTitle("PROFILE");
        } else if (id == R.id.nav_machine) {
            fragmentClass = MachineNameFragment.class;
            setTitle("Main Machine");
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    private void displaySelectedScreen(int itemId) {
//
//        //creating fragment object
//        Fragment fragment = null;
//
//        //initializing the fragment object which is selected
//        switch (itemId) {
//            case R.id.nav_cp:
//                fragment = new ProfileFragment();
//                break;
//
//            case R.id.nav_machine:
//                fragment = new MachineNameFragment();
//                break;
//        }
//
//
//        //replacing the fragment
//        if (fragment != null) {
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.content_frame, fragment);
//            ft.commit();
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//    }
//
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//
//        //calling the method displayselectedscreen and passing the id of selected menu
//        displaySelectedScreen(item.getItemId());
//        //make this method blank
//        return true;
//    }

}

