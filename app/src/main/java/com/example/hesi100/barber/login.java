package com.example.hesi100.barber;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class login extends AppCompatActivity {
    EditText username;
    EditText pass;
    JSONObject jsonObject ;
    String res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       //// if (android.os.Build.VERSION.SDK_INT > 9) {
       //     StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        ///    StrictMode.setThreadPolicy(policy);
       // }
        jsonObject= new JSONObject();
        Setting_person.setTheme((LinearLayout) findViewById(R.id.background));
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        username= (EditText) findViewById(R.id.username);
        pass= (EditText) findViewById(R.id.pass);

    }
    public void signin(View view) throws JSONException {

        jsonObject.put("user",username.getText().toString().trim());
        jsonObject.put("pass",pass.getText().toString().trim());
        jsonObject.put("gender","male");
        Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
        new LongOperation().execute("");

    }

    public void signup(View view)
    {
        Intent intent=new Intent(this,signup.class);
        startActivity(intent);
    }
    public void forgotpass(View view)
    {
        Intent intent=new Intent(this,forgot_pass.class);
        startActivity(intent);
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onResume() {
        super.onResume();
        Setting_person.setTheme((LinearLayout) findViewById(R.id.background));

    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        Internet internet;

        @Override
        protected String doInBackground(String... params) {

                    res = internet.userLogin(internet.isInternetOn() , jsonObject.toString());
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), res, Toast.LENGTH_SHORT).show();

            if(res.equals("invalid")) {
                Toast.makeText(login.this, "نام کاربری یا رمز عبور نامعتبر می باشد", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Intent intent = new Intent(getApplicationContext(), main.class);
                startActivity(intent);
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

