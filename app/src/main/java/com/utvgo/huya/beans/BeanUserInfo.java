package com.utvgo.huya.beans;

import java.util.List;

/**
 * Created by haha on 2017/7/23.
 */

public class BeanUserInfo {

    /**
     * status : 1
     * errors : []
     * result : {"msg":"","oweTotalFee":298.62,"userImg":"-1","totalBalance":0,"custId":"7784881","orderList":[],"generalBalance":0}
     * extra : {"userInfo":{"userId":24,"identityNo":null,"userName":"增值业务中心","catvid":null,"keyNo":"9950000002740494","branchNo":"278","userPsw":null,"servid":"7784881","verifyType":null,"business":null,"status":null,"mobile":"15823022208","createTime":"2017-12-20 14:13:55","email":null,"qq":null,"source":null,"token":null,"regtype":null,"loginFlag":null,"custType":null,"isBindBank":null,"isinarr":null,"version":null,"wxOpenId":null,"customId":null,"accountId":"54233256","totleBalance":"0.0","balance":"0.0","custCode":"","address":"渝北区_北部龙山大道333号广电大厦13楼增值业务中心","parentLocker":null,"oweTotalFee":"298.62","userIntegral":0,"userImg":null,"walletAmount":null,"deviceId":null,"refreshToken":null,"tokenExpireDate":null,"generalBalance":"0.0","authorizeList":[]}}
     * cmboIds : null
     */

    private int status;
    private ResultBean result;
    private ExtraBean extra;
    private Object cmboIds;
    private List<?> errors;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
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

    public static class ResultBean {
        /**
         * msg :
         * oweTotalFee : 298.62
         * userImg : -1
         * totalBalance : 0.0
         * custId : 7784881
         * orderList : []
         * generalBalance : 0.0
         */

        private String msg;
        private double oweTotalFee;
        private String userImg;
        private double totalBalance;
        private String custId;
        private double generalBalance;
        private List<?> orderList;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public double getOweTotalFee() {
            return oweTotalFee;
        }

        public void setOweTotalFee(double oweTotalFee) {
            this.oweTotalFee = oweTotalFee;
        }

        public String getUserImg() {
            return userImg;
        }

        public void setUserImg(String userImg) {
            this.userImg = userImg;
        }

        public double getTotalBalance() {
            return totalBalance;
        }

        public void setTotalBalance(double totalBalance) {
            this.totalBalance = totalBalance;
        }

        public String getCustId() {
            return custId;
        }

        public void setCustId(String custId) {
            this.custId = custId;
        }

        public double getGeneralBalance() {
            return generalBalance;
        }

        public void setGeneralBalance(double generalBalance) {
            this.generalBalance = generalBalance;
        }

        public List<?> getOrderList() {
            return orderList;
        }

        public void setOrderList(List<?> orderList) {
            this.orderList = orderList;
        }
    }

    public static class ExtraBean {
        /**
         * userInfo : {"userId":24,"identityNo":null,"userName":"增值业务中心","catvid":null,"keyNo":"9950000002740494","branchNo":"278","userPsw":null,"servid":"7784881","verifyType":null,"business":null,"status":null,"mobile":"15823022208","createTime":"2017-12-20 14:13:55","email":null,"qq":null,"source":null,"token":null,"regtype":null,"loginFlag":null,"custType":null,"isBindBank":null,"isinarr":null,"version":null,"wxOpenId":null,"customId":null,"accountId":"54233256","totleBalance":"0.0","balance":"0.0","custCode":"","address":"渝北区_北部龙山大道333号广电大厦13楼增值业务中心","parentLocker":null,"oweTotalFee":"298.62","userIntegral":0,"userImg":null,"walletAmount":null,"deviceId":null,"refreshToken":null,"tokenExpireDate":null,"generalBalance":"0.0","authorizeList":[]}
         */

        private UserInfoBean userInfo;

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public static class UserInfoBean {
            /**
             * userId : 24
             * identityNo : null
             * userName : 增值业务中心
             * catvid : null
             * keyNo : 9950000002740494
             * branchNo : 278
             * userPsw : null
             * servid : 7784881
             * verifyType : null
             * business : null
             * status : null
             * mobile : 15823022208
             * createTime : 2017-12-20 14:13:55
             * email : null
             * qq : null
             * source : null
             * token : null
             * regtype : null
             * loginFlag : null
             * custType : null
             * isBindBank : null
             * isinarr : null
             * version : null
             * wxOpenId : null
             * customId : null
             * accountId : 54233256
             * totleBalance : 0.0
             * balance : 0.0
             * custCode :
             * address : 渝北区_北部龙山大道333号广电大厦13楼增值业务中心
             * parentLocker : null
             * oweTotalFee : 298.62
             * userIntegral : 0
             * userImg : null
             * walletAmount : null
             * deviceId : null
             * refreshToken : null
             * tokenExpireDate : null
             * generalBalance : 0.0
             * authorizeList : []
             */

            private int userId;
            private Object identityNo;
            private String userName;
            private Object catvid;
            private String keyNo;
            private String branchNo;
            private Object userPsw;
            private String servid;
            private Object verifyType;
            private Object business;
            private Object status;
            private String mobile;
            private String createTime;
            private Object email;
            private Object qq;
            private Object source;
            private Object token;
            private Object regtype;
            private Object loginFlag;
            private Object custType;
            private Object isBindBank;
            private Object isinarr;
            private Object version;
            private Object wxOpenId;
            private Object customId;
            private String accountId;
            private String totleBalance;
            private String balance;
            private String custCode;
            private String address;
            private Object parentLocker;
            private String oweTotalFee;
            private int userIntegral;
            private Object userImg;
            private Object walletAmount;
            private Object deviceId;
            private Object refreshToken;
            private Object tokenExpireDate;
            private String generalBalance;
            private List<?> authorizeList;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public Object getIdentityNo() {
                return identityNo;
            }

            public void setIdentityNo(Object identityNo) {
                this.identityNo = identityNo;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public Object getCatvid() {
                return catvid;
            }

            public void setCatvid(Object catvid) {
                this.catvid = catvid;
            }

            public String getKeyNo() {
                return keyNo;
            }

            public void setKeyNo(String keyNo) {
                this.keyNo = keyNo;
            }

            public String getBranchNo() {
                return branchNo;
            }

            public void setBranchNo(String branchNo) {
                this.branchNo = branchNo;
            }

            public Object getUserPsw() {
                return userPsw;
            }

            public void setUserPsw(Object userPsw) {
                this.userPsw = userPsw;
            }

            public String getServid() {
                return servid;
            }

            public void setServid(String servid) {
                this.servid = servid;
            }

            public Object getVerifyType() {
                return verifyType;
            }

            public void setVerifyType(Object verifyType) {
                this.verifyType = verifyType;
            }

            public Object getBusiness() {
                return business;
            }

            public void setBusiness(Object business) {
                this.business = business;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public Object getQq() {
                return qq;
            }

            public void setQq(Object qq) {
                this.qq = qq;
            }

            public Object getSource() {
                return source;
            }

            public void setSource(Object source) {
                this.source = source;
            }

            public Object getToken() {
                return token;
            }

            public void setToken(Object token) {
                this.token = token;
            }

            public Object getRegtype() {
                return regtype;
            }

            public void setRegtype(Object regtype) {
                this.regtype = regtype;
            }

            public Object getLoginFlag() {
                return loginFlag;
            }

            public void setLoginFlag(Object loginFlag) {
                this.loginFlag = loginFlag;
            }

            public Object getCustType() {
                return custType;
            }

            public void setCustType(Object custType) {
                this.custType = custType;
            }

            public Object getIsBindBank() {
                return isBindBank;
            }

            public void setIsBindBank(Object isBindBank) {
                this.isBindBank = isBindBank;
            }

            public Object getIsinarr() {
                return isinarr;
            }

            public void setIsinarr(Object isinarr) {
                this.isinarr = isinarr;
            }

            public Object getVersion() {
                return version;
            }

            public void setVersion(Object version) {
                this.version = version;
            }

            public Object getWxOpenId() {
                return wxOpenId;
            }

            public void setWxOpenId(Object wxOpenId) {
                this.wxOpenId = wxOpenId;
            }

            public Object getCustomId() {
                return customId;
            }

            public void setCustomId(Object customId) {
                this.customId = customId;
            }

            public String getAccountId() {
                return accountId;
            }

            public void setAccountId(String accountId) {
                this.accountId = accountId;
            }

            public String getTotleBalance() {
                return totleBalance;
            }

            public void setTotleBalance(String totleBalance) {
                this.totleBalance = totleBalance;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getCustCode() {
                return custCode;
            }

            public void setCustCode(String custCode) {
                this.custCode = custCode;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public Object getParentLocker() {
                return parentLocker;
            }

            public void setParentLocker(Object parentLocker) {
                this.parentLocker = parentLocker;
            }

            public String getOweTotalFee() {
                return oweTotalFee;
            }

            public void setOweTotalFee(String oweTotalFee) {
                this.oweTotalFee = oweTotalFee;
            }

            public int getUserIntegral() {
                return userIntegral;
            }

            public void setUserIntegral(int userIntegral) {
                this.userIntegral = userIntegral;
            }

            public Object getUserImg() {
                return userImg;
            }

            public void setUserImg(Object userImg) {
                this.userImg = userImg;
            }

            public Object getWalletAmount() {
                return walletAmount;
            }

            public void setWalletAmount(Object walletAmount) {
                this.walletAmount = walletAmount;
            }

            public Object getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(Object deviceId) {
                this.deviceId = deviceId;
            }

            public Object getRefreshToken() {
                return refreshToken;
            }

            public void setRefreshToken(Object refreshToken) {
                this.refreshToken = refreshToken;
            }

            public Object getTokenExpireDate() {
                return tokenExpireDate;
            }

            public void setTokenExpireDate(Object tokenExpireDate) {
                this.tokenExpireDate = tokenExpireDate;
            }

            public String getGeneralBalance() {
                return generalBalance;
            }

            public void setGeneralBalance(String generalBalance) {
                this.generalBalance = generalBalance;
            }

            public List<?> getAuthorizeList() {
                return authorizeList;
            }

            public void setAuthorizeList(List<?> authorizeList) {
                this.authorizeList = authorizeList;
            }
        }
    }
}
