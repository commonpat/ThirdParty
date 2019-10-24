package com.utvgo.handsome.utils;

import android.content.Context;
import android.widget.Toast;

import com.utvgo.huya.BuildConfig;

public class XLog {

    private static final boolean DEBUG = true;


    public static void v(String tag, String msg) {
        if (DEBUG) {
            android.util.Log.v(tag, msg);
        }
    }

    /**
     * @param msg 默认android.util.Log.d("TAG", msg);
     */
    public static void d(String msg) {
        if (DEBUG) {
            android.util.Log.d("TAG", msg);
        }
    }


    public static void v(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            android.util.Log.v(tag, msg, tr);
        }
    }

    public static void d(String tag, String msg) {
//        if (DEBUG) {
        android.util.Log.d(tag, msg);
//        }
    }

    public static void d(String tag, String msg, Throwable tr) {

        if (DEBUG) {
            android.util.Log.d(tag, msg, tr);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            android.util.Log.i(tag, msg);
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            android.util.Log.i(tag, msg, tr);
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUG) {
            android.util.Log.w(tag, msg);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            android.util.Log.w(tag, msg, tr);
        }
    }

    public static void w(String tag, Throwable tr) {
        if (DEBUG) {
            android.util.Log.w(tag, tr);
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUG) {
            android.util.Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            android.util.Log.e(tag, msg, tr);
        }
    }

    public static void show(final Context context, final String msg)
    {
        if(BuildConfig.DEBUG)
        {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
