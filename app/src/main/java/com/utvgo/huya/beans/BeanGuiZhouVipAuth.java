package com.utvgo.huya.beans;

import android.os.Parcel;

import java.io.Serializable;
import java.util.List;

public class BeanGuiZhouVipAuth implements Serializable {


    /**
     * status : 1
     * errors : []
     * limit : {"limitStatus":"0"}
     * result : [{"mainBossServiceId":"SJH00023","mainImageAddress":"2017/09/11/qqmusic.png","mainProductId":29,"mainServiceName":"QQ音乐","mainVipImageAddress":null,"mainBossSpId":"99900038","mainFee":30,"mainServiceCode":"vip_code_29"},{"mainBossServiceId":"SJH00025-A001","mainImageAddress":"2017/09/11/qqmusic.png","mainProductId":43,"mainServiceName":"QQ音乐(付费新专辑)(15元)(正式)","mainVipImageAddress":null,"mainBossSpId":"99900038","mainFee":15,"mainServiceCode":"vip_code_107"}]
     * extra : {"custtype":"0","isbindbank":"Y","msg":"已开通此vip包，keyNo=8002003224274725,supplierId=","myOrderVipCode":"vip_code_29","myOrderVipName":"QQ音乐","vipCode":"vip_code_29"}
     * cmboIds : null
     */

    private int status;
    private LimitBean limit;
    private ExtraBean extra;
    private Object cmboIds;
    private List<?> errors;
    private List<ResultBean> result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LimitBean getLimit() {
        return limit;
    }

    public void setLimit(LimitBean limit) {
        this.limit = limit;
    }

    public ExtraBean getExtra() {
        return extra;
    }

    public void setExtra(ExtraBean extra) {
        this.extra = extra;
    }

    public Object getCmboIds() {
        return cmboIds;
    }

    public void setCmboIds(Object cmboIds) {
        this.cmboIds = cmboIds;
    }

    public List<?> getErrors() {
        return errors;
    }

    public void setErrors(List<?> errors) {
        this.errors = errors;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class LimitBean implements Serializable {
        /**
         * limitStatus : 0
         */

        private String limitStatus;

        public String getLimitStatus() {
            return limitStatus;
        }

        public void setLimitStatus(String limitStatus) {
            this.limitStatus = limitStatus;
        }
    }

    public static class ExtraBean implements Serializable {
        /**
         * custtype : 0
         * isbindbank : Y
         * msg : 已开通此vip包，keyNo=8002003224274725,supplierId=
         * myOrderVipCode : vip_code_29
         * myOrderVipName : QQ音乐
         * vipCode : vip_code_29
         */

        private String custtype;
        private String isbindbank;
        private String msg;
        private String myOrderVipCode;
        private String myOrderVipName;
        private String vipCode;

        public String getCusttype() {
            return custtype;
        }

        public void setCusttype(String custtype) {
            this.custtype = custtype;
        }

        public String getIsbindbank() {
            return isbindbank;
        }

        public void setIsbindbank(String isbindbank) {
            this.isbindbank = isbindbank;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getMyOrderVipCode() {
            return myOrderVipCode;
        }

        public void setMyOrderVipCode(String myOrderVipCode) {
            this.myOrderVipCode = myOrderVipCode;
        }

        public String getMyOrderVipName() {
            return myOrderVipName;
        }

        public void setMyOrderVipName(String myOrderVipName) {
            this.myOrderVipName = myOrderVipName;
        }

        public String getVipCode() {
            return vipCode;
        }

        public void setVipCode(String vipCode) {
            this.vipCode = vipCode;
        }
    }

    public static class ResultBean implements Serializable {
        /**
         * mainBossServiceId : SJH00023
         * mainImageAddress : 2017/09/11/qqmusic.png
         * mainProductId : 29
         * mainServiceName : QQ音乐
         * mainVipImageAddress : null
         * mainBossSpId : 99900038
         * mainFee : 30
         * mainServiceCode : vip_code_29
         */

        private String mainBossServiceId;
        private String mainImageAddress;
        private int mainProductId;
        private String mainServiceName;
        private String mainVipImageAddress;
        private String mainBossSpId;
        private int mainFee;
        private String mainServiceCode;

        public String getMainBossServiceId() {
            return mainBossServiceId;
        }

        public void setMainBossServiceId(String mainBossServiceId) {
            this.mainBossServiceId = mainBossServiceId;
        }

        public String getMainImageAddress() {
            return mainImageAddress;
        }

        public void setMainImageAddress(String mainImageAddress) {
            this.mainImageAddress = mainImageAddress;
        }

        public int getMainProductId() {
            return mainProductId;
        }

        public void setMainProductId(int mainProductId) {
            this.mainProductId = mainProductId;
        }

        public String getMainServiceName() {
            return mainServiceName;
        }

        public void setMainServiceName(String mainServiceName) {
            this.mainServiceName = mainServiceName;
        }

        public String getMainVipImageAddress() {
            return mainVipImageAddress;
        }

        public void setMainVipImageAddress(String mainVipImageAddress) {
            this.mainVipImageAddress = mainVipImageAddress;
        }

        public String getMainBossSpId() {
            return mainBossSpId;
        }

        public void setMainBossSpId(String mainBossSpId) {
            this.mainBossSpId = mainBossSpId;
        }

        public int getMainFee() {
            return mainFee;
        }

        public void setMainFee(int mainFee) {
            this.mainFee = mainFee;
        }

        public String getMainServiceCode() {
            return mainServiceCode;
        }

        public void setMainServiceCode(String mainServiceCode) {
            this.mainServiceCode = mainServiceCode;
        }

        public ResultBean() {
        }

        protected ResultBean(Parcel in) {
            this.mainBossServiceId = in.readString();
            this.mainImageAddress = in.readString();
            this.mainProductId = in.readInt();
            this.mainServiceName = in.readString();
            this.mainVipImageAddress = in.readString();
            this.mainBossSpId = in.readString();
            this.mainFee = in.readInt();
            this.mainServiceCode = in.readString();
        }
    }
}
