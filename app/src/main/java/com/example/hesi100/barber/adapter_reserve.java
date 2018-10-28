package com.example.hesi100.barber;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hesi100 on 12/15/2017.
 */

public class adapter_reserve extends RecyclerView.Adapter<adapter_reserve.myViewHolder> {
    Context context;
    List<reserve_time> reserve_times;

    public adapter_reserve(Context context,List<reserve_time> b)
    {
        reserve_times=b;
        this.context=context;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_reserve, parent  , false);
        return new adapter_reserve.myViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        holder.time.setText(reserve_times.get(position).time);
        holder.start_time.setText(reserve_times.get(position).start_time);
        holder.end_time.setText(reserve_times.get(position).end_time);
    }
    public int getItemCount() {
        return reserve_times.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder
    {
        TextView time;
        TextView start_time;
        TextView end_time;
        myViewHolder(View itemView) {

            super(itemView);
            time= (TextView) itemView.findViewById(R.id.time);
            start_time=(TextView) itemView.findViewById(R.id.start_time);
            end_time=(TextView) itemView.findViewById(R.id.end_time);

        }
    }
}

