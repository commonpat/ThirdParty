package com.utvgo.huya.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by haha on 2017/8/10.
 */

public class BeanAreaTypeSingerList {


    /**
     * code : 1
     * data : [{"id":148,"singerId":5840,"singerMid":"003EpLWK19z2ah","singerName":"红孩儿","singerTranslatorName":"","singerPic":"T001R120x120M000003EpLWK19z2ah.jpg","singerSmallPic":"T001R300x300M000003EpLWK19z2ah.jpg","singerBigPic":"T001R500x500M000003EpLWK19z2ah.jpg","areaId":1,"type":"2","genreId":0,"pinyinFirst":"a","status":"1","createBy":"sys","editeBy":"sys","auditBy":"sys","createTime":"2017-06-26 21:36:04","updateTime":"2017-07-06 20:16:08","auditeTime":"2017-06-26 21:36:04"}]
     * totalPage : 1
     * count : 1
     * currentPage : 1
     * message : success
     */

    private String code;
    private int totalPage;
    private int count;
    private int currentPage;
    private String message;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * id : 148
         * singerId : 5840
         * singerMid : 003EpLWK19z2ah
         * singerName : 红孩儿
         * singerTranslatorName :
         * singerPic : T001R120x120M000003EpLWK19z2ah.jpg
         * singerSmallPic : T001R300x300M000003EpLWK19z2ah.jpg
         * singerBigPic : T001R500x500M000003EpLWK19z2ah.jpg
         * areaId : 1
         * type : 2
         * genreId : 0
         * pinyinFirst : a
         * status : 1
         * createBy : sys
         * editeBy : sys
         * auditBy : sys
         * createTime : 2017-06-26 21:36:04
         * updateTime : 2017-07-06 20:16:08
         * auditeTime : 2017-06-26 21:36:04
         */

        private int id;
        private int singerId;
        private String singerMid;
        private String singerName;
        private String singerTranslatorName;
        private String singerPic;
        private String singerSmallPic;
        private String singerBigPic;
        private int areaId;
        private String type;
        private int genreId;
        private String pinyinFirst;
        private String status;
        private String createBy;
        private String editeBy;
        private String auditBy;
        private String createTime;
        private String updateTime;
        private String auditeTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSingerId() {
            return singerId;
        }

        public void setSingerId(int singerId) {
            this.singerId = singerId;
        }

        public String getSingerMid() {
            return singerMid;
        }

        public void setSingerMid(String singerMid) {
            this.singerMid = singerMid;
        }

        public String getSingerName() {
            return singerName;
        }

        public void setSingerName(String singerName) {
            this.singerName = singerName;
        }

        public String getSingerTranslatorName() {
            return singerTranslatorName;
        }

        public void setSingerTranslatorName(String singerTranslatorName) {
            this.singerTranslatorName = singerTranslatorName;
        }

        public String getSingerPic() {
            return singerPic;
        }

        public void setSingerPic(String singerPic) {
            this.singerPic = singerPic;
        }

        public String getSingerSmallPic() {
            return singerSmallPic;
        }

        public void setSingerSmallPic(String singerSmallPic) {
            this.singerSmallPic = singerSmallPic;
        }

        public String getSingerBigPic() {
            return singerBigPic;
        }

        public void setSingerBigPic(String singerBigPic) {
            this.singerBigPic = singerBigPic;
        }

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getGenreId() {
            return genreId;
        }

        public void setGenreId(int genreId) {
            this.genreId = genreId;
        }

        public String getPinyinFirst() {
            return pinyinFirst;
        }

        public void setPinyinFirst(String pinyinFirst) {
            this.pinyinFirst = pinyinFirst;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getEditeBy() {
            return editeBy;
        }

        public void setEditeBy(String editeBy) {
            this.editeBy = editeBy;
        }

        public String getAuditBy() {
            return auditBy;
        }

        public void setAuditBy(String auditBy) {
            this.auditBy = auditBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getAuditeTime() {
            return auditeTime;
        }

        public void setAuditeTime(String auditeTime) {
            this.auditeTime = auditeTime;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeInt(this.singerId);
            dest.writeString(this.singerMid);
            dest.writeString(this.singerName);
            dest.writeString(this.singerTranslatorName);
            dest.writeString(this.singerPic);
            dest.writeString(this.singerSmallPic);
            dest.writeString(this.singerBigPic);
            dest.writeInt(this.areaId);
            dest.writeString(this.type);
            dest.writeInt(this.genreId);
            dest.writeString(this.pinyinFirst);
            dest.writeString(this.status);
            dest.writeString(this.createBy);
            dest.writeString(this.editeBy);
            dest.writeString(this.auditBy);
            dest.writeString(this.createTime);
            dest.writeString(this.updateTime);
            dest.writeString(this.auditeTime);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.singerId = in.readInt();
            this.singerMid = in.readString();
            this.singerName = in.readString();
            this.singerTranslatorName = in.readString();
            this.singerPic = in.readString();
            this.singerSmallPic = in.readString();
            this.singerBigPic = in.readString();
            this.areaId = in.readInt();
            this.type = in.readString();
            this.genreId = in.readInt();
            this.pinyinFirst = in.readString();
            this.status = in.readString();
            this.createBy = in.readString();
            this.editeBy = in.readString();
            this.auditBy = in.readString();
            this.createTime = in.readString();
            this.updateTime = in.readString();
            this.auditeTime = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
