package com.example.hesi100.barber;

import android.content.SharedPreferences;
import android.widget.LinearLayout;

/**
 * Created by hesi100 on 5/6/2018.
 */

public class Setting_person {
    public static void setTheme(LinearLayout L) {
        SharedPreferences prefs = app.context.getSharedPreferences("belz", 0);
        String theme=prefs.getString("theme","no");
        if(theme.equals("no"))
        {
            L.setBackground(app.context.getResources().getDrawable(R.drawable.back0));
        }
        else
        {
            int res=app.context.getResources().getIdentifier(theme,"drawable",app.context.getPackageName());
            L.setBackground(app.context.getResources().getDrawable(res));
        }

    }

}
