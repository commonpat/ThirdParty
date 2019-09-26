package com.utvgo.huya.beans;

import java.util.List;

/**
 * Created by oo on 2017/8/22.
 */

public class BeanAuthorization {


    /**
     * cmboIds : 100002
     * userInfo : {"userId":7,"identityNo":null,"userName":"陈晓梅","catvid":null,"keyNo":"9950000002384049","branchNo":"221","userPsw":null,"servid":"1122336","verifyType":null,"business":null,"status":null,"mobile":"15823031705","createTime":"2017-08-18 15:42:47","email":null,"qq":null,"source":null,"token":"W2niJV2vRwSyFG_Wtq1uyA","regtype":null,"loginFlag":null,"custType":null,"isBindBank":null,"isinarr":null,"version":null,"wxOpenId":null,"customId":null,"accountId":"10122336","totleBalance":"0.0","balance":"0.0","custCode":"300800856567","address":"民生巷20号11-5","parentLocker":null,"oweTotalFee":"5.36","userIntegral":0,"userImg":null,"walletAmount":0,"deviceId":null,"refreshToken":"GDgV915_SMykXlfitlUHkA","tokenExpireDate":"2017-05-25 13:16:55","authorizeList":null}
     * userOrderProductInfo : [{"authId":2,"keyNo":"9950000002384049","cmboId":"100002","status":"0","createTime":"2017-08-18 15:44:17","validDate":"2017-08-18 15:48:03","expireDate":"2017-08-31 23:59:59","productName":"QQ音乐80元/季包按次扣费套餐","price":0}]
     * code : 1
     * isboss : 0
     * productInfoList : [{"id":5,"cmboId":"100004","offerId":"100004","pName":"QQ音乐300元/年包按次扣费套餐","imgUrl":"product/product_300_0.png","price":0,"type":"1","status":0,"orderCycle":"Y","orderBg":"product/product_300_1.png","cycleCount":12},{"id":4,"cmboId":"100003","offerId":"100003","pName":"QQ音乐150元/半年包按次扣费套餐","imgUrl":"product/product_150_0.png","price":0,"type":"1","status":0,"orderCycle":"H","orderBg":"product/product_150_1.png","cycleCount":6},{"id":3,"cmboId":"100002","offerId":"100002","pName":"QQ音乐80元/季包按次扣费套餐","imgUrl":"product/product_80_0.png","price":0,"type":"1","status":0,"orderCycle":"Q","orderBg":"product/product_80_1.png","cycleCount":3},{"id":1,"cmboId":"1428","offerId":"800520160050","pName":"HIFI音乐10元/月(优惠)按月扣费套餐","imgUrl":"product/product_29_0.png","price":10,"type":"0","status":0,"orderCycle":"M","orderBg":"product/product_29_1.png","cycleCount":1}]
     * message : success,没订购产品
     * isbindingDevice : false
     */

    private String cmboIds;
    private UserInfoBean userInfo;
    private int code;
    private int isboss;
    private String message;
    private boolean isbindingDevice;
    private List<UserOrderProductInfoBean> userOrderProductInfo;
    private List<ProductInfoListBean> productInfoList;

    public String getCmboIds() {
        return cmboIds;
    }

    public void setCmboIds(String cmboIds) {
        this.cmboIds = cmboIds;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getIsboss() {
        return isboss;
    }

    public void setIsboss(int isboss) {
        this.isboss = isboss;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isIsbindingDevice() {
        return isbindingDevice;
    }

    public void setIsbindingDevice(boolean isbindingDevice) {
        this.isbindingDevice = isbindingDevice;
    }

    public List<UserOrderProductInfoBean> getUserOrderProductInfo() {
        return userOrderProductInfo;
    }

    public void setUserOrderProductInfo(List<UserOrderProductInfoBean> userOrderProductInfo) {
        this.userOrderProductInfo = userOrderProductInfo;
    }

    public List<ProductInfoListBean> getProductInfoList() {
        return productInfoList;
    }

    public void setProductInfoList(List<ProductInfoListBean> productInfoList) {
        this.productInfoList = productInfoList;
    }

    public static class UserInfoBean {
        /**
         * userId : 7
         * identityNo : null
         * userName : 陈晓梅
         * catvid : null
         * keyNo : 9950000002384049
         * branchNo : 221
         * userPsw : null
         * servid : 1122336
         * verifyType : null
         * business : null
         * status : null
         * mobile : 15823031705
         * createTime : 2017-08-18 15:42:47
         * email : null
         * qq : null
         * source : null
         * token : W2niJV2vRwSyFG_Wtq1uyA
         * regtype : null
         * loginFlag : null
         * custType : null
         * isBindBank : null
         * isinarr : null
         * version : null
         * wxOpenId : null
         * customId : null
         * accountId : 10122336
         * totleBalance : 0.0
         * balance : 0.0
         * custCode : 300800856567
         * address : 民生巷20号11-5
         * parentLocker : null
         * oweTotalFee : 5.36
         * userIntegral : 0
         * userImg : null
         * walletAmount : 0.0
         * deviceId : null
         * refreshToken : GDgV915_SMykXlfitlUHkA
         * tokenExpireDate : 2017-05-25 13:16:55
         * authorizeList : null
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
        private String token;
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
        private double walletAmount;
        private Object deviceId;
        private String refreshToken;
        private String tokenExpireDate;
        private Object authorizeList;

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

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
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

        public double getWalletAmount() {
            return walletAmount;
        }

        public void setWalletAmount(double walletAmount) {
            this.walletAmount = walletAmount;
        }

        public Object getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(Object deviceId) {
            this.deviceId = deviceId;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

        public String getTokenExpireDate() {
            return tokenExpireDate;
        }

        public void setTokenExpireDate(String tokenExpireDate) {
            this.tokenExpireDate = tokenExpireDate;
        }

        public Object getAuthorizeList() {
            return authorizeList;
        }

        public void setAuthorizeList(Object authorizeList) {
            this.authorizeList = authorizeList;
        }
    }

    public static class UserOrderProductInfoBean {
        /**
         * authId : 2
         * keyNo : 9950000002384049
         * cmboId : 100002
         * status : 0
         * createTime : 2017-08-18 15:44:17
         * validDate : 2017-08-18 15:48:03
         * expireDate : 2017-08-31 23:59:59
         * productName : QQ音乐80元/季包按次扣费套餐
         * price : 0.0
         */

        private int authId;
        private String keyNo;
        private String cmboId;
        private String status;
        private String createTime;
        private String validDate;
        private String expireDate;
        private String productName;
        private double price;

        public int getAuthId() {
            return authId;
        }

        public void setAuthId(int authId) {
            this.authId = authId;
        }

        public String getKeyNo() {
            return keyNo;
        }

        public void setKeyNo(String keyNo) {
            this.keyNo = keyNo;
        }

        public String getCmboId() {
            return cmboId;
        }

        public void setCmboId(String cmboId) {
            this.cmboId = cmboId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getValidDate() {
            return validDate;
        }

        public void setValidDate(String validDate) {
            this.validDate = validDate;
        }

        public String getExpireDate() {
            return expireDate;
        }

        public void setExpireDate(String expireDate) {
            this.expireDate = expireDate;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }

    public static class ProductInfoListBean {
        /**
         * id : 5
         * cmboId : 100004
         * offerId : 100004
         * pName : QQ音乐300元/年包按次扣费套餐
         * imgUrl : product/product_300_0.png
         * price : 0.0
         * type : 1
         * status : 0
         * orderCycle : Y
         * orderBg : product/product_300_1.png
         * cycleCount : 12
         */

        private int id;
        private String cmboId;
        private String offerId;
        private String pName;
        private String imgUrl;
        private double price;
        private String type;
        private int status;
        private String orderCycle;
        private String orderBg;
        private int cycleCount;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCmboId() {
            return cmboId;
        }

        public void setCmboId(String cmboId) {
            this.cmboId = cmboId;
        }

        public String getOfferId() {
            return offerId;
        }

        public void setOfferId(String offerId) {
            this.offerId = offerId;
        }

        public String getPName() {
            return pName;
        }

        public void setPName(String pName) {
            this.pName = pName;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getOrderCycle() {
            return orderCycle;
        }

        public void setOrderCycle(String orderCycle) {
            this.orderCycle = orderCycle;
        }

        public String getOrderBg() {
            return orderBg;
        }

        public void setOrderBg(String orderBg) {
            this.orderBg = orderBg;
        }

        public int getCycleCount() {
            return cycleCount;
        }

        public void setCycleCount(int cycleCount) {
            this.cycleCount = cycleCount;
        }
    }
}
