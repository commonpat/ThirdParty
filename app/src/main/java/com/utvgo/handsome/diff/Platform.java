package com.utvgo.handsome.diff;

public enum Platform {

    gcable,
    gztv,
    hncatv,
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
    public static Platform stringToPlatform(String s){
        switch (s){
            case "gcable":
                return gcable;
            case "gztv":
                return gztv;
            case "topway":
                return topway;
            default:
                return gcable;
        }

    }
}
