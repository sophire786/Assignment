package com.assignment.utility;

import android.util.Log;

/**
 * Created by Neelam Saxena on 6/9/18.
 */
public class Debug {

    public static void i(String tag, String message) {
        if (GlobalConfig.ENABLE_DEBUG) Log.i(tag, message);
    }

    public static void i(String message) {
        Debug.i(GlobalConfig.TAG, message);
    }

    public static void e(String tag, String message) {
        if (GlobalConfig.ENABLE_DEBUG) Log.e(tag, message);
    }

    public static void d(String tag, String message) {
        if (GlobalConfig.ENABLE_DEBUG) Log.d(tag, message);
    }
    public static void e(String message) {
        Debug.e(GlobalConfig.TAG, message);
    }

    public static void e(String tag, String message, Exception e) {
        if (GlobalConfig.ENABLE_DEBUG) {
            Log.d(tag, message);

            e.printStackTrace();
        }
    }
}
