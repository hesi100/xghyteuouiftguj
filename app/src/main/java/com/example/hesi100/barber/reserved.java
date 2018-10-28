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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class reserved extends AppCompatActivity {
    Spinner search;
    TextView searchby;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_reserved);
        Setting_person.setTheme((LinearLayout) findViewById(R.id.background));
        ImageView im= (ImageView) findViewById(R.id.navigation);
        final DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.reserved);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    mDrawerLayout.closeDrawer(Gravity.RIGHT);
                } else {
                    mDrawerLayout.openDrawer(Gravity.RIGHT);
                }
            }
        });
        searchby= (TextView) findViewById(R.id.searchby);
        search = (Spinner)findViewById(R.id.search);
        ArrayAdapter<String> spinneradapter = new ArrayAdapter <String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.searchby));
        search.setAdapter(spinneradapter);
        search.setPrompt(" نوع جستجو خود را انتخاب کنید ");
        search.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String s;
                search.getSelectedItem().toString();
                s= " جستجو بر اساس نام "+search.getSelectedItem().toString()+" ...";
                searchby.setHint(s);

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        List<Reservation> list=getReservations();
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.Recycle);
        final adapter_reserved adapter = new adapter_reserved(getApplicationContext(),list);
        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(getApplicationContext());
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManagerVertical);
        recyclerView.setAdapter(adapter);
        final ImageView back= (ImageView) findViewById(R.id.finish);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public List<Reservation> getReservations()
    {

        List<Reservation> list=new ArrayList<>();
        Reservation a0=new Reservation("-1","-1","reserved");
        Barber b0=new Barber("1","سعید"," تبریز منجم"," 10%",true);
        a0.setBarber(b0);
        Reservation a1=new Reservation("-1","-1","not");
        Barber b1=new Barber("2","اکبر"," تبریز یکه دکان"," 15%",true);
        a1.setBarber(b1);
        list.add(a0);
        list.add(a1);
        return list;

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    public void reserved_suc()
    {
        //send to db
        //get id
    }
    @Override
    protected void onResume() {
        super.onResume();
        Setting_person.setTheme((LinearLayout) findViewById(R.id.background));

    }
}


