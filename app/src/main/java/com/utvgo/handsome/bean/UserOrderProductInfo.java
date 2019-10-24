package com.utvgo.handsome.bean;

import java.io.Serializable;

/**
 * Created by lgh on 2018/8/13
 */

public class UserOrderProductInfo implements Serializable {

    /**
     * "authId":8,
     * "keyNo":"8731204389466779",
     * "cmboId":"16427001001",
     * "status":"0",
     * "createTime":"2019-07-02 10:15:52",
     * "validDate":"2019-07-02 10:15:52",
     * "expireDate":"2099-12-31 23:59:59",
     * "productName":"QQ音乐-包月",
     * "price":22.0
     */
    int authId;
    String keyNo;
    String cmboId;
    String status;
    String createTime;
    String validDate;
    String expireDate;
    String productName;
    float price;

    public int getAuthId() {
        return authId;
    }

    public void setAuthId(int authId) {
        this.authId = authId;
    }

    public String getKeyNo() {
        return keyNo;
    }

    public void setKeyNo(String keyNo) {
        this.keyNo = keyNo;
    }

    public String getCmboId() {
        return cmboId;
    }

    public void setCmboId(String cmboId) {
        this.cmboId = cmboId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
