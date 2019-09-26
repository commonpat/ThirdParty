package com.utvgo.huya.utils;

import android.content.Context;
import android.text.TextUtils;


/**
 * Created by godfather on 2016/3/21.
 * app 全局变量配置，例如智能卡号
 * <p/>
 * 传递的参数 context 请使用getApplicationContext() 避免出现act的内存泄露
 */
public class Appconfig {
    private static final String APPNAME = "虎牙TV";
    private static final String KEYNO = "KEYNO";
    private static final String COOKIE = "COOKIE";
    private static final String EPG = "EPG";
    private static final String STBID = "STBID";
    private static final String TRACKID = "TRACKID";
    private static final String RegionCode = "RegionCode";
    private static String keyNo = "";
    private static String cookie;
    private static String epg_url = "";//从机顶盒获取的
    private static String serviceGroupId;
    private static String stbid;//机顶盒系列号

    public static String getRegionCode(Context context) {
        String code = Tools.getStringPreference(context, RegionCode);
//        code = "1";  //TODO 测试  记得去掉
        if(ApkUtil.isDebug(context))
            return "1";
        if(code.trim().equals("0"))
            return "101";
        return code;
    }

    public static String getKeyNo(Context context) {
        if (TextUtils.isEmpty(keyNo))
            keyNo = Tools.getStringPreference(context, KEYNO);
        if (ApkUtil.isDebug(context)) {
            //        keyNo = "9950000002384049"; //// TODO: 17/3/6  记得去掉  重庆测试卡号  以前陈蓉卡号  未订购 欠费了
//        keyNo = "9950000000796045"; //// TODO: 17/3/6  记得去掉  重庆已订购卡号  应该是婷婷卡号
//        keyNo = "9950011700025341"; //// TODO: 17/3/6  记得去掉  重庆已订购卡号  随便用户的
//        keyNo = "9950000002376597"; //// TODO: 17/3/6  记得去掉  重庆测试卡号 王黎卡号

//        keyNo = "8754002160066228"; //// TODO: 17/3/6  记得去掉  汕头不能订购测试卡号
//        keyNo = "8002004093892878"; //// TODO: 17/3/6  记得去掉  广东测试卡号  已订购

//        keyNo="3072392170"; //广东 实验室账号

//        keyNo="8754003115461324"; //广东 有儿童锁   未订购
//        keyNo="8757003890891387";//不能修改订购状态
//        keyNo="8002004093892878";
//        keyNo="8002003224274725";
//        keyNo="8002003224274725";
//        keyNo="8002003224274725";
//        keyNo="8002003224274725";
//        keyNo="8757004395552789";
//        keyNo="8757003890150552";
//        keyNo="8757003890150552";
//        keyNo="8002002601757534";  //已订购

//        keyNo="8420910210378209"; //湖北国安卡号
//        keyNo="10377056865"; //湖南国安卡号
//        keyNo="8002003646694252";//已订购 不能改
//        keyNo="3182149371";
//            keyNo="3072535006";
           keyNo="8002004093892878";
        }
        return keyNo;
    }

    public static String getStbid(Context context) {
        if (TextUtils.isEmpty(stbid))
            stbid = Tools.getStringPreference(context, STBID);
        return stbid;
    }

    public static void setStbid(Context context, String stbid) {
        Appconfig.stbid = stbid;
        if (!TextUtils.isEmpty(stbid))
            Tools.setStringPreference(context, STBID, stbid);
    }

    public static void setKeyNo(Context context, String keyNo) {
        Appconfig.keyNo = keyNo;
        if (!TextUtils.isEmpty(keyNo))
            Tools.setStringPreference(context, KEYNO, keyNo);
    }

    public static void setRegionCode(Context context, String regionCode) {
        if (!TextUtils.isEmpty(regionCode))
            Tools.setStringPreference(context, RegionCode, regionCode);
    }

    public static String getCookie(Context context) {
        if (TextUtils.isEmpty(cookie))
            cookie = Tools.getStringPreference(context, COOKIE);
        return cookie;
    }

    public static void setCookie(Context context, String cookie) {
        Appconfig.cookie = cookie;
        if (!TextUtils.isEmpty(cookie))
            Tools.setStringPreference(context, COOKIE, cookie);
    }

    public static String getServiceGroupId() {
        return serviceGroupId;
    }

    public static void setServiceGroupId(String serviceGroupId) {
        Appconfig.serviceGroupId = serviceGroupId;
    }

    public static String getEpg_url(Context context) {
        if (TextUtils.isEmpty(epg_url))
            epg_url = Tools.getStringPreference(context, EPG);
        return epg_url;
    }

    public static void setEpg_url(Context context, String epg_url) {
        Appconfig.epg_url = epg_url;
        if (!TextUtils.isEmpty(epg_url))
            Tools.setStringPreference(context, EPG, epg_url);
    }
}
