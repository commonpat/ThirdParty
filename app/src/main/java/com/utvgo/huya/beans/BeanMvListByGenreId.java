package com.utvgo.huya.beans;

import java.util.List;

/**
 * Created by haha on 2017/8/8.
 */

public class BeanMvListByGenreId extends BaseResponse {

    /**
     *      "code":"1",
     *     "data":Object{...},
     *     "totalPage":4,
     *     "count":31,
     *     "currentPage":1,
     *     "message":"success"
     * **/
    private  dataBean data;
    private int totalPage;
    private int count;
    private int currentPage;

    public dataBean getData() {
        return data;
    }

    public void setData(dataBean data) {
        this.data = data;
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
    public static class dataBean{
        /**
         *          "gridType":"1",
         *         "channelName":"王者荣耀",
         *         "programs":Array[8],
         *         "imageProfix":"http://172.16.146.66:81/cms/uploadFile/image/"
         * */
        private String gridType;
        private String channelName;
        private List<programBean> programs;
        private String imageProfix;

        public String getGridType() {
            return gridType;
        }

        public void setGridType(String gridType) {
            this.gridType = gridType;
        }

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public List<programBean> getPrograms() {
            return programs;
        }

        public void setPrograms(List<programBean> programs) {
            this.programs = programs;
        }

        public String getImageProfix() {
            return imageProfix;
        }

        public void setImageProfix(String imageProfix) {
            this.imageProfix = imageProfix;
        }
        public static class programBean{
            /***
             *             supplierName":"虎牙",
             *                 "pkId":64375,
             *                 "setNums":1,
             *                 "supplierId":39,
             *                 "imageBig":"EG/huya/198150.jpg",
             *                 "multiSetType":"0",
             *                 "priority":0,
             *                 "imageMid":"EG/huya/198150.jpg",
             *                 "keyWord":"",
             *                 "maxSet":0,
             *                 "isFree":"0",
             *                 "doubanScore":-1,
             *                 "createTime":1566815376000,
             *                 "thirdPlayUrl":"",
             *                 "name":"八强赛  A4 VS B4 2",
             *                 "imageSmall":"EG/huya/198150.jpg",
             *                 "playSourceId":0,
             *                 "vipPriority":0,
             *                 "playSourceName":"",
             *                 "channelId":34
             * */
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
        }
    }
}
