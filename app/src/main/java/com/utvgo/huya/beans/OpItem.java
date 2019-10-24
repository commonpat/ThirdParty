package com.utvgo.huya.beans;

import java.io.Serializable;

public class OpItem implements Serializable {
    private int pkId;
    private String status;
    private String imgUrl;
    private String name;
    private String des;
    private String remark;
    private String isVideo;
    private String videoUrl = "";
    private Object createBy;
    private Object editeBy;
    private Object createTime;
    private Object updateTime;
    private int subjectId;
    private int priority;
    private String href;
    private String hrefType;
    private String focusImgUrl;

    public int getPkId() {
        return pkId;
    }

    public void setPkId(int pkId) {
        this.pkId = pkId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Object getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Object createBy) {
        this.createBy = createBy;
    }

    public Object getEditeBy() {
        return editeBy;
    }

    public void setEditeBy(Object editeBy) {
        this.editeBy = editeBy;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getHrefType() {
        return hrefType;
    }

    public void setHrefType(String hrefType) {
        this.hrefType = hrefType;
    }

    public String getFocusImgUrl() {
        return focusImgUrl;
    }

    public void setFocusImgUrl(String focusImgUrl) {
        this.focusImgUrl = focusImgUrl;
    }

    public String getIsVideo() {
        return isVideo;
    }

    public void setIsVideo(String isVideo) {
        this.isVideo = isVideo;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
