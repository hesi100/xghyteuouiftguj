package com.example.hesi100.barber;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class changeback extends AppCompatActivity {

    LinearLayout back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeback);
        back= (LinearLayout) findViewById(R.id.backg);
        SharedPreferences prefs = getSharedPreferences("belz", 0);
        String theme=prefs.getString("theme","no");
        if(theme.equals("no"))
        {
            back.setBackground(getResources().getDrawable(R.drawable.back0));
        }
        else
        {
            int res=getResources().getIdentifier(theme,"drawable",getPackageName());
            back.setBackground(getResources().getDrawable(res));
        }
        final DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.my_wallet);
        ImageView im= (ImageView) findViewById(R.id.navigation);
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

        final ImageView back= (ImageView) findViewById(R.id.finish);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void setback(View v)
    {
        String theme=v.getTag().toString();
        int res=getResources().getIdentifier(theme,"drawable",getPackageName());
        back.setBackground(getResources().getDrawable(res));
        SharedPreferences prefs = getSharedPreferences("belz", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("theme", theme);
        editor.apply();
        Toast.makeText(getApplicationContext()," عکس پس زمینه تغییر یافت",Toast.LENGTH_LONG).show();
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
