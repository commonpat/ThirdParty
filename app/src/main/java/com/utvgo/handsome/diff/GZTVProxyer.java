package com.utvgo.handsome.diff;

import java.util.Properties;

public class GZTVProxyer extends Proxyer {

    public void startUp(){
        Properties properties = System.getProperties();
        properties.setProperty("proxySet", "true");
        properties.setProperty("proxyHost", "gw.httpproxy.local");
        properties.setProperty("proxyPost", "80");
    }

    public void shutDown()
    {
        Properties properties = System.getProperties();
        properties.setProperty("proxySet", "false");
        properties.setProperty("proxyHost", "");
        properties.setProperty("proxyPost", "");
    }
}
