package com.example.hesi100.barber;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hesi100 on 12/18/2017.
 */

public class Comment {
    String username;
    String nazar;
    String date;
    Comment()
    {

    }
    Comment(String username, String nazar, String date)
    {
        this.username=username;
        this.nazar=nazar;
        this.date=date;
    }
    public void fromJson(JSONObject jsonObject) {
        // by id barber
        // Deserialize json into object fields
        try {
            username = jsonObject.getString("username");
            nazar = jsonObject.getString("nazar");
            date = jsonObject.getString("date");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
