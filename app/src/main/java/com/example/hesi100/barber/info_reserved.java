package com.example.hesi100.barber;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.example.hesi100.barber.R.id.puan;

public class info_reserved extends AppCompatActivity {
    int j;
    TextView users;
    TextView fulladdress;
    TextView shop;
    TextView status_reserved;
    TextView barberoff;
    TextView puans;
    Button laghv;
    LinearLayout li_laghv;
    EditText nazar;
    TextView mo;
    TextView rish;
    CheckBox shost;
    ImageView favorite;
    Reservation reservation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_reserved);
        Setting_person.setTheme((LinearLayout) findViewById(R.id.background));
        li_laghv= (LinearLayout) findViewById(R.id.li_laghv);
        final ImageView []im=new ImageView[5];
        im[0]= (ImageView) findViewById(R.id.star0);
        im[1]= (ImageView) findViewById(R.id.star1);
        im[2]= (ImageView) findViewById(R.id.star2);
        im[3]= (ImageView) findViewById(R.id.star3);
        im[4]= (ImageView) findViewById(R.id.star4);
        for(int i=0;i<5;i++)
        {
            final int finalI = i;
            im[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String backgroundImageName = String.valueOf(im[finalI].getTag().toString());
                    j=Integer.parseInt(backgroundImageName);
                    j=j+1;
                    for(int k=0;k<j;k++)
                    {
                        im[k].setImageResource(R.drawable.fill_star);
                    }
                    for(int o=j;o<5;o++)
                    {
                        im[o].setImageResource(R.drawable.empty_star);
                    }
                }
            });
        }
        shop= (TextView) findViewById(R.id.barbername);
        nazar= (EditText) findViewById(R.id.nazar);
        fulladdress= (TextView) findViewById(R.id.fulladdress);
        users= (TextView) findViewById(R.id.users);
        barberoff= (TextView) findViewById(R.id.barberoff);
        shost= (CheckBox) findViewById(R.id.wash);
        puans=(TextView) findViewById(puan);
        status_reserved=(TextView) findViewById(R.id.status_reserved);
        laghv= (Button) findViewById(R.id.cancel);
        mo= (TextView) findViewById(R.id.spinnermo);
        rish=(TextView) findViewById(R.id.spinnerrish);
        favorite= (ImageView) findViewById(R.id.favorite);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        String shopname=  b.getString("shop");
        String shahr=b.getString("shahr");
        String mahalle=b.getString("mahalle");
        String takhfif=b.getString("takhfif");
        boolean isfavorite=b.getBoolean("isfavorite");
        String idreserve=b.getString("idreserve","-1");
        String idbarber=b.getString("idbarber","-1");
        String status=b.getString("status");
        Reservation reservation=new Reservation(idreserve,idbarber,status);
        reservation.setOthers(idreserve);
        shost.setChecked(reservation.shost);
        mo.setText(reservation.hair);
        rish.setText(reservation.rish);
        final Barber barber =new Barber("-1",shopname,shahr,takhfif,isfavorite);
        barber.setother();
        reservation.setBarber(barber);
        barberoff.setText(barber.takhfif);
        status_reserved.setText(status);
        shop.setText(barber.shop);
        if (barber.user!=0)
        {
            users.setText(""+barber.user);
            float puan=barber.puan;
            puans.setText(""+puan);
            if (puan>4)
            {
                puans.setTextColor(getResources().getColor(R.color.perfect));
            }
            else if(puan>3)
            {
                puans.setTextColor(getResources().getColor(R.color.nice));
            }
            else if(puan>2)
            {
                puans.setTextColor(getResources().getColor(R.color.average));
            }
            else if(puan>1)
            {
                puans.setTextColor(getResources().getColor(R.color.bad));
            }
            else
            {
                puans.setTextColor(getResources().getColor(R.color.terrible));
            }

        }
        if(reservation.barber.isfavorite)
        {
            favorite.setImageResource(R.drawable.star1);
            favorite.setTag("star1");
        }
        if(status.equals("not"))
        {
            li_laghv.setVisibility(View.GONE);
        }
        String address=barber.address;
        address=" آدرس کامل : "+address;
        fulladdress.setText(address);
        final ImageView back= (ImageView) findViewById(R.id.finish);
        ImageView im1= (ImageView) findViewById(R.id.navigation);
        final DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.barber_reserve);
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    mDrawerLayout.closeDrawer(Gravity.RIGHT);
                } else {
                    mDrawerLayout.openDrawer(Gravity.RIGHT);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    public void isfavorite(View v)
    {
        String backgroundImageName = String.valueOf(favorite.getTag());
        if(backgroundImageName.equals("star1"))
        {

            favorite.setImageResource(R.drawable.star2);
            favorite.setTag("star2");

        }
        else
        {
            favorite.setImageResource(R.drawable.star1);
            favorite.setTag("star1");
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        Setting_person.setTheme((LinearLayout) findViewById(R.id.background));

    }
}