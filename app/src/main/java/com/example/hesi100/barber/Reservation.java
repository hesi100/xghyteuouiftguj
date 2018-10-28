package com.example.hesi100.barber;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hesi100 on 3/16/2018.
 */

public class Reservation
    {
    int idreserve;
    int idbarber;
    int idcustomer;
    String hair;
    String rish;
    int off;
    int got_cost_service;
    boolean shost;
    String comment;
    String status;
    int puan;
    int cost;
    Barber barber;
    String date;
    String time;
    Reservation(int idreserve,int idbarber,String status)  {
        this.idreserve=idreserve;
        this.idbarber=idbarber;
        this.status=status;
        findbarber();

    }
    Reservation(int idbarber,int idcustomer)
    {
        this.idcustomer=idcustomer;
        this.idbarber=idbarber;
    }
    Reservation()
    {

    }
    Reservation (JSONObject jsonObject)
    {

        try {
            idbarber = jsonObject.getInt("id_barber");
            idreserve = jsonObject.getInt("id_reserve");
            idcustomer= jsonObject.getInt("idreserve");
            status = jsonObject.getString("status");
            hair = (jsonObject.getString("mo"));
            rish=(jsonObject.getString("rish"));
            shost = Boolean.getBoolean(jsonObject.getString("shost"));
            puan = Integer.parseInt(jsonObject.getString("puan"));
            cost = Integer.parseInt(jsonObject.getString("cost"));
        } catch (JSONException e) {
            e.printStackTrace();

        }

    }
    Reservation(int id,int idbarber,String status,String shop_name,String city,String mahale)
    {
        this.idreserve=id;
        this.status=status;
        barber = new Barber(idbarber,shop_name,city,mahale);

    }
    //'id' => $fetced_data['id'], 'date' => $fetced_data['date'], 'shop_name'=> $fetced_data['shop_name'], 'time'=>$fetced_data['time'], 'city'=>$fetced_data['city'], 'mahale'=>$fetced_data['mahale'], 'comment_customer'=>$fetced_data['comment_customer']
    Reservation(int id,String date,String shop_name,String time,String city,String mahale,String comment_customer)
    {
        this.idreserve=id;
        this.date=date;
        Barber barber=new Barber(shop_name,city,mahale);
        this.time=time;
        this.comment=comment_customer;
    }
    public void setBarber(Barber barber)
    {
        this.barber=barber;
    }
    public void findbarber()  {
//        JSONObject jsonObject=null;
//        try {
//            barber = new Barber(idbarber, jsonObject.getString("shop"), jsonObject.getString("address"), jsonObject.getString("takhfif"),jsonObject.getBoolean("isfavorite"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }
    public void setOthers(String idreserve)
    {
        this.hair="بوکسری";
        this.rish="ته ریش";
        this.shost=true;
        this.comment="عالی";
        this.puan=4;
        this.cost=9000;
    }
    public JSONObject getJsonService()
    {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("hair",hair);
            jsonObject.put("rish",rish);
            jsonObject.put("shost",shost);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  jsonObject;
    }



        public static int getpay(int cost)
        {
            int pay=(cost*9)/100;
            if(pay%1000>500)
            {
                pay=pay+1000;
            }
            pay=pay/1000;
            pay=pay*1000;
            return pay;
        }

}
