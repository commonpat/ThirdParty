package com.utvgo.huya.utils;

public class StringUtils {
    public static int intValueOfString(final String s)
    {
        int val = 0;
        try {
            val = Integer.parseInt(s);
        } catch (Exception e) {

        }
        return val;
    }
}
