package com.example.bewkitsada.loginregistration2.models.booked_process;

/**
 * Created by bewkitsada on 9/5/2560.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bewkitsada.loginregistration2.R;
import com.example.bewkitsada.loginregistration2.models.ItemClickListener;
import com.example.bewkitsada.loginregistration2.models.LongClickListener;
import com.example.bewkitsada.loginregistration2.string.Constants;

import java.util.ArrayList;

public class BookedAdapter extends RecyclerView.Adapter<BookedAdapter.ViewHolder> {
    private ArrayList<Booked> booked;
    private Context context;
    private LayoutInflater inflater;
    private SharedPreferences pref;


    public BookedAdapter(Context context, ArrayList<Booked> booked) {
        this.booked = booked;
        this.context = context;

    }


    public BookedAdapter() {

    }

    @Override
    public BookedAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_booked_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BookedAdapter.ViewHolder viewHolder, int i) {
//        final Booked book = booked.get(i);
        viewHolder.tv_name.setText(booked.get(i).getMachine_nameeng());
        viewHolder.tv_start.setText(booked.get(i).getStart_date());
        viewHolder.tv_end.setText(booked.get(i).getEnd_date());
        viewHolder.tv_status.setText(booked.get(i).getEvent_status_detail());

//        Context context = viewHolder.img_android.getContext();
//        Picasso.with(context)
//                .load(ml.getMachine_location_picture())
//                .placeholder()
//                .fit()
//                .into(viewHolder.img_android);
        viewHolder.setOnLongClickListener(new LongClickListener() {
            @Override
            public boolean onLongClick(View view, int position) {
                Toast.makeText(view.getContext(), "LongOnClick", Toast.LENGTH_LONG).show();
                Log.i("LongClick", "OnLong");
                final CharSequence[] items = {"อนุมัติ", "ไม่อนุมัติ"};

                return true;
            }
        });
        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int i) {
                Toast.makeText(view.getContext(), "OnClick", Toast.LENGTH_SHORT).show();

            }
        });
//        viewHolder.set
//
    }

    @Override
    public int getItemCount() {
        return booked.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnLongClickListener, View.OnCreateContextMenuListener {
        private TextView tv_start, tv_end, tv_status, tv_name;
        private ItemClickListener clickListener;
        private LongClickListener longClickListener;
        private ImageView img_android;

        public ViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_start = (TextView) view.findViewById(R.id.tv_start);
            tv_end = (TextView) view.findViewById(R.id.tv_end);
            tv_status = (TextView) view.findViewById(R.id.tv_status);
            img_android = (ImageView) view.findViewById(R.id.img_android);
//            view.setOnClickListener(this);
//            view.setOnLongClickListener(this);
            view.setOnCreateContextMenuListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        public void setOnLongClickListener(LongClickListener longClickListener) {
            this.longClickListener = longClickListener;
        }


        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            longClickListener.onLongClick(view, getAdapterPosition());
            return true;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            pref = context.getSharedPreferences("first", Context.MODE_PRIVATE);
            MenuItem approve;
            MenuItem deapprove;
            if (pref.getString(Constants.STATUS_DETAIL, "").equals("Teacher")) {
                approve = menu.add(Menu.NONE, 1, 1, "อนุมัติ");
                deapprove = menu.add(Menu.NONE, 2, 2, "ไม่อนุมัติ");
                approve.setOnMenuItemClickListener(onClickMenu);
                deapprove.setOnMenuItemClickListener(onClickMenu);
            }

        }

        private final MenuItem.OnMenuItemClickListener onClickMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case 1:
                        Toast.makeText(context, "approve", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(context, "deapprove", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        };

        private void get() {
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(Constants.BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//            RequestInterface requestInterface = retrofit.create(RequestInterface.class);
//
        }

    }

}
