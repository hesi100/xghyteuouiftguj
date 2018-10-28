package com.example.hesi100.barber;

import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hesi100 on 6/17/2018.
 */

public class getclasses {
    public static List<Barber> setBarbers()
    {
        JSONObject jsonObject=null;
        List <Barber> barbers;
        JSONObject jbarber;
        barbers = new ArrayList<>();
        int i=0;
        try {
            jbarber=jsonObject.getJSONObject("barber0");
            while(jbarber!=null)
            {
                Barber b=new Barber(jbarber.getString("id"), jbarber.getString("shop") , jbarber.getString("address") , jbarber.getString("takhfif") , jbarber.getBoolean("isfavorite") );
                barbers.add(b);
                i++;
                jbarber=jsonObject.getJSONObject("barber"+i);
            }
        }
        catch (NullPointerException e )
        {
            Toast.makeText(app.context, "آرایشگاهی نزدیک به اینجا نیست", Toast.LENGTH_SHORT).show();
        }
        catch (JSONException e ) {
            e.printStackTrace();
        }

        return barbers;
    }
    public static List<Barber> setBarbersfavorite()
    {
        JSONObject jsonObject=null;
        List <Barber> barbers;
        JSONObject jbarber;
        barbers = new ArrayList<>();
        int i=0;
        try {
            jbarber=jsonObject.getJSONObject("barber0");
            while(jbarber!=null)
            {
                Barber b=new Barber(jbarber.getString("id"), jbarber.getString("shop") , jbarber.getString("address") , jbarber.getString("takhfif") , jbarber.getBoolean("isfavorite") );
                barbers.add(b);
                i++;
                jbarber=jsonObject.getJSONObject("barber"+i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return barbers;
    }
    public static List<Barber> setBarbersoff()
    {
        List <Barber> barbers;
        JSONObject jsonObject=null;
        JSONObject jbarber;
        barbers = new ArrayList<>();
        int i=0;
        try {
            jbarber=jsonObject.getJSONObject("barber0");
            while(jbarber!=null)
            {
                Barber b=new Barber(jbarber.getString("id"), jbarber.getString("shop") , jbarber.getString("address") , jbarber.getString("takhfif") , jbarber.getBoolean("isfavorite") );
                barbers.add(b);
                i++;
                jbarber=jsonObject.getJSONObject("barber"+i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return barbers;
    }
    public static List<Comment> setComennets()
    {
        //id barber

            JSONObject jsonObject=null;
            List <Comment> coments;
            JSONObject jbarber;
            coments = new ArrayList<>();
            int i=0;
            try {
                jbarber=jsonObject.getJSONObject("comment0");
                while(jbarber!=null)
                {
                    Comment b=new Comment(jbarber.getString("username"), jbarber.getString("nazar") , jbarber.getString("date"));
                    coments.add(b);
                    i++;
                    jbarber=jsonObject.getJSONObject("comment"+i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return coments;

    }
    public static List<Comment> setmyComennets()
    {
        //id customer

        JSONObject jsonObject=null;
        List <Comment> coments;
        JSONObject jbarber;
        coments = new ArrayList<>();
        int i=0;
        try {
            jbarber=jsonObject.getJSONObject("comment0");
            while(jbarber!=null)
            {
                Comment b=new Comment(jbarber.getString("username"), jbarber.getString("nazar") , jbarber.getString("date"));
                coments.add(b);
                i++;
                jbarber=jsonObject.getJSONObject("comment"+i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return coments;

    }
    public static List<Reservation> setReserve()
    {
        //id customer

        JSONObject jsonObject=null;
        List <Reservation> reservations;
        JSONObject jbarber;
        reservations = new ArrayList<>();
        int i=0;
        try {
            jbarber=jsonObject.getJSONObject("reservation0");
            while(jbarber!=null)
            {
                Reservation b=new Reservation(jbarber.getString("idreserve"), jbarber.getString("idbarber") , jbarber.getString("status"));
                reservations.add(b);
                i++;
                jbarber=jsonObject.getJSONObject("reservation"+i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reservations;

    }

}
