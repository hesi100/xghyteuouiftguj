package com.example.hesi100.barber;

import android.media.Image;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hesi100 on 11/30/2017.
 */

public class User {
    String id;
    String name;
    String etebar;
    String x;
    String y;
    String tel;
    String email;
    String username;
    String password;
    String linkprofile;
    String ostan;
    String city;

    Image profile;

    //foto
    User()
    {

    }
    User(String id, String name, String etebar, String x, String y)
    {
        this.id=id;
        this.name=name;
        this.etebar=etebar;
        this.x=x;
        this.y=y;
    }
    public void userjson(JSONObject jsonObject)
    {
        try {
            tel = jsonObject.getString("tel");
            email = jsonObject.getString("email");
            linkprofile = jsonObject.getString("linkprofile");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public String sendloaction()
    {
        return "Task [lat=" + x + ", long=" + y +"]";
    }

        public String classtojson()
        {
            return "Task [id=" + id + ", lat=" + x + ", long=" + y + ", name=" + name + ", tel=" + tel
                    + ",email=" +email + "]";
        }

}
