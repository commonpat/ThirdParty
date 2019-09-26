package com.utvgo.huya.utils;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/4/1.
 */
public class StrTool {


    /***
     * 获取url 指定name的value
     *
     * @param url
     * @param name
     * @return
     */
    public static String getValueByName(String url, String name) {
        String result = "";
        int index = url.indexOf("?");
        String temp = url.substring(index + 1);
        String[] keyValue = temp.split("&");
        for (String str : keyValue) {
            if (str.contains(name)) {
                result = str.replace(name + "=", "");
                break;
            }
        }
        return result;
    }

    /**
     * 返回单个字符串，若匹配到多个的话就返回第一个，方法与getSubUtil一样
     *
     * @param soap
     * @param rgex
     * @return
     */
    public static String getSubUtilSimple(String soap, String rgex) {
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            return m.group(1);
        }
        return "";
    }

    /**
     * java代码替换指定url里的参数值
     *
     * @param url
     * @param key
     * @param value
     * @return
     */
    public static String replaceUrlValue(String url, String key, String value) {
        if (!TextUtils.isEmpty(url) && !TextUtils.isEmpty(value)) {
            int index = url.indexOf(key + "=");
            if (index != -1) {
                StringBuilder sb = new StringBuilder();
                sb.append(url.substring(0, index)).append(key + "=").append(value);
                int idx = url.indexOf("&", index);
                if (idx != -1) {
                    sb.append(url.substring(idx));
                }
                url = sb.toString();
            } else {
                if (url.contains("?")) {
                    url = url + "&";
                } else {
                    url = url + "?";
                }
                url = url + key + "=" + value;
            }
        }
        return url;
    }

    public static String getYYMMDD(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String get8random() {
        return (89999999 * Math.random() + 10000000) + "";
    }

    public static String getURLEncoder(String resultStr) {
        try {
            resultStr = URLEncoder.encode(resultStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return resultStr;
    }


}
