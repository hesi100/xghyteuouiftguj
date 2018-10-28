package com.example.hesi100.barber;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by hesi100 on 2/2/2018.
 */

public class mycomments extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycomments);
        Setting_person.setTheme((LinearLayout) findViewById(R.id.background));
        List<Reservation> list=getReservation();
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.Recycle);
        final adapter_reserved adapter = new adapter_reserved(getApplicationContext(),list);
        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(getApplicationContext());
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManagerVertical);
        recyclerView.setAdapter(adapter);
        ImageView im1= (ImageView) findViewById(R.id.navigation);
        final DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.reserved);
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT))
                {
                    mDrawerLayout.closeDrawer(Gravity.RIGHT);
                } else {
                    mDrawerLayout.openDrawer(Gravity.RIGHT);
                }
            }
        });
        final ImageView back= (ImageView) findViewById(R.id.finish);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
    public List<Reservation> getReservation()
    {
        List<Reservation> list=new ArrayList<>();

        Barber a0=new Barber("1","سعید" ," تبریز منجم"," 10%",true);
        Barber a1=new Barber("2","اکبر" ," تبریز یکه دکان"," 15%",true);
        Barber a2=new Barber("2","اکبر" ," تبریز یکه دکان"," 15%",false);

        Reservation r0=new Reservation("-1","-1","finish");
        Reservation r1=new Reservation("-1","-1","finish");
        Reservation r2=new Reservation("-1","-1","finish");
        r0.setBarber(a0);
        r1.setBarber(a1);
        r2.setBarber(a2);
        list.add(r0);
        list.add(r1);
        list.add(r2);
        //sendquery
        return list;
    }
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onResume() {
        super.onResume();
        Setting_person.setTheme((LinearLayout) findViewById(R.id.background));

    }
}
