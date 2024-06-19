package com.utvgo.handsome.bean;

public class TOPWAYOrderRespone {

        private Pay_order pay_order;
        private Buy_order buy_order;
        private Result result;
        public void setPay_order(Pay_order pay_order) {
            this.pay_order = pay_order;
        }
        public Pay_order getPay_order() {
            return pay_order;
        }

        public void setBuy_order(Buy_order buy_order) {
            this.buy_order = buy_order;
        }
        public Buy_order getBuy_order() {
            return buy_order;
        }

        public void setResult(Result result) {
            this.result = result;
        }
        public Result getResult() {
            return result;
        }
    public class Pay_order {

        private String id;
        private String name;
        public void setId(String id) {
            this.id = id;
        }
        public String getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

    }

    public class Buy_order {

        private String order_id;
        private String order_name;
        private String order_total_price;
        private String currency_type;
        private String product_id;
        private String product_fee_id;
        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }
        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_name(String order_name) {
            this.order_name = order_name;
        }
        public String getOrder_name() {
            return order_name;
        }

        public void setOrder_total_price(String order_total_price) {
            this.order_total_price = order_total_price;
        }
        public String getOrder_total_price() {
            return order_total_price;
        }

        public void setCurrency_type(String currency_type) {
            this.currency_type = currency_type;
        }
        public String getCurrency_type() {
            return currency_type;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }
        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_fee_id(String product_fee_id) {
            this.product_fee_id = product_fee_id;
        }
        public String getProduct_fee_id() {
            return product_fee_id;
        }

    }
    public class Result {

        private String state;
        private String reason;
        public void setState(String state) {
            this.state = state;
        }
        public String getState() {
            return state;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
        public String getReason() {
            return reason;
        }

    }
}
