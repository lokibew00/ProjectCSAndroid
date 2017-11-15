package com.example.bewkitsada.loginregistration2.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.bewkitsada.loginregistration2.R;
import com.example.bewkitsada.loginregistration2.models.RequestInterface;
import com.example.bewkitsada.loginregistration2.models.ServerRequest;
import com.example.bewkitsada.loginregistration2.models.ServerResponse;
import com.example.bewkitsada.loginregistration2.models.event_process.Event;
import com.example.bewkitsada.loginregistration2.string.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookingFragment extends Fragment implements View.OnClickListener {
    //    private TextView tv;
    private EditText et_machinename, et_user, et_date, et_time;
    private SharedPreferences preferences;
    private String date, user,userID, time, enddate, startdate;
    private Button bt_clear, bt_submit;
    private TimePicker timePicker;
    private TextView textView;
    private ProgressBar progressBar;
    private Calendar mcurrentTime, CalendarDate;
    private int minhour;
    private Date startDate ,  endDate;
    private Spinner spinner;



    public BookingFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTeacher();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking, container, false);
        initviews(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        preferences = getActivity().getSharedPreferences("first", Context.MODE_PRIVATE);
//        tv.setText("วันที่"+bundle.getString("day"));
        date = bundle.getString("year") + "-" + bundle.getString("month") + "-" + bundle.getString("day");
        user = preferences.getString(Constants.USERNAME, "");
        userID = preferences.getString(Constants.USER_ID,"");
//        userID = preferences.getString(Constants.)
//        et_date.setEnabled(false);
        et_date.setFocusable(false);
        et_time.setFocusable(false);
        et_user.setText(user);
        et_user.setEnabled(false);
        et_machinename.setText(bundle.getString(Constants.MACHINENAMEENG));
        et_machinename.setEnabled(false);
        bt_submit.setOnClickListener(this);
        bt_clear.setOnClickListener(this);
        et_time.setOnClickListener(this);
        et_date.setOnClickListener(this);

    }

    public void initviews(View view) {
        et_machinename = (EditText) view.findViewById(R.id.et_machinename);
        et_date = (EditText) view.findViewById(R.id.et_date);
        et_user = (EditText) view.findViewById(R.id.et_user);
        et_time = (EditText) view.findViewById(R.id.et_time);
        bt_submit = (Button) view.findViewById(R.id.bt_submit);
        bt_clear = (Button) view.findViewById(R.id.bt_clear);
        spinner = (Spinner) view.findViewById(R.id.spinner);

    }

    public void onClick(View view) {
        Bundle bundle = getArguments();
        time = et_time.getText().toString();
        switch (view.getId()) {
            case R.id.bt_submit:
                startDate = ConvertToDate(startdate);
                endDate = ConvertToDate(enddate);
                Log.d("StrDate", "Start:" + startdate + "\nEnd:" + enddate);
//                Log.d("ConverttoDate", "Start:" + startDate + "\nEnd:" + endDate);
                printDifference(startDate, endDate);
                check();
                Log.d("diff Hour Is: " + bundle.getString(Constants.MACHINENAMEENG), "MinDiff:" + minhour + "\nDiff:" + diffHour(startDate, endDate));
                if (diffHour(startDate, endDate) >= minhour) {
                    setData(bundle.getString(Constants.MACHINE_LOCATION_ID), userID, startdate, enddate.toString() , "");
                    Log.d("DATA:" , "MLID: "+bundle.getString(Constants.MACHINE_LOCATION_ID)+"\nuserID:"+userID+"\nstart:" +startdate+"\nend:"+ enddate);
                } else {
                    Snackbar.make(view, "ต้องจองมากกว่า " + minhour + " ชม", Snackbar.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_clear:
                et_time.setText("");
                enddate = "";
                break;
            case R.id.et_date:
                dateDialog();
                break;
            case R.id.et_time:
                dateDialog2();
                break;

        }
    }
    public void dateDialog() {
        CalendarDate = Calendar.getInstance();
        int year = CalendarDate.get(Calendar.YEAR);
        int month = CalendarDate.get(Calendar.MONTH);
        int day = CalendarDate.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int syear, int smonth, int sday) {
                startdate = syear + "-" + smonth + "-" + sday;
                et_date.setText(startdate);
                Log.d("", "Set date" + startdate);
                timeDialog();


            }
        }, year, month, day);
        datePickerDialog.setTitle("Select Date");
        datePickerDialog.show();
    }
    public void timeDialog() {
        mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);

        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
//                String myFormat = "hh:mm";
//                SimpleDateFormat formatter = new SimpleDateFormat(myFormat);
//                formatter.format(selectedHour);
//                et_time.setText( selectedHour + ":" + selectedMinute);
                startdate = startdate + " " + String.format("%02d:%02d", selectedHour, selectedMinute);
                et_date.setText(startdate);
            }
        }, hour, minute, true);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }
    public void dateDialog2() {
        CalendarDate = Calendar.getInstance();
        int year = CalendarDate.get(Calendar.YEAR);
        int month = CalendarDate.get(Calendar.MONTH);
        int day = CalendarDate.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int syear, int smonth, int sday) {
                enddate = syear + "-" + smonth + "-" + sday;
                et_time.setText(enddate);
                Log.d("", "Set date" + enddate);
                timeDialog2();


            }
        }, year, month, day);
        datePickerDialog.setTitle("Select Date");
        datePickerDialog.show();
    }
    public void timeDialog2() {
        mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);

        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                String myFormat = "hh:mm";
                SimpleDateFormat formatter = new SimpleDateFormat(myFormat);

//                formatter.format(selectedHour);
//                et_time.setText( selectedHour + ":" + selectedMinute);
                enddate = enddate + " " + String.format("%02d:%02d", selectedHour, selectedMinute);
                et_time.setText(enddate);
            }
        }, hour, minute, true);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    public void getTeacher(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        ServerRequest request = new ServerRequest();
        request.setOperation("teacher");
        Call<ServerResponse> response = requestInterface.operation(request);
        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse jsonResponse = response.body();
//                ArrayList<Event> data = new ArrayList<>(Arrays.asList(jsonResponse.getEvent()));
//                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext() , android.R.layout.simple_list_item_1, data);
//                String[] objects = { "January", "Feburary", "March", "April", "May",
                /*test
                        "June", "July", "August", "September", "October", "November","December" };
                ArrayAdapter adapter = new ArrayAdapter(
                        getActivity().getApplicationContext(),android.R.layout.simple_list_item_1 ,objects);
                spinner.setAdapter(adapter);*/
//                Log.d("" , data.toString());
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    public void setData(final String machine_location_id, final String user_id,
                        final String start, final String end , String teacher ) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        final Event event = new Event();
        event.setUser_id(user_id);
        event.setMachine_location_id(machine_location_id);
        event.setStart(start);
        event.setEnd(end);
        event.setTime(teacher);
        ServerRequest serverRequest = new ServerRequest();
        serverRequest.setOperation(Constants.BOOKING_OPERATION);
        serverRequest.setEvent(event);
        Call<ServerResponse> serverResponseCall = requestInterface.operation(serverRequest);
        serverResponseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse = response.body();
                if(serverResponse.getResult().equals(Constants.SUCCESS)){
                    Snackbar.make(getView() , serverResponse.getMessage() , Snackbar.LENGTH_SHORT).show();
                } else if (serverResponse.getResult().equals(Constants.EXIST)){
                    Snackbar.make(getView(),serverResponse.getMessage(),Snackbar.LENGTH_SHORT).show();
                } else{
                    Snackbar.make(getView(),serverResponse.getMessage(),Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });

    }

    private Date ConvertToDate(String dateString) {
        if (dateString == null) {
            return null;
        } else {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");
            Date convertedDate = new Date();
            try {
                convertedDate = dateFormat.parse(dateString);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return convertedDate;
        }
    }

    public void printDifference(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return;
        } else {
            long different = date2.getTime() - date1.getTime();
            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;
            long elapsedDays = different / daysInMilli;
            different = different % daysInMilli;

            long elapsedHours = different / hoursInMilli;
            different = different % hoursInMilli;

            long elapsedMinutes = different / minutesInMilli;
            different = different % minutesInMilli;

            long elapsedSeconds = different / secondsInMilli;
            String diff = String.format("%d days, %d hours, %d minutes, %d seconds%n",
                    elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);
            Log.d("Different all", diff);
        }
    }

    public long diffHour(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        } else {
            long different = date2.getTime() - date1.getTime();
            long difftohour = TimeUnit.MILLISECONDS.toHours(different);
            return difftohour;
        }
    }

    public int check() {
        Bundle b = getArguments();
        switch (b.getString(Constants.MACHINENAMEENG)) {
            case "Laminar Flow":
                minhour = 10;
                break;
            case "Freezedry":
                minhour = 24;
                break;
            case "Hot air oven":
                minhour = 6;
                break;
        }
        return minhour;
    }
}

