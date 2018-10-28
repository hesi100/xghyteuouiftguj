package com.example.hesi100.barber;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class forgot_pass extends AppCompatActivity {
    LinearLayout li_findemail;
    LinearLayout li_code;
    LinearLayout li_pass;
    EditText user;
    EditText mail;
    EditText givencode;
    EditText newpass;
    EditText newpassrepeat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        li_findemail= (LinearLayout) findViewById(R.id.li_findemail);
        li_code= (LinearLayout) findViewById(R.id.li_code);
        li_pass= (LinearLayout) findViewById(R.id.li_pass);
        givencode= (EditText) findViewById(R.id.givencode);
        newpass= (EditText) findViewById(R.id.newpass);
        newpassrepeat= (EditText) findViewById(R.id.newpassrepeat);
        user= (EditText) findViewById(R.id.name);
        mail= (EditText) findViewById(R.id.email);


    }
    public void findemail(View v)
    {
        li_findemail.setVisibility(View.GONE);
    }
    public void code(View v)
    {
        li_pass.setVisibility(View.VISIBLE);
    }
    public void pass(View v)
    {
        if(newpass.getText().toString().equals(newpassrepeat.getText().toString()))
        {
            Intent intent=new Intent(this,main.class);
            startActivity(intent);
        }
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}

