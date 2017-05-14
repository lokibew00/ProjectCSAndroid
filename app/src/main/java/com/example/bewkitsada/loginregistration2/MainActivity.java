package com.example.bewkitsada.loginregistration2;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.bewkitsada.loginregistration2.fragment.LoginFragment;
import com.example.bewkitsada.loginregistration2.string.Constants;


public class MainActivity extends AppCompatActivity {

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences("first" , Context.MODE_PRIVATE);
        initFragment();
    }

    private void initFragment(){
        Fragment fragment;
        if(pref.getBoolean(Constants.IS_LOGGED_IN,false)){
            Intent in = new Intent(this, FirstActivity.class);
            startActivity(in);
        }else {
            fragment = new LoginFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_frame,fragment);
            ft.commit();
        }

    }

}
