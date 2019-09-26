package com.utvgo.huya.beans;

import java.util.List;

/**
 * Created by dev on 2018/1/17.
 */

public class BeanGuizhouAuthAndPlayUrl{


    /**
     * result : {"order_url":null,"reason":"主产品或依赖产品没授权","new_state":"31","state":"1"}
     * product_list : [{"product_domain":"","auth_state":0,"product_and_list":"","product_from":"","cmboId":"510297","imgUrl":"product/product_29_0.png","nns_product_domain":"","product_or_list":"","pName":"贵州29元/月按月扣费套餐","orderCycle":"M","price":29,"is_free":"2","name":"互动QQ音乐TV版","id":510297,"orderBg":"product/product_29_1.png"}]
     * video : {"index_caption_data":"","metadata":"","drm_list":"","index":{"index_num":0,"id":"5a44bf75163c69283ac2d6a561d172b4","media":""},"cp_id":"10102","id":"5a44bf3938c1f17381a645778a848045","is_support_ncl":"","media_other":""}
     * buy_information : 510297
     * arg_list : {"preview_time":0,"is_support_preview":0,"preview_free_index":"","preview_infos":"","client_ip":"10.69.45.50","coupon_type_ids":"","is_support_coupon":0,"coupon_info":""}
     */

    private ResultBean result;
    private VideoBean video;
    private String buy_information;
    private ArgListBean arg_list;
    private List<ProductListBean> product_list;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public VideoBean getVideo() {
        return video;
    }

    public void setVideo(VideoBean video) {
        this.video = video;
    }

    public String getBuy_information() {
        return buy_information;
    }

    public void setBuy_information(String buy_information) {
        this.buy_information = buy_information;
    }

    public ArgListBean getArg_list() {
        return arg_list;
    }

    public void setArg_list(ArgListBean arg_list) {
        this.arg_list = arg_list;
    }

    public List<ProductListBean> getProduct_list() {
        return product_list;
    }

    public void setProduct_list(List<ProductListBean> product_list) {
        this.product_list = product_list;
    }

    public static class ResultBean {
        /**
         * order_url : null
         * reason : 主产品或依赖产品没授权
         * new_state : 31
         * state : 1
         */

        private Object order_url;
        private String reason;
        private String new_state;
        private String state;

        public Object getOrder_url() {
            return order_url;
        }

        public void setOrder_url(Object order_url) {
            this.order_url = order_url;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getNew_state() {
            return new_state;
        }

        public void setNew_state(String new_state) {
            this.new_state = new_state;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

    public static class VideoBean {
        /**
         * index_caption_data :
         * metadata :
         * drm_list :
         * index : {"index_num":0,"id":"5a44bf75163c69283ac2d6a561d172b4","media":""}
         * cp_id : 10102
         * id : 5a44bf3938c1f17381a645778a848045
         * is_support_ncl :
         * media_other :
         */

        private String index_caption_data;
        private String metadata;
        private String drm_list;
        private IndexBean index;
        private String cp_id;
        private String id;
        private String is_support_ncl;
        private String media_other;

        public String getIndex_caption_data() {
            return index_caption_data;
        }

        public void setIndex_caption_data(String index_caption_data) {
            this.index_caption_data = index_caption_data;
        }

        public String getMetadata() {
            return metadata;
        }

        public void setMetadata(String metadata) {
            this.metadata = metadata;
        }

        public String getDrm_list() {
            return drm_list;
        }

        public void setDrm_list(String drm_list) {
            this.drm_list = drm_list;
        }

        public IndexBean getIndex() {
            return index;
        }

        public void setIndex(IndexBean index) {
            this.index = index;
        }

        public String getCp_id() {
            return cp_id;
        }

        public void setCp_id(String cp_id) {
            this.cp_id = cp_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIs_support_ncl() {
            return is_support_ncl;
        }

        public void setIs_support_ncl(String is_support_ncl) {
            this.is_support_ncl = is_support_ncl;
        }

        public String getMedia_other() {
            return media_other;
        }

        public void setMedia_other(String media_other) {
            this.media_other = media_other;
        }

        public static class IndexBean {
            /**
             * index_num : 0
             * id : 5a44bf75163c69283ac2d6a561d172b4
             * media :
             */

            private int index_num;
            private String id;
            private String media;

            public int getIndex_num() {
                return index_num;
            }

            public void setIndex_num(int index_num) {
                this.index_num = index_num;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMedia() {
                return media;
            }

            public void setMedia(String media) {
                this.media = media;
            }
        }
    }

    public static class ArgListBean {
        /**
         * preview_time : 0
         * is_support_preview : 0
         * preview_free_index :
         * preview_infos :
         * client_ip : 10.69.45.50
         * coupon_type_ids :
         * is_support_coupon : 0
         * coupon_info :
         */

        private int preview_time;
        private int is_support_preview;
        private String preview_free_index;
        private String preview_infos;
        private String client_ip;
        private String coupon_type_ids;
        private int is_support_coupon;
        private String coupon_info;

        public int getPreview_time() {
            return preview_time;
        }

        public void setPreview_time(int preview_time) {
            this.preview_time = preview_time;
        }

        public int getIs_support_preview() {
            return is_support_preview;
        }

        public void setIs_support_preview(int is_support_preview) {
            this.is_support_preview = is_support_preview;
        }

        public String getPreview_free_index() {
            return preview_free_index;
        }

        public void setPreview_free_index(String preview_free_index) {
            this.preview_free_index = preview_free_index;
        }

        public String getPreview_infos() {
            return preview_infos;
        }

        public void setPreview_infos(String preview_infos) {
            this.preview_infos = preview_infos;
        }

        public String getClient_ip() {
            return client_ip;
        }

        public void setClient_ip(String client_ip) {
            this.client_ip = client_ip;
        }

        public String getCoupon_type_ids() {
            return coupon_type_ids;
        }

        public void setCoupon_type_ids(String coupon_type_ids) {
            this.coupon_type_ids = coupon_type_ids;
        }

        public int getIs_support_coupon() {
            return is_support_coupon;
        }

        public void setIs_support_coupon(int is_support_coupon) {
            this.is_support_coupon = is_support_coupon;
        }

        public String getCoupon_info() {
            return coupon_info;
        }

        public void setCoupon_info(String coupon_info) {
            this.coupon_info = coupon_info;
        }
    }

    public static class ProductListBean {
        /**
         * product_domain :
         * auth_state : 0
         * product_and_list :
         * product_from :
         * cmboId : 510297
         * imgUrl : product/product_29_0.png
         * nns_product_domain :
         * product_or_list :
         * pName : 贵州29元/月按月扣费套餐
         * orderCycle : M
         * price : 29.0
         * is_free : 2
         * name : 互动QQ音乐TV版
         * id : 510297
         * orderBg : product/product_29_1.png
         */

        private String product_domain;
        private int auth_state;
        private String product_and_list;
        private String product_from;
        private String cmboId;
        private String imgUrl;
        private String nns_product_domain;
        private String product_or_list;
        private String pName;
        private String orderCycle;
        private double price;
        private String is_free;
        private String name;
        private int id;
        private String orderBg;

        public String getProduct_domain() {
            return product_domain;
        }

        public void setProduct_domain(String product_domain) {
            this.product_domain = product_domain;
        }

        public int getAuth_state() {
            return auth_state;
        }

        public void setAuth_state(int auth_state) {
            this.auth_state = auth_state;
        }

        public String getProduct_and_list() {
            return product_and_list;
        }

        public void setProduct_and_list(String product_and_list) {
            this.product_and_list = product_and_list;
        }

        public String getProduct_from() {
            return product_from;
        }

        public void setProduct_from(String product_from) {
            this.product_from = product_from;
        }

        public String getCmboId() {
            return cmboId;
        }

        public void setCmboId(String cmboId) {
            this.cmboId = cmboId;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getNns_product_domain() {
            return nns_product_domain;
        }

        public void setNns_product_domain(String nns_product_domain) {
            this.nns_product_domain = nns_product_domain;
        }

        public String getProduct_or_list() {
            return product_or_list;
        }

        public void setProduct_or_list(String product_or_list) {
            this.product_or_list = product_or_list;
        }

        public String getPName() {
            return pName;
        }

        public void setPName(String pName) {
            this.pName = pName;
        }

        public String getOrderCycle() {
            return orderCycle;
        }

        public void setOrderCycle(String orderCycle) {
            this.orderCycle = orderCycle;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getIs_free() {
            return is_free;
        }

        public void setIs_free(String is_free) {
            this.is_free = is_free;
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

        public String getOrderBg() {
            return orderBg;
        }

        public void setOrderBg(String orderBg) {
            this.orderBg = orderBg;
        }
    }
}
