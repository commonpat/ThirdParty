package com.utvgo.handsome.utils;

import android.content.Context;
import android.util.Log;

import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.diff.GDTVBox;
import com.utvgo.huya.interfaces.PlatformInterface;
import com.utvgo.huya.utils.Tools;


import java.lang.reflect.Method;

public class PlatfromUtils implements PlatformInterface {
    private Context context;
    public PlatfromUtils(Context context) {
        this.context = context;
    }

    @Override
    public boolean isGuangDong() {
        return false;
    }

    @Override
    public boolean isChongQing() {
        return false;
    }


    @Override
    public boolean isP60() {
        try {
           String platform = Tools.getStringPreference(context,"platform");
            if(platform.contains("SC808")){
                return true;
            }else  if(platform.contains("Hi3796MV100")){
                return false;
            }if(platform.contains("HWIPTV")){
                return false;
            }else {
                if(DiffConfig.CurrentTVBox instanceof GDTVBox){
                    return false;
                }
                Class<?> clazz = Class.forName("android.os.SystemProperties");
                Method get = clazz.getMethod("get", String.class);
                String model = (String) get.invoke(null, "ro.product.model");//机顶盒序列号ro.product.model
                String name = (String) get.invoke(null, "ro.product.name");//机顶盒序列号ro.product.name
                String type = (String) get.invoke(null, "ro.product.type");//机顶盒序列号ro.product.type
                Log.d("TAG", "initDeviceInfo:"
                        + "model:" + model + "\n"
                        + "name:" + name + "\n"
                        + "type:" + type + "\n");
                if (model.contains("SC808")) {
                    Tools.setStringPreference(context, "platform", "SC808");
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isP30() {
        /**
         * iptv：ro.product.type=HWIPTV
         * p30:ro.product.name =Hi3796MV100
         * P60:ro.product.model=SC808
         * */
        try {
            String platform = Tools.getStringPreference(context,"platform");
            if(platform.contains("SC808")){
                return false;
            }else  if(platform.contains("Hi3796MV100")){
                return true;
            }if(platform.contains("HWIPTV")){
                return false;
            }else {
                if(DiffConfig.CurrentTVBox instanceof GDTVBox){
                    return false;
                }
                Class<?> clazz = Class.forName("android.os.SystemProperties");
                Method get = clazz.getMethod("get", String.class);
                String model = (String) get.invoke(null, "ro.product.model");//机顶盒序列号ro.product.model
                String name = (String) get.invoke(null, "ro.product.name");//机顶盒序列号ro.product.name
                String type = (String) get.invoke(null, "ro.product.type");//机顶盒序列号ro.product.type
                Log.d("TAG", "initDeviceInfo:"
                        + "model:" + model + "\n"
                        + "name:" + name + "\n"
                        + "type:" + type + "\n");
                if (name.contains("Hi3796MV100")) {
                    Tools.setStringPreference(context, "platform", "Hi3796MV100");
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isIptv() {

        try {
            String platform = Tools.getStringPreference(context,"platform");
            if(platform.contains("SC808")){
                return false;
            }else  if(platform.contains("Hi3796MV100")){
                return false;
            }if(platform.contains("HWIPTV")){
                return true;
            }else {
                if(DiffConfig.CurrentTVBox instanceof GDTVBox){
                    return false;
                }
                Class<?> clazz = Class.forName("android.os.SystemProperties");
                Method get = clazz.getMethod("get", String.class);
                String model = (String) get.invoke(null, "ro.product.model");//机顶盒序列号ro.product.model
                String name = (String) get.invoke(null, "ro.product.name");//机顶盒序列号ro.product.name
                String type = (String) get.invoke(null, "ro.product.type");//机顶盒序列号ro.product.type
                Log.d("TAG", "initDeviceInfo:"
                        + "model:" + model + "\n"
                        + "name:" + name + "\n"
                        + "type:" + type + "\n");
                if (type.contains("HWIPTV")) {
                    Tools.setStringPreference(context, "platform", "HWIPTV");

                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isHuBei() {
        return false;
    }

    @Override
    public boolean isHuNan() {
        return false;
    }

    @Override
    public boolean isGuiZhou() {
        return true;
    }

    @Override
    public boolean isGuoAn() {
        return false;
    }

    @Override
    public boolean isGuangDianJingLing() {

        return false;
    }

    @Override
    public boolean isFuMuLe2() {

        return false;
    }

    @Override
    public boolean isFML1() {

        return false;
    }

    @Override
    public boolean isZJSM() {
        return false;
    }
}
