package com.utvgo.huya.beans;

import java.util.List;

/**
 * Created by haha on 2017/8/10.
 */

public class BeanCollect {


    /**
     * code : 1
     * data : [{"collectionType":"1","isFree":0,"contentId":725,"bigPic":"mvPoster/hd/ifxwtPzAqWkfE4THI1lIb7.jpg","contentMid":"ifxwtPzAqWkfE4THI1lIb7","pic":"mvPoster/hd/ifxwtPzAqWkfE4THI1lIb7.jpg","smallPic":"mvPoster/hd/ifxwtPzAqWkfE4THI1lIb7.jpg","contentName":"欧阳娜娜全新专辑携手成龙《梦的天空》MV大首播，夺人眼球！"},{"collectionType":"1","isFree":0,"contentId":146,"bigPic":"mvPoster/mv/6M9K6qX8sK8hBKpOrvpW0e.jpg","contentMid":"6M9K6qX8sK8hBKpOrvpW0e","pic":"mvPoster/mv/6M9K6qX8sK8hBKpOrvpW0e.jpg","smallPic":"mvPoster/mv/6M9K6qX8sK8hBKpOrvpW0e.jpg","contentName":"谢帝《瓜老外》官方版"}]
     * totalPage : 1
     * count : 2
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

    public static class DataBean {
        /**
         * collectionType : 1
         * isFree : 0
         * contentId : 725
         * bigPic : mvPoster/hd/ifxwtPzAqWkfE4THI1lIb7.jpg
         * contentMid : ifxwtPzAqWkfE4THI1lIb7
         * pic : mvPoster/hd/ifxwtPzAqWkfE4THI1lIb7.jpg
         * smallPic : mvPoster/hd/ifxwtPzAqWkfE4THI1lIb7.jpg
         * contentName : 欧阳娜娜全新专辑携手成龙《梦的天空》MV大首播，夺人眼球！
         */

        private String collectionType;
        private int isFree;
        private int contentId;
        private String bigPic;
        private String contentMid;
        private String pic;
        private String smallPic;
        private String contentName;

        public String getCollectionType() {
            return collectionType;
        }

        public void setCollectionType(String collectionType) {
            this.collectionType = collectionType;
        }

        public int getIsFree() {
            return isFree;
        }

        public void setIsFree(int isFree) {
            this.isFree = isFree;
        }

        public int getContentId() {
            return contentId;
        }

        public void setContentId(int contentId) {
            this.contentId = contentId;
        }

        public String getBigPic() {
            return bigPic;
        }

        public void setBigPic(String bigPic) {
            this.bigPic = bigPic;
        }

        public String getContentMid() {
            return contentMid;
        }

        public void setContentMid(String contentMid) {
            this.contentMid = contentMid;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getSmallPic() {
            return smallPic;
        }

        public void setSmallPic(String smallPic) {
            this.smallPic = smallPic;
        }

        public String getContentName() {
            return contentName;
        }

        public void setContentName(String contentName) {
            this.contentName = contentName;
        }
    }
}
