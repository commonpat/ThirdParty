package com.utvgo.huya.beans;

/**
 * Created by oo on 2017/12/5.
 */

public class BeanStatistics extends BaseResponse {


    /**
     * message : success
     * data : {"id":"1512461586162"}
     * code : 1
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }


    public static class DataBean {
        /**
         * id : 1512461586162
         */

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
