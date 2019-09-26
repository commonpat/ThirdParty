package com.utvgo.huya.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;

public class ApkUtil {
    public static boolean isDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }
}
