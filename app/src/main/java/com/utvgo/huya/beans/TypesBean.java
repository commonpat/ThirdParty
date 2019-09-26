package com.utvgo.huya.beans;


import java.util.List;

public class TypesBean {

    /**
     * code : 1
     * data : [{"typeName":"全部","typeId":48,"templateId":17},{"typeName":"推荐","typeId":40,"templateId":16},{"typeName":"王者荣耀","typeId":41,"templateId":17},{"typeName":"英雄联盟","typeId":42,"templateId":17},{"typeName":"绝地求生","typeId":43,"templateId":17},{"typeName":"主机游戏","typeId":44,"templateId":17},{"typeName":"热门单机","typeId":45,"templateId":17},{"typeName":"热门手游","typeId":46,"templateId":17},{"typeName":"热门端游","typeId":47,"templateId":17}]
     * message : success
     * {
     *     "code":"1",
     *     "data":{
     *         "navigationBar":Array[4],
     *         "packageInfo":[
     *             {
     *                 "productImgUrl":"",
     *                 "background":"",
     *                 "packageId":31,
     *                 "packageName":"虎牙电竞-安卓-首页"
     *             }
     *         ],
     *         "imageProfix":"http://172.16.146.66:81/cms/uploadFile/image/"
     *     },
     *     "message":"success"
     * }
     */

    private String code;
    private String message;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * typeName : 全部
         * typeId : 48
         * templateId : 17
         *          "navigationBar":Array[4],
         *         "packageInfo":Array[1],
         *         "imageProfix":"http://172.16.146.66:81/cms/uploadFile/image/"
         */
        private List<navigationBarBean> navigationBar;
        private List<packageInfoBean> packageInfo;
        private String imageProfix;

        public List<navigationBarBean> getNavigationBar() {
            return navigationBar;
        }

        public void setNavigationBar(List<navigationBarBean> navigationBar) {
            this.navigationBar = navigationBar;
        }

        public List<packageInfoBean> getPackageInfo() {
            return packageInfo;
        }

        public void setPackageInfo(List<packageInfoBean> packageInfo) {
            this.packageInfo = packageInfo;
        }

        public String getImageProfix() {
            return imageProfix;
        }

        public void setImageProfix(String imageProfix) {
            this.imageProfix = imageProfix;
        }
        public static class navigationBarBean{
            /*
            * "imgUrl":"2019/09/24/20190924101919135.png",
                            "selImgUrl":"2019/09/24/20190924101922552.png",
                            "navigationId":79,
                            "selLeaveImgUrl":"2019/09/24/20190924101935220.png",
                            "hrefType":"0",
                            "href":"index.html",
                            "columnName":"热门推荐"
            * */
            private String imgUrl;
            private String selImgUrl;
            private int navigationId;
            private String selLeaveImgUrl;
            private String hrefType;
            private String href;
            private String columnName;

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getSelImgUrl() {
                return selImgUrl;
            }

            public void setSelImgUrl(String selImgUrl) {
                this.selImgUrl = selImgUrl;
            }

            public int getNavigationId() {
                return navigationId;
            }

            public void setNavigationId(int navigationId) {
                this.navigationId = navigationId;
            }

            public String getSelLeaveImgUrl() {
                return selLeaveImgUrl;
            }

            public void setSelLeaveImgUrl(String selLeaveImgUrl) {
                this.selLeaveImgUrl = selLeaveImgUrl;
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

            public String getColumnName() {
                return columnName;
            }

            public void setColumnName(String columnName) {
                this.columnName = columnName;
            }
        }
        public static class packageInfoBean{
/*
*  "productImgUrl":"",
                "background":"",
                "packageId":31,
                "packageName":"虎牙电竞-安卓-首页"
* */
        private String productImgUrl;
        private int packageId;
        private String packageName;

            public String getProductImgUrl() {
                return productImgUrl;
            }

            public void setProductImgUrl(String productImgUrl) {
                this.productImgUrl = productImgUrl;
            }

            public int getPackageId() {
                return packageId;
            }

            public void setPackageId(int packageId) {
                this.packageId = packageId;
            }

            public String getPackageName() {
                return packageName;
            }

            public void setPackageName(String packageName) {
                this.packageName = packageName;
            }
        }
    }
}
