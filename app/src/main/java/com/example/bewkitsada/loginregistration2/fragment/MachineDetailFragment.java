package com.example.bewkitsada.loginregistration2.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bewkitsada.loginregistration2.R;
import com.example.bewkitsada.loginregistration2.models.machine_locationprocess.MachineListAdapter;
import com.example.bewkitsada.loginregistration2.string.Constants;

public class MachineDetailFragment extends Fragment implements View.OnClickListener {
    private TextView tv_namethai, tv_name;
    private Button button_list;


    public MachineDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_machine_detail, container, false);
        initViews(view);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        tv_name.setText("ชื่อภาษาอังกฤษ : " + bundle.getString(Constants.MACHINENAMEENG));
        tv_namethai.setText("ชื่อภาษาไทย : " + bundle.getString(Constants.MACHINENAMETHAI));
        button_list.setOnClickListener(this);
    }

    public void initViews(final View view) {
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_namethai = (TextView) view.findViewById(R.id.tv_namethai);
        button_list = (Button) view.findViewById(R.id.button_list);


    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.getArguments();

        switch (v.getId()) {

            case R.id.button_list:
                AppCompatActivity activity = (AppCompatActivity) getContext();
                MachineListFragment myFragment = new MachineListFragment();
                MachineListAdapter adapter = new MachineListAdapter();
                bundle.putString(Constants.MACHINE_ID , bundle2.getString(Constants.MACHINE_ID));
                bundle.putString(Constants.MACHINENAMEENG , bundle2.getString(Constants.MACHINE_ID));
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, myFragment)
                        .addToBackStack(null)
                        .commit();
                break;

        }

    }


}
