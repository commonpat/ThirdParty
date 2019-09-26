package com.utvgo.huya.beans;

import java.util.List;

public class SelectData extends BaseResponse {

    private String imageProfix;
    private List<SData> data;

    public void setData(List<SData> data) {
        this.data = data;
    }

    public List<SData> getData() {
        return data;
    }

    public void setImageProfix(String imageProfix) {
        this.imageProfix = imageProfix;
    }

    public String getImageProfix() {
        return imageProfix;
    }

    public static class SData {
        private String imgUrl;
        private String vodId;
        private String name;
        private String hrefType;
        private String href;
        private int number;
        private int typeId;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getVodId() {
            return vodId;
        }

        public void setVodId(String vodId) {
            this.vodId = vodId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHrefType() {
            return hrefType;
        }

        public void setHrefType(String hrefType) {
            this.hrefType = hrefType;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }
    }
}
