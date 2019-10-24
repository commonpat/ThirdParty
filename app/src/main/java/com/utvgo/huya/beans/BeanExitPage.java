package com.utvgo.huya.beans;

import java.util.List;

public class BeanExitPage extends BaseResponse {
    private List<Data> data;
    private String imageProfix;

    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return this.data;
    }

    public void setImageProfix(String imageProfix) {
        this.imageProfix = imageProfix;
    }

    public String getImageProfix() {
        return this.imageProfix;
    }

    public static class Data {
        private String imgUrl;

        private int number;

        private String videoUrl;

        private String bgImgUrl;

        private String isVideo;

        private int typeId;

        private String hrefType;

        private String href;

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getImgUrl() {
            return this.imgUrl;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getNumber() {
            return this.number;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getVideoUrl() {
            return this.videoUrl;
        }

        public void setBgImgUrl(String bgImgUrl) {
            this.bgImgUrl = bgImgUrl;
        }

        public String getBgImgUrl() {
            return this.bgImgUrl;
        }

        public void setIsVideo(String isVideo) {
            this.isVideo = isVideo;
        }

        public String getIsVideo() {
            return this.isVideo;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public int getTypeId() {
            return this.typeId;
        }

        public void setHrefType(String hrefType) {
            this.hrefType = hrefType;
        }

        public String getHrefType() {
            return this.hrefType;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getHref() {
            return this.href;
        }
    }
}
