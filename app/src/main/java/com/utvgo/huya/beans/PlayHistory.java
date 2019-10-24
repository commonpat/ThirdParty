package com.utvgo.huya.beans;

import java.io.Serializable;

public class PlayHistory implements Serializable {
    private long modifiedTime;

    private int totalTime;

    private String multiSetType;

    private int videoId;

    private String keyNo;

    private String programName;

    private String videoName;

    private long createdTime;

    private int id;

    private String state;

    private int playPoint;

    private int channelId;

    private int programId;

    public void setModifiedTime(long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public long getModifiedTime() {
        return this.modifiedTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public int getTotalTime() {
        return this.totalTime;
    }

    public void setMultiSetType(String multiSetType) {
        this.multiSetType = multiSetType;
    }

    public String getMultiSetType() {
        return this.multiSetType;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public int getVideoId() {
        return this.videoId;
    }

    public void setKeyNo(String keyNo) {
        this.keyNo = keyNo;
    }

    public String getKeyNo() {
        return this.keyNo;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramName() {
        return this.programName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoName() {
        return this.videoName;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getCreatedTime() {
        return this.createdTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    public void setPlayPoint(int playPoint) {
        this.playPoint = playPoint;
    }

    public int getPlayPoint() {
        return this.playPoint;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getChannelId() {
        return this.channelId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public int getProgramId() {
        return this.programId;
    }
}
