package com.example.hesi100.barber;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hesi100 on 10/10/2018.
 */

public class WorkTime {
    boolean saturday_isclose;
    boolean sunday_isclose;
    boolean monday_isclose;
    boolean tuesday_isclose;
    boolean wednesday_isclose;
    boolean thursday_isclose;
    boolean friday_isclose;
    String saturday_shift1_start;
    String saturday_shift1_end;
    String saturday_shift2_start;
    String saturday_shift2_end;
    String sunday_shift1_start;
    String sunday_shift1_end;
    String sunday_shift2_start;
    String sunday_shift2_end;
    String monday_shift1_start;
    String monday_shift1_end;
    String monday_shift2_start;
    String monday_shift2_end;
    String tuesday_shift1_start;
    String tuesday_shift1_end;
    String tuesday_shift2_start;
    String tuesday_shift2_end;
    String wednesday_shift1_start;
    String wednesday_shift1_end;
    String wednesday_shift2_start;
    String wednesday_shift2_end;
    String thursday_shift1_start;
    String thursday_shift1_end;
    String thursday_shift2_start;
    String thursday_shift2_end;
    String friday_shift1_start;
    String friday_shift2_start;
    String friday_shift1_end;
    String friday_shift2_end;
    public void getFromjson(JSONObject res) throws JSONException {
        saturday_isclose=res.getBoolean("saturday_isclose");
        sunday_isclose=res.getBoolean("sunday_isclose");
        monday_isclose=res.getBoolean("monday_isclose");
        wednesday_isclose=res.getBoolean("wednesday_isclose");
        thursday_isclose=res.getBoolean("thursday_isclose");
        friday_isclose=res.getBoolean("friday_isclose");
        saturday_shift1_start=res.getString("saturday_shift1_start");
        saturday_shift1_end=res.getString("saturday_shift1_end");
        saturday_shift2_start=res.getString("saturday_shift2_start");
        saturday_shift2_end=res.getString("saturday_shift2_end");
        //////////
        sunday_shift1_start=res.getString("sunday_shift1_start");
        sunday_shift1_end=res.getString("sunday_shift1_end");
        sunday_shift2_start=res.getString("sunday_shift2_start");
        sunday_shift2_end=res.getString("sunday_shift1_end");
        /////////
        monday_shift1_start=res.getString("monday_shift1_start");
        monday_shift1_end=res.getString("monday_shift1_end");
        monday_shift2_start=res.getString("monday_shift2_start");
        monday_shift2_end=res.getString("monday_shift2_end");
        ////////////
        tuesday_shift1_start=res.getString("tuesday_shift1_start");
        tuesday_shift1_end=res.getString("tuesday_shift1_end");
        tuesday_shift2_start=res.getString("tuesday_shift2_start");
        tuesday_shift2_end=res.getString("tuesday_shift2_end");
        ////////////////
        wednesday_shift1_start=res.getString("wednesday_shift1_start");
        wednesday_shift1_end=res.getString("wednesday_shift1_end");
        wednesday_shift2_start=res.getString("wednesday_shift2_start");
        wednesday_shift2_end=res.getString("wednesday_shift2_end");
        ////////////////
        thursday_shift1_start=res.getString("thursday_shift1_start");
        thursday_shift1_end=res.getString("thursday_shift1_end");
        thursday_shift2_start=res.getString("thursday_shift2_start");
        thursday_shift2_end=res.getString("thursday_shift2_end");
        ///////////
        friday_shift1_start=res.getString("thursday_shift1_start");
        friday_shift1_end=res.getString("thursday_shift1_end");
        friday_shift2_start=res.getString("thursday_shift2_start");
        friday_shift2_end=res.getString("thursday_shift2_end");
    }
}
