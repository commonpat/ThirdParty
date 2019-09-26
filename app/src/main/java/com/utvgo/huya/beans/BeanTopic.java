package com.utvgo.huya.beans;

import java.util.List;

/**
 * Created by oo on 2017/10/23.
 */

public class BeanTopic extends BaseResponse {


    /**
     * code : 1
     * data : {"id":4,"status":"0","imgNum":0,"imgUrl":"2017/12/15/20171215173933207.jpg","imgUrl1":"","imgUrl2":"","imgUrl3":"","imgUrl4":"","nameBgColor":"","nameColor":"","name":"活力舞曲","des":"活力舞曲","remark":"","createBy":null,"editeBy":null,"createTime":null,"updateTime":null,"utvgoSubjectRecordList":[{"id":661,"name":"Colorful Day","subjectId":4,"status":"0","priority":4,"href":"play_video.html?mvsMid=53,1,15,8&mvMid=sTRLNotltusSISRYKRxV2d","imgUrl":"2017/12/15/20171215180725843.jpg","des":"snh48","remark":"","createBy":null,"editeBy":null,"createTime":null,"updateTime":null},{"id":659,"name":"宠爱","subjectId":4,"status":"0","priority":3,"href":"play_video.html?mvsMid=53,1,9,8&mvMid=x4pVO9fCsrohlEuSNnJ2c6","imgUrl":"2017/12/15/20171215180821157.jpg","des":"宠爱","remark":"","createBy":null,"editeBy":null,"createTime":null,"updateTime":null},{"id":658,"name":"MOMOLAND《惊人》","subjectId":4,"status":"0","priority":2,"href":"play_video.html?mvsMid=60,1,1,8&mvMid=xDosLybCrucuUc4otNcf7d","imgUrl":"2017/12/15/20171215180830225.jpg","des":"MOMOLAND《惊人》","remark":"","createBy":null,"editeBy":null,"createTime":null,"updateTime":null},{"id":660,"name":"早安少女","subjectId":4,"status":"0","priority":1,"href":"play_video.html?mvsMid=53,1,7,8&mvMid=o3NQ5rDhs5071XEsjdb09b","imgUrl":"2017/12/15/20171215180810228.jpg","des":"早安少女","remark":"","createBy":null,"editeBy":null,"createTime":null,"updateTime":null}]}
     * message : success
     * imageProfix : http://192.168.18.43:81/cms/uploadFile/image/
     */

    private DataBean data;
    private String imageProfix;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getImageProfix() {
        return imageProfix;
    }

    public void setImageProfix(String imageProfix) {
        this.imageProfix = imageProfix;
    }

    public static class DataBean {
        /**
         * id : 4
         * status : 0
         * imgNum : 0
         * imgUrl : 2017/12/15/20171215173933207.jpg
         * imgUrl1 :
         * imgUrl2 :
         * imgUrl3 :
         * imgUrl4 :
         * nameBgColor :
         * nameColor :
         * name : 活力舞曲
         * des : 活力舞曲
         * remark :
         * createBy : null
         * editeBy : null
         * createTime : null
         * updateTime : null
         * utvgoSubjectRecordList : [{"id":661,"name":"Colorful Day","subjectId":4,"status":"0","priority":4,"href":"play_video.html?mvsMid=53,1,15,8&mvMid=sTRLNotltusSISRYKRxV2d","imgUrl":"2017/12/15/20171215180725843.jpg","des":"snh48","remark":"","createBy":null,"editeBy":null,"createTime":null,"updateTime":null},{"id":659,"name":"宠爱","subjectId":4,"status":"0","priority":3,"href":"play_video.html?mvsMid=53,1,9,8&mvMid=x4pVO9fCsrohlEuSNnJ2c6","imgUrl":"2017/12/15/20171215180821157.jpg","des":"宠爱","remark":"","createBy":null,"editeBy":null,"createTime":null,"updateTime":null},{"id":658,"name":"MOMOLAND《惊人》","subjectId":4,"status":"0","priority":2,"href":"play_video.html?mvsMid=60,1,1,8&mvMid=xDosLybCrucuUc4otNcf7d","imgUrl":"2017/12/15/20171215180830225.jpg","des":"MOMOLAND《惊人》","remark":"","createBy":null,"editeBy":null,"createTime":null,"updateTime":null},{"id":660,"name":"早安少女","subjectId":4,"status":"0","priority":1,"href":"play_video.html?mvsMid=53,1,7,8&mvMid=o3NQ5rDhs5071XEsjdb09b","imgUrl":"2017/12/15/20171215180810228.jpg","des":"早安少女","remark":"","createBy":null,"editeBy":null,"createTime":null,"updateTime":null}]
         */

        private int id;
        private String status;
        private int imgNum;
        private String imgUrl;
        private String imgUrl1;
        private String imgUrl2;
        private String imgUrl3;
        private String imgUrl4;
        private String nameBgColor;
        private String nameColor;
        private String name;
        private String des;
        private String remark;
        private Object createBy;
        private Object editeBy;
        private Object createTime;
        private Object updateTime;
        private String showType;

        public String getShowType() {
            return showType;
        }

        public void setShowType(String showType) {
            this.showType = showType;
        }

        private List<UtvgoSubjectRecordListBean> utvgoSubjectRecordList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getImgNum() {
            return imgNum;
        }

        public void setImgNum(int imgNum) {
            this.imgNum = imgNum;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getImgUrl1() {
            return imgUrl1;
        }

        public void setImgUrl1(String imgUrl1) {
            this.imgUrl1 = imgUrl1;
        }

        public String getImgUrl2() {
            return imgUrl2;
        }

        public void setImgUrl2(String imgUrl2) {
            this.imgUrl2 = imgUrl2;
        }

        public String getImgUrl3() {
            return imgUrl3;
        }

        public void setImgUrl3(String imgUrl3) {
            this.imgUrl3 = imgUrl3;
        }

        public String getImgUrl4() {
            return imgUrl4;
        }

        public void setImgUrl4(String imgUrl4) {
            this.imgUrl4 = imgUrl4;
        }

        public String getNameBgColor() {
            return nameBgColor;
        }

        public void setNameBgColor(String nameBgColor) {
            this.nameBgColor = nameBgColor;
        }

        public String getNameColor() {
            return nameColor;
        }

        public void setNameColor(String nameColor) {
            this.nameColor = nameColor;
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

        public List<UtvgoSubjectRecordListBean> getUtvgoSubjectRecordList() {
            return utvgoSubjectRecordList;
        }

        public void setUtvgoSubjectRecordList(List<UtvgoSubjectRecordListBean> utvgoSubjectRecordList) {
            this.utvgoSubjectRecordList = utvgoSubjectRecordList;
        }

        public static class UtvgoSubjectRecordListBean {
            /**
             * id : 661
             * name : Colorful Day
             * subjectId : 4
             * status : 0
             * priority : 4
             * href : play_video.html?mvsMid=53,1,15,8&mvMid=sTRLNotltusSISRYKRxV2d
             * imgUrl : 2017/12/15/20171215180725843.jpg
             * des : snh48
             * remark :
             * createBy : null
             * editeBy : null
             * createTime : null
             * updateTime : null
             */

            private int id;
            private String name;
            private int subjectId;
            private String status;
            private int priority;
            private String href;
            private String imgUrl;
            private String des;
            private String mvMid;
            private String remark;
            private Object createBy;
            private Object editeBy;
            private Object createTime;
            private Object updateTime;

            public String getMvMid() {
                return mvMid;
            }

            public void setMvMid(String mvMid) {
                this.mvMid = mvMid;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSubjectId() {
                return subjectId;
            }

            public void setSubjectId(int subjectId) {
                this.subjectId = subjectId;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
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

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
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
        }
    }
}
