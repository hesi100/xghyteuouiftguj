package com.example.hesi100.barber;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class signup extends AppCompatActivity {
    TextView name;
    TextView lastname;

    TextView telefon;
    TextView username;
    TextView password;
    TextView passwordrepeat;
    TextView email;
    Spinner spinnerostan;
    Spinner spinnershahr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_signup);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        spinnerostan = (Spinner)findViewById(R.id.spinnerostan);
        spinnershahr = (Spinner)findViewById(R.id.spinnershahr);
        ArrayAdapter<String> spinneradapter = new ArrayAdapter <String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ostan));
        spinnerostan.setAdapter(spinneradapter);
        spinnerostan.setPrompt(" استان خود را انتخاب کنید ");
        spinnerostan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString(); //this is your selected item

                tayinshahr(spinnershahr,position);
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        ArrayAdapter<String> spinneradapterj = new ArrayAdapter <String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.jensiyat));

        name= (TextView) findViewById(R.id.name);
        lastname= (TextView) findViewById(R.id.lastname);

        telefon = (TextView) findViewById(R.id.telefon);
        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);
        passwordrepeat = (TextView) findViewById(R.id.passwordrepeat);
        email=(TextView) findViewById(R.id.email);

    }

    public void register(View view) {
        if (ok()) {
            SharedPreferences prefs = getSharedPreferences("barber", 0);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("name", name.getText().toString());
            editor.putString("lastname", lastname.getText().toString());
            editor.putString("telefon", telefon.getText().toString());
            editor.putString("username", username.getText().toString());
            editor.putString("password", password.getText().toString());
            editor.putString("email", email.getText().toString());
            editor.putInt("ostan", spinnerostan.getSelectedItemPosition());
            editor.putInt("shahr", spinnershahr.getSelectedItemPosition());
            editor.apply();
            Intent intent=new Intent(this,main.class);
            startActivity(intent);
        }
    }
    private boolean ok() {
        boolean o=true;
        if (name.getText().toString().equals("")) {
            name.setBackgroundResource(R.drawable.edittextfalse);
            Toast.makeText(this, " نام خود را وارد کنید. ", Toast.LENGTH_LONG).show();
            o=false;
        }
        if (lastname.getText().toString().equals("")) {
            lastname.setBackgroundResource(R.drawable.edittextfalse);
            Toast.makeText(this, " نام خانوادگی خود را وارد کنید. ", Toast.LENGTH_LONG).show();
            o=false;
        }

        if (telefon.getText().toString().equals("")) {
            telefon.setBackgroundResource(R.drawable.edittextfalse);
            Toast.makeText(this, " شماره تلفن خود را وارد کنید. ", Toast.LENGTH_LONG).show();
            o=false;
        }
        if(username.getText().toString().equals(""))
        {
            username.setBackgroundResource(R.drawable.edittextfalse);
            Toast.makeText(this," یک حساب کاربری برای خود انتخاب کنید. ",Toast.LENGTH_LONG).show();
            o=false;
        }
        if(password.getText().toString().equals(""))
        {
            password.setBackgroundResource(R.drawable.edittextfalse);
            Toast.makeText(this," رمز برای حساب کاربری را وارد کنید. ",Toast.LENGTH_LONG).show();
            o=false;
        }
        if(passwordrepeat.getText().toString().equals(""))
        {
            passwordrepeat.setBackgroundResource(R.drawable.edittextfalse);
            Toast.makeText(this," رمزی را که وارد کردید اینجا وارد کنید. ",Toast.LENGTH_LONG).show();
            o=false;
        }

        if(spinnerostan.getSelectedItemPosition()==0)
        {
            Toast.makeText(this," استان محل زندگی خود را انتخاب کنید. ",Toast.LENGTH_LONG).show();
        }
        if(spinnershahr.getSelectedItemPosition()==0)
        {
            Toast.makeText(this," شهر محل زندگی خود را انتخاب کنید. ",Toast.LENGTH_LONG).show();
        }
        String pass=password.getText().toString();
        if(!pass.equals(passwordrepeat.getText().toString()))
        {
            password.setBackgroundResource( R.drawable.edittextfalse);
            passwordrepeat.setBackgroundResource( R.drawable.edittextfalse);
            Toast.makeText(this," رمز ها با هم مطابقت نمیکند",Toast.LENGTH_LONG).show();
            o=false;
        }
        return o;
    }

    public void tayinshahr(Spinner spinner, int position) {
        ArrayAdapter<String> spin = null;
        switch (position) {
            case 0:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.nothing));
                break;
            case 1:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ash));
                break;
            case 2:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.agh));
                break;
            case 3:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ardabil));
                break;
            case 4:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.esfahan));
                break;
            case 5:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.alborz));
                break;
            case 6:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ilam));
                break;
            case 7:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.bushehr));
                break;
            case 8:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tehran));
                break;
            case 9:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.chaarmahal));
                break;
            case 10:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.khorasanjonob));
                break;
            case 11:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.khorasanrazvi));
                break;
            case 12:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.khorasanshomal));
                break;
            case 13:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.khozestan));
                break;
            case 14:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.zanjan));
                break;
            case 15:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.semnan));
                break;
            case 16:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.sistan));
                break;
            case 17:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.fars));
                break;
            case 18:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.gazvin));
                break;
            case 19:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.gom));
                break;
            case 20:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kordestan));
                break;
            case 21:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kerman));
                break;
            case 22:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kermanshah));
                break;
            case 23:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.boyerahmad));
                break;
            case 24:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.golestan));
                break;
            case 25:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.gilan));
                break;
            case 26:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.lorestan));
                break;
            case 27:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.mazandaran));
                break;
            case 28:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.markazi));
                break;
            case 29:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.hormozgan));
                break;
            case 30:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.hormozgan));
                break;
            case 31:
                spin = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.yazd));
                break;
        }
        spinner.setAdapter(spin);
        spinner.setPrompt(" شهر خود را انتخاب کنید ");
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}

