package com.example.bewkitsada.loginregistration2.models.machineprocess;

/**
 * Created by bewkitsada on 9/5/2560.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bewkitsada.loginregistration2.R;

import java.util.ArrayList;

public class MachineAdapter extends RecyclerView.Adapter<MachineAdapter.ViewHolder> {
    private ArrayList<MachineName> machinename;

    public MachineAdapter(ArrayList<MachineName> machinename) {
        this.machinename = machinename;
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
    }

    @Override
    public int getItemCount() {
        return machinename.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_machinenameeng,tv_machinenamethai;
        public ViewHolder(View view) {
            super(view);

            tv_machinenameeng = (TextView)view.findViewById(R.id.tv_machinenameeng);
            tv_machinenamethai = (TextView)view.findViewById(R.id.tv_machinenamethai);

        }
    }

}
