package com.example.hesi100.barber;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.zarinpal.ewallets.purchase.OnCallbackRequestPaymentListener;
import com.zarinpal.ewallets.purchase.OnCallbackVerificationPaymentListener;
import com.zarinpal.ewallets.purchase.PaymentRequest;
import com.zarinpal.ewallets.purchase.ZarinPal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Baber_reserve extends AppCompatActivity {
    TextView price;
    int x=0;
    int xmo=0;
    int xrish=0;
    CheckBox wash;
    RecyclerView comments;
    TextView puans;
    TextView users;
    TextView fulladdress;
    TextView priceoff;
    TextView shop;
    int xoff=0;
    TextView barberoff;
    TextView date;
    int takhfif;
    Barber barber;
    TextView date_reserve;
    int id;
    String shopname;
    String shahr;
    String mahalle;
    Spinner spinneromo;
    ImageView favorite;
    boolean isfavorite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baber_reserve);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        id= b.getInt("id");
        shopname=  b.getString("shop");
        shahr=b.getString("shahr");
        mahalle=b.getString("mahalle");
        takhfif=b.getInt("takhfif");
        initView();
        boolean isfavorite=b.getBoolean("isfavorite");
        x=0;
        Setting_person.setTheme((LinearLayout) findViewById(R.id.background));
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        getcomments();

        ImageView im= (ImageView) findViewById(R.id.navigation);
        final DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.barber_reserve);
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
        getBarber getbarber= new getBarber();
        getbarber.execute();

        Uri data =getIntent().getData();
        ZarinPal.getPurchase(this).verificationPayment(data, new OnCallbackVerificationPaymentListener() {
            @Override
            public void onCallbackResultVerificationPayment(boolean isPaymentSuccess, String refID, PaymentRequest paymentRequest) {
                if(isPaymentSuccess)
                {
                    Toast.makeText(Baber_reserve.this,"ba movafag shod",Toast.LENGTH_LONG).show();
                    reserve_successful();

                }
            }
        });
    }
    public void initView()
    {
        shop= (TextView) findViewById(R.id.barbername);
        price= (TextView) findViewById(R.id.price);
        users= (TextView) findViewById(R.id.users);
        barberoff= (TextView) findViewById(R.id.barberoff);
        priceoff=(TextView) findViewById(R.id.priceoff);
        date= (TextView) findViewById(R.id.date_reserve);
        fulladdress= (TextView) findViewById(R.id.fulladdress);
        puans=(TextView) findViewById(R.id.puan);
        date_reserve=(TextView) findViewById(R.id.date_reserve);
        spinneromo= (Spinner) findViewById(R.id.spinnermo);
        favorite=(ImageView) findViewById(R.id.favorite);
        wash = (CheckBox) findViewById(R.id.wash);
    }
    public void setActive()
    {

        price.setText(   "قیمت "+x+" تومان");

        //    barber =new Barber(id,shopname,shahr,takhfif,isfavorite);

        if(isfavorite)
        {
            favorite.setImageResource(R.drawable.star1);
            favorite.setTag("star1");
        }
        barberoff.setText(barber.takhfif+"%");
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
        String address=barber.address;
        address=" آدرس کامل : "+address;
        fulladdress.setText(address);

        favorite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
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

        });
//irad


        ArrayAdapter<String> spinneradaptermo = new ArrayAdapter <String>(this, android.R.layout.simple_list_item_1, barber.getlistofemkanatmo());
        spinneromo.setAdapter(spinneradaptermo);
        spinneromo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                xmo=0;
                xmo+=barber.emkanatmo.get(position).cost;
                x=xmo+xrish;
                xoff=x*(takhfif);
                xoff=x-xoff/100;
                priceoff.setText(""+xoff);
                if(wash.isChecked())
                {
                    x+=barber.shost;
                }
                price.setText(   "قیمت "+x+" تومان");
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        final Spinner spinnerrish= (Spinner) findViewById(R.id.spinnerrish);

        ArrayAdapter<String> spinneradapterrish = new ArrayAdapter <String>(this, android.R.layout.simple_list_item_1, barber.getlistofemkanatrish());
        spinnerrish.setAdapter(spinneradapterrish);
        spinnerrish.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                xrish=0;
                xrish+=barber.emkanatrish.get(position).cost;
                x=xmo+xrish;
                xoff=x*(takhfif);
                xoff=x-xoff/100;
                if(wash.isChecked())
                {
                    x+=barber.shost;
                    xoff=x*(takhfif);
                    xoff=xoff/100;
                }
                price.setText(   "قیمت "+x+" تومان");
                priceoff.setText(""+xoff);
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        wash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wash.isChecked()){

                    x=x + barber.shost;
                    xoff=x*(takhfif);
                    xoff=x-xoff/100;

                    price.setText(   "قیمت "+x+" تومان");
                    priceoff.setText(""+xoff);
                }else{
                    x=x - barber.shost;
                    xoff=x*(takhfif);
                    xoff=x-xoff/100;
                    price.setText(   "قیمت "+x+" تومان");
                    priceoff.setText(""+xoff);
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
        settoday();
    }
    public void getcomments()
    {
    List<Comment> list=getcomment();
    comments= (RecyclerView) findViewById(R.id.comments);
    final adapter_comment adapter = new adapter_comment(getApplicationContext(),list);
    LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(getApplicationContext());
    mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
    comments.setLayoutManager(mLinearLayoutManagerVertical);
    comments.setAdapter(adapter);
        final ImageView back= (ImageView) findViewById(R.id.finish);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public List<Comment> getcomment()
    {
        List<Comment> list=new ArrayList<>();
        Comment a0=new Comment("hesi100"," عالی بود","2017/6/23");
        Comment a1=new Comment("hesi200"," خوب بود","2017/6/28");
        list.add(a0);
        list.add(a1);
        //sendquery
        return list;
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    public void settime(View v) {
        // TODO Auto-generated method stub

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(Baber_reserve.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                String minute;
                if(selectedMinute<10)
                {
                    minute="0"+selectedMinute;
                }
                else
                {
                    minute=""+selectedMinute;
                }

                String s=""+selectedHour+" : "+minute;
                TextView t= (TextView) findViewById(R.id.time_reserve);
                t.setText(s);

            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("زمان خود را انتخاب کنید.");
        mTimePicker.show();

    }
    public void setdate(View view)
    {
        final PersianCalendar now = new PersianCalendar();
        final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
            Toast.makeText(getApplicationContext(), "" + year + "/" + monthOfYear + "/" + dayOfMonth, Toast.LENGTH_SHORT).show();
            String dat="" + year + "/" + monthOfYear + "/" + dayOfMonth;
            date.setText(dat);
            }
            }, now.getPersianYear(),
                now.getPersianMonth(),
                now.getPersianDay());
        datePickerDialog.setThemeDark(true);
        datePickerDialog.show(getFragmentManager(), "tpd");
        datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                Log.d("TimePicker", "Dialog was cancelled");
            }
        });

    }
    public void settoday()
    {
        final PersianCalendar now = new PersianCalendar();
        int mYear = now.getPersianYear();
        int mMonth = now.getPersianMonth()+1;
        int mDay = now.getPersianDay();
        date.setText("" + mYear + "/" + mMonth + "/" + mDay);

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
    public void pay(View v)
    {
        if(Internet.isInternetOn()) {
            requestPayment();

        }
        else
        {
            Toast.makeText(this, "لطفا اینترنت خود را باز کنید", Toast.LENGTH_SHORT).show();
        }
    }
    public void reserve_successful()
    {
        //send to sql
        //get verification
        Intent intent = new Intent(this, reserved.class);
        startActivity(intent);

    }
    @Override
    protected void onResume() {
        super.onResume();
        Setting_person.setTheme((LinearLayout) findViewById(R.id.background));

    }
    private void requestPayment() {

        ZarinPal purchase = ZarinPal.getPurchase(this);
        PaymentRequest payment = ZarinPal.getPaymentRequest();

        payment.setMerchantID("aea31c68-43bf-11e8-9db1-005056a205be");
        payment.setAmount(xoff/10);
        payment.setDescription(" پرداخت برای رزرو");
        payment.setCallbackURL("return://zarinpalpayment");

        payment.setMobile("09367584300");            /* Optional Parameters */
        payment.setEmail("hesi100@gmail.com");     /* Optional Parameters */


        purchase.startPayment(payment, new OnCallbackRequestPaymentListener() {
            @Override
            public void onCallbackResultPaymentRequest(int status, String authority, Uri paymentGatewayUri, Intent intent) {


                if (status == 100) {
                    /*
                    When Status is 100 Open Zarinpal PG on Browser
                    */
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Your Payment Failure :(", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    public void see_reserves(View v)
    {
        Intent intent = new Intent(this, seereserve_time.class);
        startActivity(intent);
    }
    public void see_comments(View v)
    {
        Intent intent = new Intent(this, fullcomennt_barber.class);
        startActivity(intent);
    }
    private class getBarber extends AsyncTask<String, Void, String> {

        Internet internet;

        @Override
        protected String doInBackground(String... params) {

            barber = internet.getBarber(internet.isInternetOn() , id);
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            if(barber !=null) {
                barber.shahr=shahr;
                barber.shop=shopname;
                barber.takhfif=takhfif;
                getService getservice=new getService();
                getservice.execute();
            }
            else
            {

            }

        }

        @Override
        protected void onPreExecute() {
            internet = new Internet();
        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
    private class getService extends AsyncTask<String, Void, String> {

        Internet internet;

        @Override
        protected String doInBackground(String... params) {

            barber = internet.getBarberService(internet.isInternetOn() , barber);
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            if(barber !=null) {
                setActive();
            }
            else
            {

            }

        }

        @Override
        protected void onPreExecute() {
            internet = new Internet();
        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}