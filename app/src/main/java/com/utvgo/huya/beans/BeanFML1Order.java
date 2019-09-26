package com.utvgo.huya.beans;

/**
 * Created by dev on 2018/3/15.
 */

public class BeanFML1Order {


    /**
     * pay_order : {"id":"5aaa1f334577b10582421168bde6fc"}
     * buy_order : {"order_id":"5aaa1f33457dc10582421164206358","order_total_price":"0.10","order_discount_price":"0.10","currency_type":"CNY","product_id":"510297"}
     * result : {"state":0,"reason":"OK"}
     */

    private PayOrderBean pay_order;
    private BuyOrderBean buy_order;
    private ResultBean result;

    public PayOrderBean getPay_order() {
        return pay_order;
    }

    public void setPay_order(PayOrderBean pay_order) {
        this.pay_order = pay_order;
    }

    public BuyOrderBean getBuy_order() {
        return buy_order;
    }

    public void setBuy_order(BuyOrderBean buy_order) {
        this.buy_order = buy_order;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class PayOrderBean {
        /**
         * id : 5aaa1f334577b10582421168bde6fc
         */

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class BuyOrderBean {
        /**
         * order_id : 5aaa1f33457dc10582421164206358
         * order_total_price : 0.10
         * order_discount_price : 0.10
         * currency_type : CNY
         * product_id : 510297
         */

        private String order_id;
        private String order_total_price;
        private String order_discount_price;
        private String currency_type;
        private String product_id;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_total_price() {
            return order_total_price;
        }

        public void setOrder_total_price(String order_total_price) {
            this.order_total_price = order_total_price;
        }

        public String getOrder_discount_price() {
            return order_discount_price;
        }

        public void setOrder_discount_price(String order_discount_price) {
            this.order_discount_price = order_discount_price;
        }

        public String getCurrency_type() {
            return currency_type;
        }

        public void setCurrency_type(String currency_type) {
            this.currency_type = currency_type;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }
    }

    public static class ResultBean {
        /**
         * state : 0
         * reason : OK
         */

        private int state;
        private String reason;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}
