package com.utvgo.handsome.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

public class AppUtils {
    public static String getVersion(Context context) {
        String version = "0.0";
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            version = info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return version;
    }
    public static Boolean isApkExist(Context context,String pkgName){
        PackageManager packageManager;
        try{
             packageManager = context.getPackageManager();
            List<PackageInfo> list = packageManager.getInstalledPackages(0);
            for (PackageInfo packageInfo:list){
                if (pkgName.equalsIgnoreCase(packageInfo.packageName)){
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
