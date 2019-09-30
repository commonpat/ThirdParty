package com.utvgo.huya.beans;

/**
 * Created by lgh on 2018/8/13
 */

public class BeanBasic {


    /**
     * message : success
     * tipsInfo : 您已成功登记手机号码，工作人员将会在活动结束后7个工作日内电话通知中奖用户。
     * code : 1
     */
    private  Data data;
    private String message;
    private String code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public static class Data{
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
