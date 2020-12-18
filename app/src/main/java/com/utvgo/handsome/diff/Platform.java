package com.utvgo.handsome.diff;

public enum Platform {

    gcable,
    gztv,
    hncatv,
    gacs,
    gahb,
    topway,
    gzbn;

    public String toAreaString()
    {
        switch (this)
        {
            case gcable:
                return "广东";
            case gztv:
                return "广州";
            case hncatv:
                return "湖南";
            case gzbn:
                return "贵州";
            case topway:
                return "深圳天威";
             default:
                    return "";
        }
    }
}
