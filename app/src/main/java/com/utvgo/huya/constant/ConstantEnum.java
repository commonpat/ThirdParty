package com.utvgo.huya.constant;

/**
 * Created by Administrator on 2016/3/17.
 */
public class ConstantEnum {

    public static final int TAGReWaiLiuqAlbumMid = 3000;

    public static final int TAGRecommendExit = 3007;
    public static final int ReqActivity = 3008;


    public enum MediaType {
        audio, video;

        public static MediaType fromValue(final int value)
        {
            switch (value)
            {
                case 0:
                    return audio;

                    default:
                        return video;
            }
        }
    }

    public enum OpType
    {
        web, program, album, topic, topicPage, topicCollection, albumList, more, back, activity;

        public static OpType fromTypeString(final String typeString)
        {
            /*
            ****0	超链接
                1	节目
                2	专辑
                3	专题
                4	页面专题
                5	专题收录类型
                6	专辑收录类型
                7	更多
                8	返回
             */

            OpType opType = web;
            if("1".equalsIgnoreCase(typeString))
            {
                opType = program;
            }
            else if("2".equalsIgnoreCase(typeString))
            {
                opType = album;
            }
            else if("3".equalsIgnoreCase(typeString))
            {
                opType = topic;
            }
            else if("4".equalsIgnoreCase(typeString))
            {
                opType = topicPage;
            }
            else if("5".equalsIgnoreCase(typeString))
            {
                opType = topicCollection;
            }
            else if("6".equalsIgnoreCase(typeString))
            {
                opType = albumList;
            }
            else if("7".equalsIgnoreCase(typeString))
            {
                opType = more;
            }
            else if("8".equalsIgnoreCase(typeString))
            {
                opType = back;
            }
            else if("9".equalsIgnoreCase(typeString))
            {
                opType = activity;
            }
            return opType;
        }
    }
}
