package com.example.bewkitsada.loginregistration2.models.machine_locationprocess;

/**
 * Created by bewkitsada on 9/5/2560.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bewkitsada.loginregistration2.R;
import com.example.bewkitsada.loginregistration2.fragment.BookingFragment;
import com.example.bewkitsada.loginregistration2.fragment.CalendarFragment;
import com.example.bewkitsada.loginregistration2.models.ItemClickListener;
import com.example.bewkitsada.loginregistration2.string.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MachineListAdapter extends RecyclerView.Adapter<MachineListAdapter.ViewHolder> {
    private ArrayList<MachineLocation> machineLocations;
    private Context context;
    private LayoutInflater inflater;


    public MachineListAdapter(Context context , ArrayList<MachineLocation> machineLocations) {
        this.machineLocations = machineLocations;
        this.context = context;

    }



    public MachineListAdapter(){

    }

    @Override
    public MachineListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_machine_list_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MachineListAdapter.ViewHolder viewHolder, int i) {
        final MachineLocation ml = machineLocations.get(i);
        viewHolder.tv_machinenameeng.setText(machineLocations.get(i).getMachine_nameeng()+" "+i);
        viewHolder.tv_machinenamethai.setText("สถานะ " + machineLocations.get(i).getMachine_statusDetail());
        viewHolder.tv_location.setText("ตึก "+machineLocations.get(i).getLocation_build()+" ชั้น " +machineLocations.get(i).getLocation_floor() + " ห้อง "+machineLocations.get(i).getLocation_room());
        viewHolder.tv_location_room.setText(machineLocations.get(i).getLocation_detail());

//        Context context = viewHolder.img_android.getContext();
        Picasso.with(context)
                .load(Constants.BASE_URL+"/android_api/Admin/dist/img/machine_location/"+ml.getMachine_location_picture())
                .fit()
                .into(viewHolder.img_android);
        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int i) {
                switch (view.getId()){
                    case R.id.bt_booking:
                        Bundle bundle = new Bundle();
                        bundle.putString(Constants.MACHINENAMEENG, machineLocations.get(i).getMachine_nameeng());
                        bundle.putString(Constants.MACHINE_LOCATION_ID, machineLocations.get(i).getMachine_location_id());
                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        BookingFragment bookingFragment = new BookingFragment();
                        bookingFragment.setArguments(bundle);
                        activity.getSupportFragmentManager().beginTransaction()
                                .replace(R.id.content_frame, bookingFragment)
                                .addToBackStack(null)
                                .commit();
                        Log.d("booking" , "click");
                        break;
                    case R.id.bt_check:
                        AppCompatActivity activity2 = (AppCompatActivity) view.getContext();
                        CalendarFragment myFragment = new CalendarFragment();
                        activity2.getSupportFragmentManager().beginTransaction()
                                .replace(R.id.content_frame, myFragment)
                                .addToBackStack(null)
                                .commit();
                        Log.d("check" , "click");
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return machineLocations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_machinenameeng, tv_machinenamethai , tv_location , tv_location_room;
        private ItemClickListener clickListener;
        private Button bt_booking , bt_check;
        private ImageView img_android;

        public ViewHolder(View view) {
            super(view);

            tv_machinenameeng = (TextView) view.findViewById(R.id.tv_machinenameeng);
            tv_machinenamethai = (TextView) view.findViewById(R.id.tv_status);
            tv_location = (TextView) view.findViewById(R.id.tv_location);
            tv_location_room = (TextView) view.findViewById(R.id.tv_location_room);
            img_android = (ImageView) view.findViewById(R.id.img_android);
            bt_check = (Button) view.findViewById(R.id.bt_check);
            bt_booking = (Button) view.findViewById(R.id.bt_booking);
            view.setOnClickListener(this);
            bt_check.setOnClickListener(this);
            bt_booking.setOnClickListener(this);

        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getAdapterPosition());

        }
    }



    }

