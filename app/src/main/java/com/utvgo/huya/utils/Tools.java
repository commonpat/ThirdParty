package com.utvgo.huya.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * Created by YS on 2016/2/26.
 * 类说明：工具类
 */

public class Tools {


    /**
     * 获取app所在的cache缓存目录
     *
     * @param context
     * @return
     */
    public static String getAppCacheDir(Context context) {
        if (!isMounted())
            return null;
        StringBuilder sb = new StringBuilder();
        File file = context.getExternalCacheDir();
        if (file != null) {
            sb.append(file.getAbsolutePath()).append(File.separator);
        } else {
            sb.append(Environment.getExternalStorageDirectory().getPath()).append("/Android/data/").append(context.getPackageName())
                    .append("/cache/").append(File.separator).toString();
        }
        return sb.toString();
    }

    /**
     * @param context 获取crash目录
     * @return
     */
    public static String getAppCrashDir(Context context) {
        if (!isMounted())
            return null;
        StringBuilder sb = new StringBuilder();
        File file = context.getExternalFilesDir("crash");
        if (file != null) {
            sb.append(file.getAbsolutePath()).append(File.separator);
        } else {
            sb.append(Environment.getExternalStorageDirectory().getPath()).append("/Android/data/").append(context.getPackageName())
                    .append("/crash/").append(File.separator).toString();
        }
        return sb.toString();
    }


    private static boolean isMounted() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }

    /**
     * 是否空字符串
     *
     * @param input
     * @return
     */
    public static boolean isNullOrEmpty(String input) {
        return input == null || input.length() == 0 || input.equals("[]") || input.equals("null");
    }

    public static String formatTime(long l) {
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");//初始化Formatter的转换格式。
        return formatter.format(l);
    }

    // a integer to xx:xx:xx
    public static String secToTime(long time) {
        String timeStr = null;
        long hour = 0;
        long minute = 0;
        long second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }
    public static String unitFormat(long i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + i;
        else
            retStr = "" + i;
        return retStr;
    }

    /**
     * 获取手机屏幕高度
     *
     * @param activity
     * @return
     */
    public static int getDisplayHeight(Activity activity) {
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

    /**
     * 获取手机屏幕宽度
     *
     * @param activity
     * @return
     */
    public static int getDisplayWidth(Activity activity) {
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    // SharedPreferences-------------------------------------------------------start
    public static String getStringPreference(Context context, String tag) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(tag, "");
    }

    public static void setStringPreference(Context context, String tag, String value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = sp.edit();
        editor.putString(tag, value);
        editor.commit();
    }

    public static int getIntegerPreference(Context context, String tag) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(tag, 0);
    }

    public static int getIntegerPreference(Context context, String tag, int defaultInt) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(tag, defaultInt);
    }


    public static void setIntegerPreference(Context context, String tag, int value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = sp.edit();
        editor.putInt(tag, value);
        editor.commit();
    }

    public static Long getLongPreference(Context context, String tag) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getLong(tag, 0);
    }

    public static void setLongPreference(Context context, String tag, long value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = sp.edit();
        editor.putLong(tag, value);
        editor.commit();
    }

    public static boolean getBooleanPreference(Context context, String tag) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(tag, false);
    }

    public static void setBooleanPreference(Context context, String tag, boolean value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = sp.edit();
        editor.putBoolean(tag, value);
        editor.commit();
    }

    public static void setFloatPreference(Context context, String tag, float value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = sp.edit();
        editor.putFloat(tag, value);
        editor.commit();
    }

    public static float getFloatPreference(Context context, String tag) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getFloat(tag, 0.0f);
    }

    // SharedPreferences-------------------------------------------------------end


    /**
     * 遍历 设置布局里面的所有字控件Enable的属性
     */
    public static void setLayoutAllViewEnable(RelativeLayout layoutView, boolean enable) {
        for (int i = 0; i < layoutView.getChildCount(); i++) {
            View v = layoutView.getChildAt(i);
            setEnable(enable, v);

        }
    }

    /**
     * 遍历 设置布局里面的所有字控件Enable的属性
     */
    public static void setLayoutAllViewEnable(FrameLayout layoutView, boolean enable) {
        for (int i = 0; i < layoutView.getChildCount(); i++) {
            View v = layoutView.getChildAt(i);
            setEnable(enable, v);

        }
    }

    /**
     * 遍历 设置布局里面的所有字控件Enable的属性
     */
    public static void setLayoutAllViewEnable(RadioGroup layoutView, boolean enable) {
        for (int i = 0; i < layoutView.getChildCount(); i++) {
            View v = layoutView.getChildAt(i);
            setEnable(enable, v);

        }
    }

    /**
     * 遍历 设置布局里面的所有字控件Enable的属性
     */
    public static void setLayoutAllViewEnable(LinearLayout layoutView, boolean enable) {
        for (int i = 0; i < layoutView.getChildCount(); i++) {
            View v = layoutView.getChildAt(i);
            setEnable(enable, v);

        }
    }

    private static void setEnable(boolean enable, View v) {
        if (v instanceof LinearLayout)
            setLayoutAllViewEnable((LinearLayout) v, enable);
        if (v instanceof RelativeLayout)
            setLayoutAllViewEnable((RelativeLayout) v, enable);
        if (v instanceof FrameLayout)
            setLayoutAllViewEnable((FrameLayout) v, enable);
        if (v instanceof RadioGroup)
            setLayoutAllViewEnable((RadioGroup) v, enable);
        if (v instanceof Button)
            v.setEnabled(enable);
        if (v instanceof EditText)
            v.setEnabled(enable);
        if (v instanceof ImageView)
            v.setEnabled(enable);
        if (v instanceof TextView)
            v.setEnabled(enable);

        if (v instanceof View)
            v.setEnabled(enable);
        if (v instanceof CheckBox)
            v.setEnabled(enable);
        if (v instanceof RadioButton)
            v.setEnabled(enable);

        if (v instanceof ImageButton)
            v.setEnabled(enable);
    }

    public static float getPrice(String vipprice, String price) {
        if (Tools.isNullOrEmpty(vipprice) && Tools.isNullOrEmpty(price))
            return 0;
        float t_price = Float.valueOf(Tools.isNullOrEmpty(price) ? "0" : price);
        float t_vipprice = Float.valueOf(Tools.isNullOrEmpty(price) ? "0" : vipprice);
        return t_vipprice > 0 ? t_vipprice : t_price;
    }
}
