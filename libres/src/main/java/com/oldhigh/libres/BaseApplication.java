package com.oldhigh.libres;

import android.app.Application;
import android.content.Context;

/**
 * Created by oldhigh on 2018/3/26.
 */

public class BaseApplication extends Application {


    public static Context sContext ;
    @Override
    public void onCreate() {
        super.onCreate();

        sContext = this.getApplicationContext();

    }
}
