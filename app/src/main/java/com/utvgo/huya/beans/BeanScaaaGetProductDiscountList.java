package com.utvgo.huya.beans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dev on 2018/3/19.
 */

public class BeanScaaaGetProductDiscountList {


    /**
     * result : {"state":300000,"sub_state":300707,"reason":"OK"}
     * product_id : 510297
     * discount_list : [{"ruler_id":"5aa9e1c721d6631802007b4dea2641e2","ruler_name_chn":"QQ音乐0.1月包","ruler_validity":{"begin":"2018-03-15 11:00:00","end":"2031-03-15 11:00:00"},"i18n_names":{"default":"QQ音乐0.1月包"},"i18n_short_descriptions":[],"user_source":[],"time_cycle_day":[],"time_cycle_week":[],"need_user_group":"-1","need_purchased_times":"1","need_vip_level":0,"discount":"0.10","discount_type":{"type_name":"fixed_price","type_name_chn":"价格","curreny":"CNY"}}]
     */

    private ResultBean result;
    private String product_id;
    private List<DiscountListBean> discount_list;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public List<DiscountListBean> getDiscount_list() {
        return discount_list;
    }

    public void setDiscount_list(List<DiscountListBean> discount_list) {
        this.discount_list = discount_list;
    }

    public static class ResultBean {
        /**
         * state : 300000
         * sub_state : 300707
         * reason : OK
         */

        private int state;
        private int sub_state;
        private String reason;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getSub_state() {
            return sub_state;
        }

        public void setSub_state(int sub_state) {
            this.sub_state = sub_state;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }

    public static class DiscountListBean {
        /**
         * ruler_id : 5aa9e1c721d6631802007b4dea2641e2
         * ruler_name_chn : QQ音乐0.1月包
         * ruler_validity : {"begin":"2018-03-15 11:00:00","end":"2031-03-15 11:00:00"}
         * i18n_names : {"default":"QQ音乐0.1月包"}
         * i18n_short_descriptions : []
         * user_source : []
         * time_cycle_day : []
         * time_cycle_week : []
         * need_user_group : -1
         * need_purchased_times : 1
         * need_vip_level : 0
         * discount : 0.10
         * discount_type : {"type_name":"fixed_price","type_name_chn":"价格","curreny":"CNY"}
         */

        private String ruler_id;
        private String ruler_name_chn;
        private RulerValidityBean ruler_validity;
        private I18nNamesBean i18n_names;
        private String need_user_group;
        private String need_purchased_times;
        private int need_vip_level;
        private String discount;
        private DiscountTypeBean discount_type;
        private List<?> i18n_short_descriptions;
        private List<?> user_source;
        private List<?> time_cycle_day;
        private List<?> time_cycle_week;

        public String getRuler_id() {
            return ruler_id;
        }

        public void setRuler_id(String ruler_id) {
            this.ruler_id = ruler_id;
        }

        public String getRuler_name_chn() {
            return ruler_name_chn;
        }

        public void setRuler_name_chn(String ruler_name_chn) {
            this.ruler_name_chn = ruler_name_chn;
        }

        public RulerValidityBean getRuler_validity() {
            return ruler_validity;
        }

        public void setRuler_validity(RulerValidityBean ruler_validity) {
            this.ruler_validity = ruler_validity;
        }

        public I18nNamesBean getI18n_names() {
            return i18n_names;
        }

        public void setI18n_names(I18nNamesBean i18n_names) {
            this.i18n_names = i18n_names;
        }

        public String getNeed_user_group() {
            return need_user_group;
        }

        public void setNeed_user_group(String need_user_group) {
            this.need_user_group = need_user_group;
        }

        public String getNeed_purchased_times() {
            return need_purchased_times;
        }

        public void setNeed_purchased_times(String need_purchased_times) {
            this.need_purchased_times = need_purchased_times;
        }

        public int getNeed_vip_level() {
            return need_vip_level;
        }

        public void setNeed_vip_level(int need_vip_level) {
            this.need_vip_level = need_vip_level;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public DiscountTypeBean getDiscount_type() {
            return discount_type;
        }

        public void setDiscount_type(DiscountTypeBean discount_type) {
            this.discount_type = discount_type;
        }

        public List<?> getI18n_short_descriptions() {
            return i18n_short_descriptions;
        }

        public void setI18n_short_descriptions(List<?> i18n_short_descriptions) {
            this.i18n_short_descriptions = i18n_short_descriptions;
        }

        public List<?> getUser_source() {
            return user_source;
        }

        public void setUser_source(List<?> user_source) {
            this.user_source = user_source;
        }

        public List<?> getTime_cycle_day() {
            return time_cycle_day;
        }

        public void setTime_cycle_day(List<?> time_cycle_day) {
            this.time_cycle_day = time_cycle_day;
        }

        public List<?> getTime_cycle_week() {
            return time_cycle_week;
        }

        public void setTime_cycle_week(List<?> time_cycle_week) {
            this.time_cycle_week = time_cycle_week;
        }

        public static class RulerValidityBean {
            /**
             * begin : 2018-03-15 11:00:00
             * end : 2031-03-15 11:00:00
             */

            private String begin;
            private String end;

            public String getBegin() {
                return begin;
            }

            public void setBegin(String begin) {
                this.begin = begin;
            }

            public String getEnd() {
                return end;
            }

            public void setEnd(String end) {
                this.end = end;
            }
        }

        public static class I18nNamesBean {
            /**
             * default : QQ音乐0.1月包
             */

            @SerializedName("default")
            private String defaultX;

            public String getDefaultX() {
                return defaultX;
            }

            public void setDefaultX(String defaultX) {
                this.defaultX = defaultX;
            }
        }

        public static class DiscountTypeBean {
            /**
             * type_name : fixed_price
             * type_name_chn : 价格
             * curreny : CNY
             */

            private String type_name;
            private String type_name_chn;
            private String curreny;

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }

            public String getType_name_chn() {
                return type_name_chn;
            }

            public void setType_name_chn(String type_name_chn) {
                this.type_name_chn = type_name_chn;
            }

            public String getCurreny() {
                return curreny;
            }

            public void setCurreny(String curreny) {
                this.curreny = curreny;
            }
        }
    }
}
