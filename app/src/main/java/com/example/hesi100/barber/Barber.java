package com.example.hesi100.barber;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hesi100 on 11/21/2017.
 */

public class Barber {

    String shop;
    int id;
    String address;
    String shahr;
    String mahalle;
    int takhfif;
    float puan;
    int user;
    int shost;
    String urlimage;
    List<Emkanat> emkanatmo;
    List<Emkanat> emkanatrish;
    boolean isfavorite;

    Barber(int id, String shop, String address, String takhfif, boolean isfavorite)
    {
        this.id=id;
        this.shop=shop;
        this.address=address;
        this.takhfif=takhfif;
        this.isfavorite=isfavorite;
        mahalle="زعفرانیه";
        shahr="تبریز";
        this.emkanatmo=new ArrayList<>();
        this.emkanatrish=new ArrayList<>();
    }
    Barber(JSONObject jsonObject)
    {
        try {
            id = jsonObject.getInt("id");
            shop = jsonObject.getString("shop");
            address  = jsonObject.getString("images");
            takhfif = jsonObject.getString("takhfif");
            urlimage = jsonObject.getString("takhfif");
            isfavorite = Boolean.getBoolean(jsonObject.getString("isfavorite"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    Barber(int id,String shop,String city,String mahalle)
    {
        this.id=id;
        this.shop=shop;
        this.shahr=city;
        this.mahalle=mahalle;
    }
    Barber(String shop,String city,String mahalle)
    {

        this.shop=shop;
        this.shahr=city;
        this.mahalle=mahalle;
    }
    public List<String> getlistofemkanatmo()
    {
        List<String> emkanathair=new ArrayList<>();
        for(int i=0;i<emkanatmo.size();i++)
        {
            emkanathair.add(emkanatmo.get(i).costandname);
        }
        return emkanathair;
    }
    public List<String> getlistofemkanatrish()
    {
        List<String> emkanatri=new ArrayList<>();
        for(int i=0;i<emkanatrish.size();i++)
        {
            emkanatri.add(emkanatrish.get(i).costandname);
        }
        return emkanatri;
    }

    public void setother()
    {
        //seto();
        this.shost=5000;
        this.puan= (float) 3.5;
        this.user=25;
        this.setEmkanat();
        this.address=" تبریز حجتی خیابان قطران جدید پلاک 18";

    }
    public String classtojson_o()
    {
        return "Task [id=" + id + ", shop=" + shop + ", address=" + address + ", takhfif=" + takhfif + "]";
    }

    public String classtojson_others()
    {
        return "Task [shost=" +shost + ",puan=" +puan + ",user=" +user+ "]";
    }
    public void setEmkanat() {


    }

    public void setother(JSONObject jsonObject) {
        try {
            puan = Float.parseFloat(jsonObject.getString("puan"));
            user = Integer.parseInt(jsonObject.getString("user"));
            address = jsonObject.getString("address");
            takhfif = jsonObject.getString("takhfif");
            isfavorite = Boolean.getBoolean(jsonObject.getString("isfavorite"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public String getphone()
    {
        // get phone by id or username
        return "0914";
    }



}