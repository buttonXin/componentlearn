package com.oldhigh.libres.util;

import android.util.Log;

import java.util.Arrays;

/**
 * Created by oldhigh on 2018/3/27.
 */

public class L {


    public static void d(Object... objects){
        Log.d(Thread.currentThread().getName(), Arrays.toString(objects));
    }

    public static void e(Object... objects){
        Log.e(Thread.currentThread().getName(), Arrays.toString(objects));
    }
}
