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
import android.widget.Toast;

import java.util.List;

/**
 * Created by hesi100 on 8/27/2017.
 */

public class adapter extends RecyclerView.Adapter<adapter.myViewHolder> {
    Context context;
    List<Barber> barber;

    public adapter(Context context,List<Barber> b)
    {
        barber=b;
        this.context=context;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle, parent  , false);
        return new myViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, final int position) {
        holder.barbername.setText(barber.get(position).shop);
        holder.barberaddress.setText(barber.get(position).mahalle+" - "+barber.get(position).shahr);
        holder.barberoff.setText(barber.get(position).takhfif+"%");
        LinearLayout b = (LinearLayout) holder.itemView.findViewById(R.id.li);
        final ImageView favorite=(ImageView)  holder.itemView.findViewById(R.id.favorite);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context , Baber_reserve.class);
                intent.putExtra("id",barber.get(position).id);
                intent.putExtra("shop",barber.get(position).shop);
                intent.putExtra("shahr",barber.get(position).shahr);
                intent.putExtra("mahalle",barber.get(position).mahalle);
                intent.putExtra("takhfif",barber.get(position).takhfif);
                intent.putExtra("isfavorite",barber.get(position).isfavorite);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });


        favorite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String backgroundImageName = String.valueOf(favorite.getTag());
                if(backgroundImageName.equals("star1"))
                {

                   favorite.setImageResource(R.drawable.star2);
                    favorite.setTag("star2");
                    barber.get(position).isfavorite=false;

                }
                else
                {
                    favorite.setImageResource(R.drawable.star1);
                    favorite.setTag("star1");
                    barber.get(position).isfavorite=true;
                }
                Toast.makeText(context," clicked ",Toast.LENGTH_LONG).show();


            }
        });
    }
    public int getItemCount() {
        return barber.size();
    }

    static class myViewHolder extends RecyclerView.ViewHolder
    {
        TextView barbername;
        TextView barberaddress;
        TextView barberoff;
        myViewHolder(View itemView) {

            super(itemView);
            barbername= (TextView) itemView.findViewById(R.id.barbername);
            barberaddress=(TextView) itemView.findViewById(R.id.barberaddress);
            barberoff=(TextView) itemView.findViewById(R.id.barberoff);
        }
    }

}