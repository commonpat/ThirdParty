package com.utvgo.huya.beans;

/**
 * Created by dev on 2018/3/16.
 */

public class BeanQianDaiBuy {


    /**
     * result : {"state":4,"reason":"Wallet payment failure 调用BOSS接口异常"}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * state : 4
         * reason : Wallet payment failure 调用BOSS接口异常
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
