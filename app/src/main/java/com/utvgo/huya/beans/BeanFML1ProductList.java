package com.utvgo.huya.beans;

import java.util.List;

/**
 * Created by dev on 2018/3/19.
 */

public class BeanFML1ProductList {


    /**
     * result : {"state":300000,"sub_state":300001,"reason":"OK"}
     * product : [{"product_id":"510297","product_name":"互动QQ音乐TV版","product_type":"cycle","product_fee_id":"5a60577f36c214a894876caa4e0bf4f5","product_fee_name":"QQ音乐","price":10,"unit":"month","currency_type":"CNY","favor_price":10,"amount":"1","price_unit":"month","exp_begin_time":"2018-01-18 00:00:00","exp_end_time":"2042-01-18 00:00:00","is_support_preview":"0","preview_time":"0","preview_infos":",","is_vip_product":"0","present_vip_level":"0","whether_preview":"0","whether_renew":"1","whether_to_buy":"1","whether_free":"2","whether_relation_time_sync":"0","cp_id":"10102"}]
     */

    private ResultBean result;
    private List<ProductBean> product;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public List<ProductBean> getProduct() {
        return product;
    }

    public void setProduct(List<ProductBean> product) {
        this.product = product;
    }

    public static class ResultBean {
        /**
         * state : 300000
         * sub_state : 300001
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

    public static class ProductBean {
        /**
         * product_id : 510297
         * product_name : 互动QQ音乐TV版
         * product_type : cycle
         * product_fee_id : 5a60577f36c214a894876caa4e0bf4f5
         * product_fee_name : QQ音乐
         * price : 10
         * unit : month
         * currency_type : CNY
         * favor_price : 10
         * amount : 1
         * price_unit : month
         * exp_begin_time : 2018-01-18 00:00:00
         * exp_end_time : 2042-01-18 00:00:00
         * is_support_preview : 0
         * preview_time : 0
         * preview_infos : ,
         * is_vip_product : 0
         * present_vip_level : 0
         * whether_preview : 0
         * whether_renew : 1
         * whether_to_buy : 1
         * whether_free : 2
         * whether_relation_time_sync : 0
         * cp_id : 10102
         */

        private String product_id;
        private String product_name;
        private String product_type;
        private String product_fee_id;
        private String product_fee_name;
        private int price;
        private String unit;
        private String currency_type;
        private int favor_price;
        private String amount;
        private String price_unit;
        private String exp_begin_time;
        private String exp_end_time;
        private String is_support_preview;
        private String preview_time;
        private String preview_infos;
        private String is_vip_product;
        private String present_vip_level;
        private String whether_preview;
        private String whether_renew;
        private String whether_to_buy;
        private String whether_free;
        private String whether_relation_time_sync;
        private String cp_id;

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getProduct_type() {
            return product_type;
        }

        public void setProduct_type(String product_type) {
            this.product_type = product_type;
        }

        public String getProduct_fee_id() {
            return product_fee_id;
        }

        public void setProduct_fee_id(String product_fee_id) {
            this.product_fee_id = product_fee_id;
        }

        public String getProduct_fee_name() {
            return product_fee_name;
        }

        public void setProduct_fee_name(String product_fee_name) {
            this.product_fee_name = product_fee_name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getCurrency_type() {
            return currency_type;
        }

        public void setCurrency_type(String currency_type) {
            this.currency_type = currency_type;
        }

        public int getFavor_price() {
            return favor_price;
        }

        public void setFavor_price(int favor_price) {
            this.favor_price = favor_price;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getPrice_unit() {
            return price_unit;
        }

        public void setPrice_unit(String price_unit) {
            this.price_unit = price_unit;
        }

        public String getExp_begin_time() {
            return exp_begin_time;
        }

        public void setExp_begin_time(String exp_begin_time) {
            this.exp_begin_time = exp_begin_time;
        }

        public String getExp_end_time() {
            return exp_end_time;
        }

        public void setExp_end_time(String exp_end_time) {
            this.exp_end_time = exp_end_time;
        }

        public String getIs_support_preview() {
            return is_support_preview;
        }

        public void setIs_support_preview(String is_support_preview) {
            this.is_support_preview = is_support_preview;
        }

        public String getPreview_time() {
            return preview_time;
        }

        public void setPreview_time(String preview_time) {
            this.preview_time = preview_time;
        }

        public String getPreview_infos() {
            return preview_infos;
        }

        public void setPreview_infos(String preview_infos) {
            this.preview_infos = preview_infos;
        }

        public String getIs_vip_product() {
            return is_vip_product;
        }

        public void setIs_vip_product(String is_vip_product) {
            this.is_vip_product = is_vip_product;
        }

        public String getPresent_vip_level() {
            return present_vip_level;
        }

        public void setPresent_vip_level(String present_vip_level) {
            this.present_vip_level = present_vip_level;
        }

        public String getWhether_preview() {
            return whether_preview;
        }

        public void setWhether_preview(String whether_preview) {
            this.whether_preview = whether_preview;
        }

        public String getWhether_renew() {
            return whether_renew;
        }

        public void setWhether_renew(String whether_renew) {
            this.whether_renew = whether_renew;
        }

        public String getWhether_to_buy() {
            return whether_to_buy;
        }

        public void setWhether_to_buy(String whether_to_buy) {
            this.whether_to_buy = whether_to_buy;
        }

        public String getWhether_free() {
            return whether_free;
        }

        public void setWhether_free(String whether_free) {
            this.whether_free = whether_free;
        }

        public String getWhether_relation_time_sync() {
            return whether_relation_time_sync;
        }

        public void setWhether_relation_time_sync(String whether_relation_time_sync) {
            this.whether_relation_time_sync = whether_relation_time_sync;
        }

        public String getCp_id() {
            return cp_id;
        }

        public void setCp_id(String cp_id) {
            this.cp_id = cp_id;
        }
    }
}
