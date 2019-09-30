package com.utvgo.huya.diff;

public enum Platform {

    gcable,
    guizhou;

    public String toAreaString()
    {
        switch (this)
        {
            case gcable:
                return "广东";

            case guizhou:
                return "贵州";

                default:
                    return "";
        }
    }
}
