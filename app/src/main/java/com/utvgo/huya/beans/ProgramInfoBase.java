package com.utvgo.huya.beans;

import java.io.Serializable;

public class ProgramInfoBase implements Serializable {
    private String supplierName;
    private int pkId;
    private int setNums;
    private int supplierId;
    private String imageBig;
    private String multiSetType;
    private String priority;
    private String imageMid;
    private String keyWord;
    private int maxSet;
    private String isFree;
    private int  doubanScore;
    private long createTime;
    private String thirdPlayUrl;
    private String name;
    private String imageSmall;
    private int playSourceId;
    private int vipPriority;
    private String playSourceName;
    private int channelId;

    private boolean isFavor = false;

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public int getPkId() {
        return pkId;
    }

    public void setPkId(int pkId) {
        this.pkId = pkId;
    }

    public int getSetNums() {
        return setNums;
    }

    public void setSetNums(int setNums) {
        this.setNums = setNums;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getImageBig() {
        return imageBig;
    }

    public void setImageBig(String imageBig) {
        this.imageBig = imageBig;
    }

    public String getMultiSetType() {
        return multiSetType;
    }

    public void setMultiSetType(String multiSetType) {
        this.multiSetType = multiSetType;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getImageMid() {
        return imageMid;
    }

    public void setImageMid(String imageMid) {
        this.imageMid = imageMid;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public int getMaxSet() {
        return maxSet;
    }

    public void setMaxSet(int maxSet) {
        this.maxSet = maxSet;
    }

    public String getIsFree() {
        return isFree;
    }

    public void setIsFree(String isFree) {
        this.isFree = isFree;
    }

    public int getDoubanScore() {
        return doubanScore;
    }

    public void setDoubanScore(int doubanScore) {
        this.doubanScore = doubanScore;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getThirdPlayUrl() {
        return thirdPlayUrl;
    }

    public void setThirdPlayUrl(String thirdPlayUrl) {
        this.thirdPlayUrl = thirdPlayUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageSmall() {
        return imageSmall;
    }

    public void setImageSmall(String imageSmall) {
        this.imageSmall = imageSmall;
    }

    public int getPlaySourceId() {
        return playSourceId;
    }

    public void setPlaySourceId(int playSourceId) {
        this.playSourceId = playSourceId;
    }

    public int getVipPriority() {
        return vipPriority;
    }

    public void setVipPriority(int vipPriority) {
        this.vipPriority = vipPriority;
    }

    public String getPlaySourceName() {
        return playSourceName;
    }

    public void setPlaySourceName(String playSourceName) {
        this.playSourceName = playSourceName;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public boolean isFavor() {
        return isFavor;
    }

    public void setFavor(boolean favor) {
        isFavor = favor;
    }

    public boolean isFree()
    {
        return "-1".equalsIgnoreCase(this.isFree);
    }
}
