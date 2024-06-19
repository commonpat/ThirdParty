package com.utvgo.handsome.bean;

import java.io.Serializable;

public class BaseResponse implements Serializable {

    private String code;
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }
}


