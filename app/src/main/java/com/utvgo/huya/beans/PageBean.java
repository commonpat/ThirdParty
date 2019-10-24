package com.utvgo.huya.beans;

import java.util.List;

public class PageBean extends BaseResponse {

    /**
     * code : 1
     * data : [{"imgUrl":"","number":1,"videoUrl":"http://172.16.146.69:17553/EG/feimo/20190422/GZFM0416039.mp4,http://172.16.146.69:17553/EG/feimo/20190422/GZFM0416003.mp4,http://172.16.146.69:17553/EG/bears/20190319/SZX0319044.mp4","isVideo":"1","typeId":36,"href":"playPage.html?multiSetType=0&channelId=30&pkId=49319&videoId=0&point=0&vipCode=vip_code_32&packageId=24,playPage.html?multiSetType=0&channelId=30&pkId=49287&videoId=0&point=0&vipCode=vip_code_32&packageId=24,playPage.html?multiSetType=0&channelId=30&pkId=49069&videoId=0&point=0&vipCode=vip_code_32&packageId=24"},{"imgUrl":"2019/04/11/20190411151915388.jpg","number":2,"videoUrl":"","isVideo":"0","typeId":36,"href":"http://172.16.146.40/uusports/albumPlayer.html?pkId=49284&vipCode=vip_code_32&packageId=24"},{"imgUrl":"2019/04/09/20190409140812691.jpg","number":3,"videoUrl":"","isVideo":"0","typeId":36,"href":"http://172.16.146.40/uusports/albumPlayer.html?pkId=49149&vipCode=vip_code_32&packageId=24"},{"imgUrl":"2019/04/11/20190411152026721.jpg","number":4,"videoUrl":"","isVideo":"0","typeId":36,"href":"http://172.16.146.40/uusports/albumPlayer.html?pkId=49283&vipCode=vip_code_32&packageId=24"},{"imgUrl":"2019/04/09/20190409140925284.jpg","number":5,"videoUrl":"","isVideo":"0","typeId":36,"href":"http://172.16.146.40/uusports/albumPlayer.html?pkId=49154&vipCode=vip_code_32&packageId=24"},{"imgUrl":"2019/04/11/20190411152121749.jpg","number":6,"videoUrl":"","isVideo":"0","typeId":36,"href":"http://172.16.146.40/uusports/albumPlayer.html?pkId=49282&vipCode=vip_code_32&packageId=24"},{"imgUrl":"2019/04/24/20190424115721387.jpg","number":7,"videoUrl":"","isVideo":"0","typeId":36,"href":"http://172.16.146.40/uusports/playPage.html?multiSetType=0&channelId=19&pkId=52130&videoId=0&point=0"},{"imgUrl":"2019/04/24/20190424115845577.jpg","number":8,"videoUrl":"","isVideo":"0","typeId":36,"href":"http://172.16.146.40/uusports/playPage.html?multiSetType=0&channelId=20&pkId=52143&videoId=0&point=0&"}]
     * message : success
     * imageProfix : http://172.16.146.40:81/cms/uploadFile/image/
     */

    private String imageProfix;
    private List<DataBean> data;

    public String getImageProfix() {
        return imageProfix;
    }

    public void setImageProfix(String imageProfix) {
        this.imageProfix = imageProfix;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * imgUrl :
         * number : 1
         * videoUrl : http://172.16.146.69:17553/EG/feimo/20190422/GZFM0416039.mp4,http://172.16.146.69:17553/EG/feimo/20190422/GZFM0416003.mp4,http://172.16.146.69:17553/EG/bears/20190319/SZX0319044.mp4
         * isVideo : 1
         * typeId : 36
         * href : playPage.html?multiSetType=0&channelId=30&pkId=49319&videoId=0&point=0&vipCode=vip_code_32&packageId=24,playPage.html?multiSetType=0&channelId=30&pkId=49287&videoId=0&point=0&vipCode=vip_code_32&packageId=24,playPage.html?multiSetType=0&channelId=30&pkId=49069&videoId=0&point=0&vipCode=vip_code_32&packageId=24
         */

        private String imgUrl;
        private int number;
        private String videoUrl;
        private String isVideo;
        private int typeId;
        private String href;
        private String hrefType;

        public String gethrefType() {
            return hrefType;
        }

        public void sethrefType(String hrefType) {
            hrefType = hrefType;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getIsVideo() {
            return isVideo;
        }

        public void setIsVideo(String isVideo) {
            this.isVideo = isVideo;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }
}
