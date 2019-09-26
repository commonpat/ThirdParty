package com.utvgo.huya.beans;

/**
 * Created by Administrator on 2017/11/15.
 */

public class AuthData{

    /**
     * c_orderOriginalPrice : 2
     * c_productName :
     * c_productType : OTT点播
     * c_orderNO : 12017111515282059660
     * payPageUrl : http%3A%2F%2F10.254.15.10%3A8080%2FEMSP_AMS_TERMINAL_INTERFACE%2Fpay%2FjumpToPay.jsp
     * c_orderType : 产品包
     * queryPayResultUrl : http%3A%2F%2F10.254.15.10%3A8080%2FEMSP_AMS_TERMINAL_INTERFACE%2Fservlet%2FmediaAuthServlet%3Ftype%3DqueryPayResult
     * c_orderPrice : 2
     * authResult : -1
     * c_productID : OC1021210000171115001NNNNNNNNNNN
     */

    private String c_orderOriginalPrice;
    private String c_productName;
    private String c_productType;
    private String c_orderNO;
    private String payPageUrl;
    private String c_orderType;
    private String queryPayResultUrl;
    private String c_orderPrice;
    private int authResult;
    private String c_productID;
    /**
     * FAssetsID : utvgo-EVMTV-EX100021
     * FAssetsList : 1
     * vvideoId : 112193
     * LinkedId : 112192
     * videoOrder : 1
     * strategyId : 0
     * code : 1
     * authResult : 1
     * resKind : 101016
     * playUrl : http://10.254.17.81/otv/source/guangshi/6/7E/A5/00000012458/h0024tfb2pv_blue_ray.mp4
     * categoryName : QQ音乐
     * flag : 1
     * FEndTime : 0
     * err : 播放
     * FAssetsLength : 0
     * FAssetsTotalList : 1
     * FAssetsName : 小孩
     */

    private String FAssetsID;
    private int FAssetsList;
    private int vvideoId;
    private int LinkedId;
    private int videoOrder;
    private int strategyId;
    private int code;
    private String resKind;
    private String playUrl;
    private String categoryName;
    private String flag;
    private int FEndTime;
    private String err;
    private int FAssetsLength;
    private int FAssetsTotalList;
    private String FAssetsName;

    public String getC_orderOriginalPrice() {
        return c_orderOriginalPrice;
    }

    public void setC_orderOriginalPrice(String c_orderOriginalPrice) {
        this.c_orderOriginalPrice = c_orderOriginalPrice;
    }

    public String getC_productName() {
        return c_productName;
    }

    public void setC_productName(String c_productName) {
        this.c_productName = c_productName;
    }

    public String getC_productType() {
        return c_productType;
    }

    public void setC_productType(String c_productType) {
        this.c_productType = c_productType;
    }

    public String getC_orderNO() {
        return c_orderNO;
    }

    public void setC_orderNO(String c_orderNO) {
        this.c_orderNO = c_orderNO;
    }

    public String getPayPageUrl() {
        return payPageUrl;
    }

    public void setPayPageUrl(String payPageUrl) {
        this.payPageUrl = payPageUrl;
    }

    public String getC_orderType() {
        return c_orderType;
    }

    public void setC_orderType(String c_orderType) {
        this.c_orderType = c_orderType;
    }

    public String getQueryPayResultUrl() {
        return queryPayResultUrl;
    }

    public void setQueryPayResultUrl(String queryPayResultUrl) {
        this.queryPayResultUrl = queryPayResultUrl;
    }

    public String getC_orderPrice() {
        return c_orderPrice;
    }

    public void setC_orderPrice(String c_orderPrice) {
        this.c_orderPrice = c_orderPrice;
    }

    public int getAuthResult() {
        return authResult;
    }

    public void setAuthResult(int authResult) {
        this.authResult = authResult;
    }

    public String getC_productID() {
        return c_productID;
    }

    public void setC_productID(String c_productID) {
        this.c_productID = c_productID;
    }

    public String getFAssetsID() {
        return FAssetsID;
    }

    public void setFAssetsID(String FAssetsID) {
        this.FAssetsID = FAssetsID;
    }

    public int getFAssetsList() {
        return FAssetsList;
    }

    public void setFAssetsList(int FAssetsList) {
        this.FAssetsList = FAssetsList;
    }

    public int getVvideoId() {
        return vvideoId;
    }

    public void setVvideoId(int vvideoId) {
        this.vvideoId = vvideoId;
    }

    public int getLinkedId() {
        return LinkedId;
    }

    public void setLinkedId(int LinkedId) {
        this.LinkedId = LinkedId;
    }

    public int getVideoOrder() {
        return videoOrder;
    }

    public void setVideoOrder(int videoOrder) {
        this.videoOrder = videoOrder;
    }

    public int getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(int strategyId) {
        this.strategyId = strategyId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResKind() {
        return resKind;
    }

    public void setResKind(String resKind) {
        this.resKind = resKind;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getFEndTime() {
        return FEndTime;
    }

    public void setFEndTime(int FEndTime) {
        this.FEndTime = FEndTime;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public int getFAssetsLength() {
        return FAssetsLength;
    }

    public void setFAssetsLength(int FAssetsLength) {
        this.FAssetsLength = FAssetsLength;
    }

    public int getFAssetsTotalList() {
        return FAssetsTotalList;
    }

    public void setFAssetsTotalList(int FAssetsTotalList) {
        this.FAssetsTotalList = FAssetsTotalList;
    }

    public String getFAssetsName() {
        return FAssetsName;
    }

    public void setFAssetsName(String FAssetsName) {
        this.FAssetsName = FAssetsName;
    }
}
