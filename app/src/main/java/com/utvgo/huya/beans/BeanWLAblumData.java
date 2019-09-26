package com.utvgo.huya.beans;

import java.util.List;

public class BeanWLAblumData extends BaseResponse{
/**
 *
 "message":"success",
 "data":Object{...},
 "currentPage":1,
 "code":"1",
 "count":5,
 "totalPage":1,
 "currentSize":5
 * */
    private int totalPage;
    private int count;
    private int currentPage;
    private int currentSize;
    private DataBean data;

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

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
            /***
             *
             *{
             *
             *          "pkId":53089,
             *         "supplierName":"虎牙",
             *         "playSourceId":36,
             *         "packageId":29,
             *         "albumBackground":null,
             *         "imageSmall":"",
             *         "name":"LCK春季联赛",
             *         "multiSetType":"4",
             *         "mainRole":"LCK联赛",
             *         "showType":"",
             *         "vipCode":"vip_code_50",
             *         "channelName":"电竞",
             *         "supplierId":39,
             *         "status":"1",
             *         "channelId":30,
             *         "thirdPlayUrl":"",
             *         "labels":Array[0],
             *         "videos":Array[5],
             *         "descript":"",
             *         "playSourceName":"",
             *         "director":"",
             *         "languageName":"普通话",
             *         "imageMid":"",
             *         "publicTime":"2019",
             *         "vipName":"U点电竞",
             *         "areaName":"内地",
             *         "isFree":"0",
             *         "imageProfix":"http://172.16.146.66:81/cms/uploadFile/image/",
             *         "imageBig":""
             * */

        private int pkId;
        private int supplierId;
        private String multiSetType;
        private List<VideoBean> videos;
        private String languageName;
        private String publicTime;
        private String vipName;

        public String[] getLabels() {
            return labels;
        }

        public void setLabels(String[] labels) {
            this.labels = labels;
        }

        private int isFree;
        private String areaName;
        private String showType;
        private String playSourceName;
        private int channelId;
        private String supplierName;
        private String director;
        private String imageBig;
        private int packageId;
        private String albumBackground;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        //private HistoryBean history;
        private String imageMid;
        private String imageProfix;
        private String[] labels;
        private String mainRole;
        private String thirdPlayUrl;
        private String name;
        private String imageSmall;
        private String channelName;
        private int playSourceId;
        private String descript;
        private String   vipCode;
        private String status;

        public int getPkId() {
            return pkId;
        }

        public int getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(int supplierId) {
            this.supplierId = supplierId;
        }

        public String getMultiSetType() {
            return multiSetType;
        }

        public void setMultiSetType(String multiSetType) {
            this.multiSetType = multiSetType;
        }

        public String getLanguageName() {
            return languageName;
        }

        public void setLanguageName(String languageName) {
            this.languageName = languageName;
        }

        public String getPublicTime() {
            return publicTime;
        }

        public void setPublicTime(String publicTime) {
            this.publicTime = publicTime;
        }

        public String getVipName() {
            return vipName;
        }

        public void setVipName(String vipName) {
            this.vipName = vipName;
        }

        public int getIsFree() {
            return isFree;
        }

        public void setIsFree(int isFree) {
            this.isFree = isFree;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getShowType() {
            return showType;
        }

        public void setShowType(String showType) {
            this.showType = showType;
        }

        public String getPlaySourceName() {
            return playSourceName;
        }

        public void setPlaySourceName(String playSourceName) {
            this.playSourceName = playSourceName;
        }

        public List<VideoBean> getVideos() {
            return videos;
        }

        public void setVideos(List<VideoBean> videos) {
            this.videos = videos;
        }

        public int getChannelId() {
            return channelId;
        }

        public int getPackageId() {
            return packageId;
        }

        public String getSupplierName() {
            return supplierName;
        }

        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public String getImageBig() {
            return imageBig;
        }

        public void setImageBig(String imageBig) {
            this.imageBig = imageBig;
        }

        public String getAlbumBackground() {
            return albumBackground;
        }

        public void setAlbumBackground(String albumBackground) {
            this.albumBackground = albumBackground;
        }

        public String getImageMid() {
            return imageMid;
        }

        public void setImageMid(String imageMid) {
            this.imageMid = imageMid;
        }

        public String getImageProfix() {
            return imageProfix;
        }

        public void setImageProfix(String imageProfix) {
            this.imageProfix = imageProfix;
        }


        public String getMainRole() {
            return mainRole;
        }

        public void setMainRole(String mainRole) {
            this.mainRole = mainRole;
        }

        public String getThirdPlayUrl() {
            return thirdPlayUrl;
        }

        public void setThirdPlayUrl(String thirdPlayUrl) {
            this.thirdPlayUrl = thirdPlayUrl;
        }

        public String getImageSmall() {
            return imageSmall;
        }

        public void setImageSmall(String imageSmall) {
            this.imageSmall = imageSmall;
        }

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public void setPkId(int pkId) {
            this.pkId = pkId;
        }

        public void setChannelId(int channelId) {
            this.channelId = channelId;
        }

        public void setPackageId(int packageId) {
            this.packageId = packageId;
        }

        public int getPlaySourceId() {
            return playSourceId;
        }

        public void setPlaySourceId(int playSourceId) {
            this.playSourceId = playSourceId;
        }

        public String getDescript() {
            return descript;
        }

        public void setDescript(String descript) {
            this.descript = descript;
        }

        public String getVipCode() {
            return vipCode;
        }

        public void setVipCode(String vipCode) {
            this.vipCode = vipCode;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public static class VideoBean{
            /**
             *{
             *                 "aliasName": "小棉花Top5大腿杯篇：白白橘右京名刀强行续命！空血硬钢四人怒保基地",
             *                 "focusImgUrl": "",
             *                 "videoUrlHigh": "http://172.16.146.69:17553/EG/huya/169832881.mp4",
             *                 "imageBig": "2019/08/06/20190806105958517.jpg",
             *                 "freeSecond": 15,
             *                 "videoId": 197058,
             *                 "imageMid": "2019/08/06/20190806105958517.jpg",
             *                 "number": 0,
             *                 "publicTime": "20180101",
             *                 "isFree": "0",
             *                 "vodId": "",
             *                 "normalImgUrl": "",
             *                 "name": "小棉花Top5大腿杯篇：白白橘右京名刀强行续命！空血硬钢四人怒保基地",
             *                 "imageSmall": "2019/08/06/20190806105958517.jpg",
             *                 "videoUrlFluency": "http://172.16.146.69:17553/EG/huya/169832881.mp4",
             *                 "titleSecond": 0
             *             },
             */
                private String  aliasName;//小棉花Top5大腿杯篇：白白橘右京名刀强行续命！空血硬钢四人怒保基地",
                private String  focusImgUrl;
                private String  videoUrlHigh;//http://172.16.146.69:17553/EG/huya/169832881.mp4",
                private String imageBig;//2019/08/06/20190806105958517.jpg",
                private int freeSecond;// 15,
                private int videoId;//": 197058,
                private String imageMid;//": "2019/08/06/20190806105958517.jpg",
                private int number;//": 0,
                private String publicTime;//": "20180101",
                private int isFree;//": "0",
                private String vodId;//": "",
                private String normalImgUrl;//": "",
                private String name;//": "小棉花Top5大腿杯篇：白白橘右京名刀强行续命！空血硬钢四人怒保基地",
                private String imageSmall;//": "2019/08/06/20190806105958517.jpg",
                private String videoUrlFluency;//": "http://172.16.146.69:17553/EG/huya/169832881.mp4",
                private int titleSecond;//": 0

                public String getAliasName() {
                    return aliasName;
                }

                public void setAliasName(String aliasName) {
                    this.aliasName = aliasName;
                }

                public String getFocusImgUrl() {
                    return focusImgUrl;
                }

                public void setFocusImgUrl(String focusImgUrl) {
                    this.focusImgUrl = focusImgUrl;
                }

                public String getVideoUrlHigh() {
                    return videoUrlHigh;
                }

                public void setVideoUrlHigh(String videoUrlHigh) {
                    this.videoUrlHigh = videoUrlHigh;
                }

                public String getImageBig() {
                    return imageBig;
                }

                public void setImageBig(String imageBig) {
                    this.imageBig = imageBig;
                }

                public int getFreeSecond() {
                    return freeSecond;
                }

                public void setFreeSecond(int freeSecond) {
                    this.freeSecond = freeSecond;
                }

                public int getVideoId() {
                    return videoId;
                }

                public void setVideoId(int videoId) {
                    this.videoId = videoId;
                }

                public String getImageMid() {
                    return imageMid;
                }

                public void setImageMid(String imageMid) {
                    this.imageMid = imageMid;
                }

                public int getNumber() {
                    return number;
                }

                public void setNumber(int number) {
                    this.number = number;
                }

                public String getPublicTime() {
                    return publicTime;
                }

                public void setPublicTime(String publicTime) {
                    this.publicTime = publicTime;
                }

                public int getIsFree() {
                    return isFree;
                }

                public void setIsFree(int isFree) {
                    this.isFree = isFree;
                }

                public String getVodId() {
                    return vodId;
                }

                public void setVodId(String vodId) {
                    this.vodId = vodId;
                }

                public String getNormalImgUrl() {
                    return normalImgUrl;
                }

                public void setNormalImgUrl(String normalImgUrl) {
                    this.normalImgUrl = normalImgUrl;
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

                public String getVideoUrlFluency() {
                    return videoUrlFluency;
                }

                public void setVideoUrlFluency(String videoUrlFluency) {
                    this.videoUrlFluency = videoUrlFluency;
                }

                public int getTitleSecond() {
                    return titleSecond;
                }

                public void setTitleSecond(int titleSecond) {
                    this.titleSecond = titleSecond;

            }
        }

    }
}
