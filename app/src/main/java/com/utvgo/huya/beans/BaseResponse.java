package com.utvgo.huya.beans;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {

    private String code;
    private String message;

    private  T data;

    //optional
    private String imageProfix;

    private int totalPage;
    private int count;
    private int currentPage;

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    //public
    public boolean isOk()
    {
        return "1".equalsIgnoreCase(getCode());
    }

    //optional
    public String getImageProfix() {
        return imageProfix;
    }
    public void setImageProfix(String imageProfix) {
        this.imageProfix = imageProfix;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return this.count;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }
}
