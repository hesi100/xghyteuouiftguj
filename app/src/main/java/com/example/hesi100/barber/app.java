package com.example.hesi100.barber;

import android.app.Application;
import android.content.Context;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by hesi100 on 11/26/2017.
 */

public class app extends Application{

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/b_aseman.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        context = getApplicationContext();
    }


}
