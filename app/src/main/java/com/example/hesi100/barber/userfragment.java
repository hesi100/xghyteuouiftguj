package com.example.hesi100.barber;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class userfragment extends Fragment {
    public userfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_userfragment, container, false);
        LinearLayout my_reserved= (LinearLayout) v.findViewById(R.id.my_reserved);
        LinearLayout my_wallet= (LinearLayout) v.findViewById(R.id.my_wallet);
        LinearLayout support= (LinearLayout) v.findViewById(R.id.support);
        LinearLayout setting= (LinearLayout) v.findViewById(R.id.setting);
        LinearLayout comment= (LinearLayout) v.findViewById(R.id.comment);
        LinearLayout info= (LinearLayout) v.findViewById(R.id.myinfo);
        LinearLayout setting_back= (LinearLayout) v.findViewById(R.id.setting_back);
        ImageView home= (ImageView) v.findViewById(R.id.home);
        my_reserved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),reserved.class);
                startActivity(intent);
            }
        });
        setting_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),changeback.class);
                startActivity(intent);
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),about_us.class);
                startActivity(intent);
            }
        });
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),mycomments.class);
                startActivity(intent);
            }
        });
        my_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),my_wallet.class);
                startActivity(intent);
            }
        });
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),support.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),main.class);
                startActivity(intent);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),setting.class);
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return v;

    }

}