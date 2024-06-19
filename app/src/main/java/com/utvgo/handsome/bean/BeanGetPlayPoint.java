package com.utvgo.handsome.bean;

/**
 * @author wzb
 * @description:
 * @date : 2021/3/15 15:19
 */
public class BeanGetPlayPoint extends BaseResponse {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        private int totalTime;
        private String imageBig;
        private String multiSetType;
        private long videoId;
        private String imageMid;
        private String keyNo;
        private String videoName;
        private String programName;
        private String imageSmall;
        private long id;
        private int playPoint;
        private int programId;
        private int channelId;

        public int getTotalTime() {
            return totalTime;
        }

        public void setTotalTime(int totalTime) {
            this.totalTime = totalTime;
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

        public long getVideoId() {
            return videoId;
        }

        public void setVideoId(long videoId) {
            this.videoId = videoId;
        }

        public String getImageMid() {
            return imageMid;
        }

        public void setImageMid(String imageMid) {
            this.imageMid = imageMid;
        }

        public String getKeyNo() {
            return keyNo;
        }

        public void setKeyNo(String keyNo) {
            this.keyNo = keyNo;
        }

        public String getVideoName() {
            return videoName;
        }

        public void setVideoName(String videoName) {
            this.videoName = videoName;
        }

        public String getProgramName() {
            return programName;
        }

        public void setProgramName(String programName) {
            this.programName = programName;
        }

        public String getImageSmall() {
            return imageSmall;
        }

        public void setImageSmall(String imageSmall) {
            this.imageSmall = imageSmall;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getPlayPoint() {
            return playPoint;
        }

        public void setPlayPoint(int playPoint) {
            this.playPoint = playPoint;
        }

        public int getProgramId() {
            return programId;
        }

        public void setProgramId(int programId) {
            this.programId = programId;
        }

        public int getChannelId() {
            return channelId;
        }

        public void setChannelId(int channelId) {
            this.channelId = channelId;
        }
    }
}
