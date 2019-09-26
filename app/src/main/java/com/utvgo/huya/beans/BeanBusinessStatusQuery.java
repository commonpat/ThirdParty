package com.utvgo.huya.beans;

/**
 * Created by dev on 2018/3/15.
 */

public class BeanBusinessStatusQuery {

    /**
     * data : {"status":"310000"}
     * result : {"state":0,"reason":"OK"}
     */

    private DataBean data;
    private ResultBean result;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class DataBean {
        /**
         * status : 310000
         */

        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class ResultBean {
        /**
         * state : 0
         * reason : OK
         */

        private int state;
        private String reason;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}
