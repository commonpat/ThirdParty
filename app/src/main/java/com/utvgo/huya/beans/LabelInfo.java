package com.utvgo.huya.beans;

import java.io.Serializable;

public class LabelInfo implements Serializable {
    private int labelId;
    private int channelId;
    private int isShowScore;
    private String name = "";
    private String gridType;

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getIsShowScore() {
        return isShowScore;
    }

    public void setIsShowScore(int isShowScore) {
        this.isShowScore = isShowScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGridType() {
        return gridType;
    }

    public void setGridType(String gridType) {
        this.gridType = gridType;
    }
}
