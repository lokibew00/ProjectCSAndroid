package com.example.bewkitsada.loginregistration2.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.bewkitsada.loginregistration2.R;

import static android.content.ContentValues.TAG;


public class CalendarFragment extends Fragment {

    public CalendarFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final Bundle bundle = new Bundle();
        View view = inflater.inflate(R.layout.fragment_calendar, container , false);
        view.setTag(TAG);
        final CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendarView); // get the reference of CalendarView
//        calendarView.setMinDate(1463918226920L);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
//                Toast.makeText(view.getContext(), "วันที่:"+dayOfMonth+"เดือน:"+month+"ปี:"+year, Toast.LENGTH_SHORT).show();
                Bundle bundle1 = getArguments();
                String stday = Integer.toString(dayOfMonth);
                String stmonth = Integer.toString(month);
                String styear = Integer.toString(year);
                bundle.putString("day",stday);
                bundle.putString("month" , stmonth);
                bundle.putString("year" , styear);
                AppCompatActivity activity = (AppCompatActivity)view.getContext() ;
                BookedListFragment fragment = new BookedListFragment();
                fragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.content_frame,fragment)
                        .commit();

            }
        });
        return view ;
    }



}
