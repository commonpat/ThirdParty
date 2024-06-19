package com.utvgo.handsome.bean;

/**
 * @author wzb
 * @description:
 * @date : 2021/3/15 15:19
 */
public class BeanMvPlayPoint extends BaseResponse {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        private long id;
        private String keyNo;
        private String contentMid;
        private String contentType;
        private String createTime;
        private String playTime;
        private String sourceType;
        private int playPoint;
        private String contentName;
        private String singerMids;
        private String singerNames;
        public void setId(long id) {
            this.id = id;
        }
        public long getId() {
            return id;
        }

        public void setKeyNo(String keyNo) {
            this.keyNo = keyNo;
        }
        public String getKeyNo() {
            return keyNo;
        }

        public void setContentMid(String contentMid) {
            this.contentMid = contentMid;
        }
        public String getContentMid() {
            return contentMid;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }
        public String getContentType() {
            return contentType;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
        public String getCreateTime() {
            return createTime;
        }

        public void setPlayTime(String playTime) {
            this.playTime = playTime;
        }
        public String getPlayTime() {
            return playTime;
        }

        public void setSourceType(String sourceType) {
            this.sourceType = sourceType;
        }
        public String getSourceType() {
            return sourceType;
        }

        public void setPlayPoint(int playPoint) {
            this.playPoint = playPoint;
        }
        public int getPlayPoint() {
            return playPoint;
        }

        public void setContentName(String contentName) {
            this.contentName = contentName;
        }
        public String getContentName() {
            return contentName;
        }

        public void setSingerMids(String singerMids) {
            this.singerMids = singerMids;
        }
        public String getSingerMids() {
            return singerMids;
        }

        public void setSingerNames(String singerNames) {
            this.singerNames = singerNames;
        }
        public String getSingerNames() {
            return singerNames;
        }

    }
}
