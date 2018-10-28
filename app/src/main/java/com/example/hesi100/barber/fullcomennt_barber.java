package com.example.hesi100.barber;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class fullcomennt_barber extends AppCompatActivity {
    RecyclerView recycler;
    int id;
    List<Comment> comments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullcomennt_barber);
        comments = new ArrayList<>();
        recycler=(RecyclerView) findViewById(R.id.recycler_comment);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        id=b.getInt("idbarber");
        getListComments getListCommentS=new getListComments();
        getListCommentS.execute();
        // send request

    }

    public void setRecycler() {
        final adapter_comment adapter = new adapter_comment(getApplicationContext(),comments);
        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(getApplicationContext());
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(mLinearLayoutManagerVertical);
        recycler.setAdapter(adapter);
    }

    private class getListComments extends AsyncTask<String, Void, String> {

        Internet internet;
        @Override
        protected void onPreExecute() {
            internet = new Internet();
            comments=new ArrayList<>();
        }
        @Override
        protected String doInBackground(String... params) {

            comments = internet.getListBarberComment(internet.isInternetOn() , id);
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            if(comments.size()>0) {
                setRecycler();
            }
            else
            {
                Toast.makeText(fullcomennt_barber.this, "نظری برای آرایشگاه یافت نشد", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
