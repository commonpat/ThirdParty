package com.utvgo.handsome.bean;

/**
 * Created by Administrator on 2019/6/14.
 */

public class HNTVOrderData {

    /**
     * code : 1
     * data : {"clientpwd":"1C6BC2EA76486F729B826A970A4D56B7","clienttype":"01","clientcode":"109999060","servicecode":"PROD_ORDER","dataSign":"F769338F7C0B0F845127F77C22779D03","requestid":"10999906020190613a2b9fc7f","version":"V2APP","requestContent":"%7B%22infmod%22%3A%221%22%2C%22isOrder%22%3A%22Y%22%2C%22noticeAction%22%3A%22http%3A%2F%2F100.125.9.4%2Fcq-order-web%2Fhunan%2FhnUserController%2FnoticeAction.utvgo%22%2C%22orderNo%22%3A%2210999906020190613f0df95d0%22%2C%22orderparamstr%22%3A%5B%7B%22orderTotalAmt%22%3A%22100.0%22%2C%22proNo%22%3A%2223186301%22%2C%22typeNo%22%3A%228731204033541662%22%7D%5D%2C%22payments%22%3A%22%22%2C%22productInfos%22%3A%5B%7B%22assistinfo%22%3A%22%22%2C%22branchNO%22%3A%22%22%2C%22count%22%3A%221%22%2C%22fee%22%3A100%2C%22keyno%22%3A%228731204033541662%22%2C%22pcode%22%3A%22311192%22%2C%22productName%22%3A%22QQ%E9%9F%B3%E4%B9%90%22%7D%5D%2C%22redirectUrl%22%3A%22http%3A%2F%2F100.125.9.4%2F%22%2C%22ticketstr%22%3A%22%22%2C%22totalFee%22%3A100%7D"}
     * message : success
     */

    private String code;
    private DataBean data;
    private String message;
    private String orderUrl;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderUrl() {
        return orderUrl;
    }

    public void setOrderUrl(String orderUrl) {
        this.orderUrl = orderUrl;
    }

    public static class DataBean {
        /**
         * clientpwd : 1C6BC2EA76486F729B826A970A4D56B7
         * clienttype : 01
         * clientcode : 109999060
         * servicecode : PROD_ORDER
         * dataSign : F769338F7C0B0F845127F77C22779D03
         * requestid : 10999906020190613a2b9fc7f
         * version : V2APP
         * requestContent : %7B%22infmod%22%3A%221%22%2C%22isOrder%22%3A%22Y%22%2C%22noticeAction%22%3A%22http%3A%2F%2F100.125.9.4%2Fcq-order-web%2Fhunan%2FhnUserController%2FnoticeAction.utvgo%22%2C%22orderNo%22%3A%2210999906020190613f0df95d0%22%2C%22orderparamstr%22%3A%5B%7B%22orderTotalAmt%22%3A%22100.0%22%2C%22proNo%22%3A%2223186301%22%2C%22typeNo%22%3A%228731204033541662%22%7D%5D%2C%22payments%22%3A%22%22%2C%22productInfos%22%3A%5B%7B%22assistinfo%22%3A%22%22%2C%22branchNO%22%3A%22%22%2C%22count%22%3A%221%22%2C%22fee%22%3A100%2C%22keyno%22%3A%228731204033541662%22%2C%22pcode%22%3A%22311192%22%2C%22productName%22%3A%22QQ%E9%9F%B3%E4%B9%90%22%7D%5D%2C%22redirectUrl%22%3A%22http%3A%2F%2F100.125.9.4%2F%22%2C%22ticketstr%22%3A%22%22%2C%22totalFee%22%3A100%7D
         */

        private String clientpwd;
        private String clienttype;
        private String clientcode;
        private String servicecode;
        private String dataSign;
        private String requestid;
        private String version;
        private String requestContent;

        public String getClientpwd() {
            return clientpwd;
        }

        public void setClientpwd(String clientpwd) {
            this.clientpwd = clientpwd;
        }

        public String getClienttype() {
            return clienttype;
        }

        public void setClienttype(String clienttype) {
            this.clienttype = clienttype;
        }

        public String getClientcode() {
            return clientcode;
        }

        public void setClientcode(String clientcode) {
            this.clientcode = clientcode;
        }

        public String getServicecode() {
            return servicecode;
        }

        public void setServicecode(String servicecode) {
            this.servicecode = servicecode;
        }

        public String getDataSign() {
            return dataSign;
        }

        public void setDataSign(String dataSign) {
            this.dataSign = dataSign;
        }

        public String getRequestid() {
            return requestid;
        }

        public void setRequestid(String requestid) {
            this.requestid = requestid;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getRequestContent() {
            return requestContent;
        }

        public void setRequestContent(String requestContent) {
            this.requestContent = requestContent;
        }
    }
}
