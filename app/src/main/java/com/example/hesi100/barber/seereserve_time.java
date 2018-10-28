package com.example.hesi100.barber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class seereserve_time extends AppCompatActivity {
    RecyclerView recyclerView;
    List<reserve_time> reserve_times;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seereserve_time);
        reserve_times=new ArrayList<>();
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
//        String id=b.getString("idbarber","");

        //get reserve of today
        recyclerView= (RecyclerView) findViewById(R.id.reserves);

        final adapter_reserve adapter = new adapter_reserve(getApplicationContext(),get_reserve_time());
        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(getApplicationContext());
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManagerVertical);
        recyclerView.setAdapter(adapter);
    }
    public List<reserve_time> get_reserve_time()
    {
        List<reserve_time> l=new ArrayList<>();
        reserve_time r1=new reserve_time("45","12:00","12:45");
        reserve_time r2=new reserve_time("45","13:00","13:45");
        l.add(r1);
        l.add(r2);
        return  l;
    }
}

