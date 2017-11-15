package com.example.bewkitsada.loginregistration2.models.machineprocess;

/**
 * Created by bewkitsada on 9/5/2560.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bewkitsada.loginregistration2.R;
import com.example.bewkitsada.loginregistration2.fragment.MachineDetailFragment;
import com.example.bewkitsada.loginregistration2.models.ItemClickListener;
import com.example.bewkitsada.loginregistration2.string.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MachineAdapter extends RecyclerView.Adapter<MachineAdapter.ViewHolder> {
    private ArrayList<MachineName> machinename;
    private Context context;


    public MachineAdapter(Context context  ,ArrayList<MachineName> machinename){
        this.machinename = machinename;
        this.context = context;
    }

    @Override
    public MachineAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_machine_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MachineAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_machinenameeng.setText(machinename.get(i).getMachinenameeng());
        viewHolder.tv_machinenamethai.setText(machinename.get(i).getMachinenamethai());
        Picasso.with(context)
                .load(Constants.BASE_URL+"/android_api/Admin/dist/img/"+machinename.get(i).getMachine_picture())
                .fit()
                .into(viewHolder.img_name);

        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int i) {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.MACHINE_ID,machinename.get(i).getMachine_id());
                bundle.putString(Constants.MACHINENAMETHAI,machinename.get(i).getMachinenamethai());
                bundle.putString(Constants.MACHINENAMEENG,machinename.get(i).getMachinenameeng());
                bundle.putString(Constants.MACHINEDETAIL,machinename.get(i).getMachine_detail_how_to());
                bundle.putString(Constants.MACHINERULE,machinename.get(i).getMachine_rule_detail());

//                Toast.makeText(view.getContext(), "Number: " + machinename.get(i).getMachine_id() + bundle , Toast.LENGTH_SHORT).show();
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                MachineDetailFragment myFragment = new MachineDetailFragment();
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null)
//                        .hide(view);
                        .replace(R.id.content_frame, myFragment)

                        .commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return machinename.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_machinenameeng, tv_machinenamethai;
        private ItemClickListener clickListener;
        private ImageView img_name;

        public ViewHolder(View view) {
            super(view);

            tv_machinenameeng = (TextView) view.findViewById(R.id.tv_machinenameeng);
            tv_machinenamethai = (TextView) view.findViewById(R.id.tv_status);
            img_name = (ImageView) view.findViewById(R.id.img_name);
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
