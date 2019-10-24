package com.utvgo.handsome.diff;

public enum Platform {

    gcable,
    gztv,
    hncatv,
    gacs,
    gahb;

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

                default:
                    return "";
        }
    }
}
