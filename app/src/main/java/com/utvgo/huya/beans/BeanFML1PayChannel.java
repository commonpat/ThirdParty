package com.utvgo.huya.beans;

import java.util.List;

/**
 * Created by dev on 2018/3/15.
 */

public class BeanFML1PayChannel {


    /**
     * channel_list : [{"id":"12","name":"123","pay_platform_id":"boss_pay","desc":"1","partner_id":"p1","mode":{"id":"wallet_payment","name":"BOSS支付","currency_type_list":["CNY"]}},{"id":"fdsaf","name":"fdsaf","pay_platform_id":"ocean_pay","desc":"fdsaf","partner_id":"p1","mode":{"id":"quickpay_payment","name":"钱海支付","currency_type_list":["CNY","USD","EUR","GBP","KPW","VND","JPY","LAK","KHR","PHP","MYR","SGD","THP"],"account_info":{"account":"2","terminal":"3","secure_code":"4"}}}]
     * result : {"state":0,"reason":"OK"}
     */

    private ResultBean result;
    private List<ChannelListBean> channel_list;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public List<ChannelListBean> getChannel_list() {
        return channel_list;
    }

    public void setChannel_list(List<ChannelListBean> channel_list) {
        this.channel_list = channel_list;
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

    public static class ChannelListBean {
        /**
         * id : 12
         * name : 123
         * pay_platform_id : boss_pay
         * desc : 1
         * partner_id : p1
         * mode : {"id":"wallet_payment","name":"BOSS支付","currency_type_list":["CNY"]}
         */

        private String id;
        private String name;
        private String pay_platform_id;
        private String desc;
        private String partner_id;
        private ModeBean mode;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPay_platform_id() {
            return pay_platform_id;
        }

        public void setPay_platform_id(String pay_platform_id) {
            this.pay_platform_id = pay_platform_id;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPartner_id() {
            return partner_id;
        }

        public void setPartner_id(String partner_id) {
            this.partner_id = partner_id;
        }

        public ModeBean getMode() {
            return mode;
        }

        public void setMode(ModeBean mode) {
            this.mode = mode;
        }

        public static class ModeBean {
            /**
             * id : wallet_payment
             * name : BOSS支付
             * currency_type_list : ["CNY"]
             */

            private String id;
            private String name;
            private List<String> currency_type_list;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<String> getCurrency_type_list() {
                return currency_type_list;
            }

            public void setCurrency_type_list(List<String> currency_type_list) {
                this.currency_type_list = currency_type_list;
            }
        }
    }
}
