package com.example.hesi100.barber;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hesi100 on 12/15/2017.
 */

public class adapter_reserved extends RecyclerView.Adapter<adapter_reserved.myViewHolder> {
    Context context;
    List<Reservation> reservation;

    public adapter_reserved(Context context,List<Reservation> R)
    {
        reservation=R;
        this.context=context;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_status, parent  , false);
        return new adapter_reserved.myViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(adapter_reserved.myViewHolder holder, final int position) {
        holder.barbername.setText(reservation.get(position).barber.shop);
        holder.barberaddress.setText(reservation.get(position).barber.address);
        holder.status.setText(reservation.get(position).status);
        LinearLayout b = (LinearLayout) holder.itemView.findViewById(R.id.li);
        final ImageView favorite=(ImageView)  holder.itemView.findViewById(R.id.favorite);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(reservation.get(position).status.equals("finish")) {
                    Intent intent = new Intent(context, myc.class);
                    intent.putExtra("id", reservation.get(position).idreserve);
                    intent.putExtra("shop", reservation.get(position).barber.shop);
                    intent.putExtra("shahr", reservation.get(position).barber.shahr);
                    intent.putExtra("mahalle", reservation.get(position).barber.mahalle);
                    intent.putExtra("address", reservation.get(position).barber.address);
                    intent.putExtra("takhfif", reservation.get(position).barber.takhfif);
                    intent.putExtra("isfavorite", reservation.get(position).barber.isfavorite);
                    intent.putExtra("status", reservation.get(position).status);
                    intent.putExtra("idreserve", reservation.get(position).idreserve);
                    intent.putExtra("idbarber", reservation.get(position).idbarber);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(context, info_reserved.class);
                    intent.putExtra("id", reservation.get(position).idreserve);
                    intent.putExtra("shop", reservation.get(position).barber.shop);
                    intent.putExtra("shahr", reservation.get(position).barber.shahr);
                    intent.putExtra("mahalle", reservation.get(position).barber.mahalle);
                    intent.putExtra("address", reservation.get(position).barber.address);
                    intent.putExtra("takhfif", reservation.get(position).barber.takhfif);
                    intent.putExtra("isfavorite", reservation.get(position).barber.isfavorite);
                    intent.putExtra("status", reservation.get(position).status);
                    intent.putExtra("idreserve", reservation.get(position).idreserve);
                    intent.putExtra("idbarber", reservation.get(position).idbarber);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
        if (reservation.get(position).barber.isfavorite)
        {
            favorite.setImageResource(R.drawable.star1);
            favorite.setTag("star1");
        }
        favorite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String backgroundImageName = String.valueOf(favorite.getTag());
                if(backgroundImageName.equals("star1"))
                {

                    favorite.setImageResource(R.drawable.star2);
                    favorite.setTag("star2");
                    reservation.get(position).barber.isfavorite=false;

                }
                else
                {
                    favorite.setImageResource(R.drawable.star1);
                    favorite.setTag("star1");
                    reservation.get(position).barber.isfavorite=true;
                }
            }
        });
    }
    public int getItemCount() {
        return reservation.size();
    }
    class myViewHolder extends RecyclerView.ViewHolder
    {
        TextView barbername;
        TextView barberaddress;
        TextView status;
        myViewHolder(View itemView) {
            super(itemView);
            barbername= (TextView) itemView.findViewById(R.id.barbername);
            barberaddress=(TextView) itemView.findViewById(R.id.barberaddress);
            status=(TextView) itemView.findViewById(R.id.status);
        }
    }


}
