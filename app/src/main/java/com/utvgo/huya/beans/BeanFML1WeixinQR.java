package com.utvgo.huya.beans;

/**
 * Created by dev on 2018/3/15.
 */

public class BeanFML1WeixinQR {


    /**
     * qrcode_url : http://10.21.4.195:8585/payweb/paytv/pay-box!doPay?version=1&clientcode=1022&citycode=5201&clientpwd=6cb2d0b736f3841658074f9b854e8c54&servicecode=Pay&requestid=10222018031551763285&requestContent={"orderInfo":{"orderNo":"5aaa25ecad0f9105824211471f75c1","productList":[{"keyno":null,"productName":"%25E4%25BA%2592%25E5%258A%25A8QQ%25E9%259F%25B3%25E4%25B9%2590TV%25E7%2589%2588","count":"1","fee":"0.10"}]},"custInfo":{"custname":"%25E8%25B4%25B5%25E5%25B7%259E%25E9%2580%259A%25E6%25B1%2587%25E9%2585%2592%25E5%25BA%2597%25E7%25AE%25A1%25E7%2590%2586%25E6%259C%2589%25E9%2599%2590%25E5%2585%25AC%25E5%258F%25B8","custid":"B10000826466","servid":"","gdNo":"","mac":"","cardNo":"","area":"5201","city":"5201"},"totalFee":"0.10","payments":"","bankacct":"","feeCode":"","redirectUrl":"http:\/\/10.21.16.60\/nn_cms\/api\/gzgd\/k13\/bosspay\/boss_pay_result.php","noticeAction":"http:\/\/10.21.16.60\/nn_cms\/api\/gzgd\/k13\/bosspay\/notify_boss_pay.php","orderNum":"5aaa25ecad0f9105824211471f75c1","flag":"1"}&
     * result : {"state":0,"reason":"OK"}
     */

    private String qrcode_url;
    private ResultBean result;

    public String getQrcode_url() {
        return qrcode_url;
    }

    public void setQrcode_url(String qrcode_url) {
        this.qrcode_url = qrcode_url;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
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
