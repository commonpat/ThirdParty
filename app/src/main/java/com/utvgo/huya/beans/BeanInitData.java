package com.utvgo.huya.beans;

public class BeanInitData extends BaseResponse {


    /**
     * code : 1
     * endPushContent : {"imgUrl":"2018/07/21/20180721174332642.jpg","pushContentType":"7","pushAreas":"","pushMode":"1","name":"退出推送","id":2,"href":"http://172.16.146.41/qqmusic_zt.html?themId=49","pushContentMid":"1","pushOrderType":"0"}
     * startPushContent : {"imgUrl":"2018/07/21/20180721155130536.png","pushContentType":"1","pushAreas":"","pushMode":"0","name":"test","id":3,"href":"test","pushContentMid":"1","pushOrderType":"0"}
     * message : success
     * homePageResource : {"imagUrl":"2018/07/21/20180721174214203.png","logoUrl":"2018/07/21/20180721174211761.png"}
     */

    private EndPushContentBean endPushContent;
    private StartPushContentBean startPushContent;
    private HomePageResourceBean homePageResource;

    public EndPushContentBean getEndPushContent() {
        return endPushContent;
    }

    public void setEndPushContent(EndPushContentBean endPushContent) {
        this.endPushContent = endPushContent;
    }

    public StartPushContentBean getStartPushContent() {
        return startPushContent;
    }

    public void setStartPushContent(StartPushContentBean startPushContent) {
        this.startPushContent = startPushContent;
    }

    public HomePageResourceBean getHomePageResource() {
        return homePageResource;
    }

    public void setHomePageResource(HomePageResourceBean homePageResource) {
        this.homePageResource = homePageResource;
    }

    public static class EndPushContentBean {
        /**
         * imgUrl : 2018/07/21/20180721174332642.jpg
         * pushContentType : 7
         * pushAreas :
         * pushMode : 1
         * name : 退出推送
         * id : 2
         * href : http://172.16.146.41/qqmusic_zt.html?themId=49
         * pushContentMid : 1
         * pushOrderType : 0
         */

        private String imgUrl;
        private String pushContentType;
        private String pushAreas;
        private String pushMode;
        private String name;
        private int id;
        private String href;
        private String pushContentMid;
        private String pushOrderType;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getPushContentType() {
            return pushContentType;
        }

        public void setPushContentType(String pushContentType) {
            this.pushContentType = pushContentType;
        }

        public String getPushAreas() {
            return pushAreas;
        }

        public void setPushAreas(String pushAreas) {
            this.pushAreas = pushAreas;
        }

        public String getPushMode() {
            return pushMode;
        }

        public void setPushMode(String pushMode) {
            this.pushMode = pushMode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getPushContentMid() {
            return pushContentMid;
        }

        public void setPushContentMid(String pushContentMid) {
            this.pushContentMid = pushContentMid;
        }

        public String getPushOrderType() {
            return pushOrderType;
        }

        public void setPushOrderType(String pushOrderType) {
            this.pushOrderType = pushOrderType;
        }
    }

    public static class StartPushContentBean {
        /**
         * imgUrl : 2018/07/21/20180721155130536.png
         * pushContentType : 1
         * pushAreas :
         * pushMode : 0
         * name : test
         * id : 3
         * href : test
         * pushContentMid : 1
         * pushOrderType : 0
         */

        private String imgUrl;
        private String pushContentType;
        private String pushAreas;
        private String pushMode;
        private String name;
        private int id;
        private String href;
        private String pushContentMid;
        private String pushOrderType;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getPushContentType() {
            return pushContentType;
        }

        public void setPushContentType(String pushContentType) {
            this.pushContentType = pushContentType;
        }

        public String getPushAreas() {
            return pushAreas;
        }

        public void setPushAreas(String pushAreas) {
            this.pushAreas = pushAreas;
        }

        public String getPushMode() {
            return pushMode;
        }

        public void setPushMode(String pushMode) {
            this.pushMode = pushMode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getPushContentMid() {
            return pushContentMid;
        }

        public void setPushContentMid(String pushContentMid) {
            this.pushContentMid = pushContentMid;
        }

        public String getPushOrderType() {
            return pushOrderType;
        }

        public void setPushOrderType(String pushOrderType) {
            this.pushOrderType = pushOrderType;
        }
    }

    public static class HomePageResourceBean {
        /**
         * imagUrl : 2018/07/21/20180721174214203.png
         * logoUrl : 2018/07/21/20180721174211761.png
         */

        private String imagUrl;
        private String logoUrl;

        public String getImagUrl() {
            return imagUrl;
        }

        public void setImagUrl(String imagUrl) {
            this.imagUrl = imagUrl;
        }

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
        }
    }
}
