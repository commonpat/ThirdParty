package com.utvgo.huya.utils;

import java.util.Properties;


public class ProxyUtil {
    public static void up() {
        Properties properties = System.getProperties();
        properties.setProperty("proxySet", "true");
        properties.setProperty("proxyHost", "gw.httpproxy.local");
        properties.setProperty("proxyPost", "80");
    }

    public static void down() {
        Properties properties = System.getProperties();
        properties.setProperty("proxySet", "false");
        properties.setProperty("proxyHost", "");
        properties.setProperty("proxyPost", "");
    }
}
