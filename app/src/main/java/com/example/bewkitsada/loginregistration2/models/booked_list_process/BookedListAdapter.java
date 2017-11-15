package com.example.bewkitsada.loginregistration2.models.booked_list_process;

/**
 * Created by bewkitsada on 9/5/2560.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bewkitsada.loginregistration2.R;
import com.example.bewkitsada.loginregistration2.models.ItemClickListener;
import com.example.bewkitsada.loginregistration2.models.booked_process.Booked;

import java.util.ArrayList;

public class BookedListAdapter extends RecyclerView.Adapter<BookedListAdapter.ViewHolder> {
    private ArrayList<Booked> booked;
    private Context context;
    private LayoutInflater inflater;


    public BookedListAdapter(Context context , ArrayList<Booked> booked) {
        this.booked = booked;
        this.context = context;

    }



    public BookedListAdapter(){

    }

    @Override
    public BookedListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_booked_list_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookedListAdapter.ViewHolder viewHolder, int i) {
//        final Booked book = booked.get(i);
        viewHolder.tv_name.setText(booked.get(i).getMachine_nameeng());
        viewHolder.tv_start.setText(booked.get(i).getStart_date());
        viewHolder.tv_end.setText(booked.get(i).getEnd_date());
        viewHolder.tv_status.setText(booked.get(i).getName());

//        Context context = viewHolder.img_android.getContext();
//        Picasso.with(context)
//                .load(ml.getMachine_location_picture())
//                .placeholder()
//                .fit()
//                .into(viewHolder.img_android);
        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int i) {
//                Bundle bundle = new Bundle();
//                bundle.putString(Constants.MACHINENAMEENG,booked.get(i).getMachine_nameeng());
//                bundle.putString(Constants.MACHINE_LOCATION_ID ,machineLocations.get(i).getMachine_location_id());
//                bundle.putString(Constants.MACHINE_LOCATION_ID,machineLocations.get(i).getMachine_location_id());
//                Toast.makeText(view.getContext(), "Number: " + ml.getMachine_location_id(), Toast.LENGTH_SHORT).show();
//                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                CalendarFragment myFragment = new CalendarFragment();
//                myFragment.setArguments(bundle);
//                activity.getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.content_frame, myFragment)
//                        .addToBackStack(null)
//                        .commit();

            }
        });
//
    }

    @Override
    public int getItemCount() {
        return booked.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_start, tv_end , tv_status , tv_name;
        private ItemClickListener clickListener;
        private ImageView img_android;

        public ViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_start = (TextView) view.findViewById(R.id.tv_start);
            tv_end = (TextView) view.findViewById(R.id.tv_end);
            tv_status = (TextView) view.findViewById(R.id.tv_status);
//            img_android = (ImageView) view.findViewById(R.id.img_android);
            view.setOnClickListener(this);

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
