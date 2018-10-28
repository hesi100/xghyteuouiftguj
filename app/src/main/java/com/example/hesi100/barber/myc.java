package com.example.hesi100.barber;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.example.hesi100.barber.R.id.puan;

public class myc extends AppCompatActivity {
    TextView users;
    TextView fulladdress;
    TextView shop;
    TextView barberoff;
    TextView puans;
    TextView mo;
    TextView status_reserved;
    TextView rish;
    CheckBox shost;
    EditText nazar;
    ImageView favorite;
    Barber barber;
    Reservation reservation;
    String shopname;
    String shahr;
    String mahalle;
    String takhfif;
    boolean isfavorite;
    String idreserve;
    String idbarber;
    String status;
    ImageView []im=new ImageView[5];
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myc);
        initView();
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        shopname=  b.getString("shop");
        shahr=b.getString("shahr");
        mahalle=b.getString("mahalle");
        takhfif=b.getString("takhfif");
        isfavorite=b.getBoolean("isfavorite");
        idreserve=b.getString("idreserve","-1");
        idbarber=b.getString("idbarber","-1");
        status=b.getString("status");

        reservation.setOthers(idreserve);
        shost.setChecked(reservation.shost);
        mo.setText(reservation.hair);
        rish.setText(reservation.rish);

        barber.setother();
        reservation.setBarber(barber);
        barberoff.setText(barber.takhfif);
        status_reserved.setText(status);
        shop.setText(barber.shop);
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
        String address=barber.address;
        address=" آدرس کامل : "+address;
        fulladdress.setText(address);


        for(int k=0;k<reservation.puan;k++)
        {
            im[k].setImageResource(R.drawable.fill_star);
        }
        if(reservation.barber.isfavorite)
        {
            favorite.setImageResource(R.drawable.star1);
            favorite.setTag("star1");
        }
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
    public void initView()
    {
        Setting_person.setTheme((LinearLayout) findViewById(R.id.background));
        favorite= (ImageView) findViewById(R.id.favorite);
        mo= (TextView) findViewById(R.id.spinnermo);
        rish= (TextView) findViewById(R.id.spinnerrish);
        shost= (CheckBox) findViewById(R.id.wash);
        status_reserved=(TextView) findViewById(R.id.status_reserved);
        shop= (TextView) findViewById(R.id.barbername);
        nazar= (EditText) findViewById(R.id.nazar);
        fulladdress= (TextView) findViewById(R.id.fulladdress);
        users= (TextView) findViewById(R.id.users);
        barberoff= (TextView) findViewById(R.id.barberoff);
        puans=(TextView) findViewById(puan);
        im[0]= (ImageView) findViewById(R.id.star0);
        im[1]= (ImageView) findViewById(R.id.star1);
        im[2]= (ImageView) findViewById(R.id.star2);
        im[3]= (ImageView) findViewById(R.id.star3);
        im[4]= (ImageView) findViewById(R.id.star4);
        back= (ImageView) findViewById(R.id.finish);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Setting_person.setTheme((LinearLayout) findViewById(R.id.background));

    }

}
