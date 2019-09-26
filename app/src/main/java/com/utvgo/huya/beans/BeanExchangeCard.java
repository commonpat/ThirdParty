package com.utvgo.huya.beans;

/**
 * Created by oo on 2018/4/11.
 */

public class BeanExchangeCard {


    /**
     * code : 1
     * invalidTime : 2018-04-18 23:59:59
     * message : 恭喜，兑换成功!，你可以免费体验QQ音乐至：2018-04-18 23:59:59
     */

    private String code;
    private String invalidTime;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
