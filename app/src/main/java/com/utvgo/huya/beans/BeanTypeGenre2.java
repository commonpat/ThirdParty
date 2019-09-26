package com.utvgo.huya.beans;

import java.util.List;

/**
 * Created by haha on 2017/8/8.
 */

public class BeanTypeGenre2 extends BaseResponse {

    /***
     *
     "code":"1",
     "data":Array[4],
     "message":"success"
     * */
    private List<dataBean> data;

    public List<dataBean> getData() {
        return data;
    }

    public void setData(List<dataBean> data) {
        this.data = data;
    }

    public static class dataBean{
        /**
         "isShowScore":0,
         "labelId":790,
         "gridType":"1",
         "name":"赛事",
         "channelId":34
         * */
        private int isShowScore;
        private int labelId;
        private String gridType;
        private String name;
        private int channelId;

        public int getIsShowScore() {
            return isShowScore;
        }

        public void setIsShowScore(int isShowScore) {
            this.isShowScore = isShowScore;
        }

        public int getLabelId() {
            return labelId;
        }

        public void setLabelId(int labelId) {
            this.labelId = labelId;
        }

        public String getGridType() {
            return gridType;
        }

        public void setGridType(String gridType) {
            this.gridType = gridType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getChannelId() {
            return channelId;
        }

        public void setChannelId(int channelId) {
            this.channelId = channelId;
        }
    }
}
