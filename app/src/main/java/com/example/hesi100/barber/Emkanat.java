package com.example.hesi100.barber;

import android.media.Image;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hesi100 on 4/6/2018.
 */

public class Emkanat {
    String name;
    int id;
    int cost;
    String costandname;
    String url;
    Image i;
    Emkanat( int id,String name,int cost,String url)
    {
        this.id=id;
        this.name=name;
        this.cost=cost;
        costandname=name+" - "+cost;
        this.url=url;
        i=null;
    }
    Emkanat(JSONObject j)
    {
        try {
            id=j.getInt("id");
            cost=j.getInt("cost");
            name=j.getString("service_name");
            url=j.getString("image");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void findimagebylink(String u)
    {
        //i= set image
    }
}
