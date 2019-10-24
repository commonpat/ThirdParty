package com.utvgo.handsome.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/6/13.
 */

public class HNTVAuthData {

    /**
     * cmboIds :
     * msg :
     * userInfo : {"userId":14,"identityNo":null,"userName":null,"catvid":null,"keyNo":"8731204033541662","branchNo":null,"userPsw":null,"servid":"","verifyType":null,"business":null,"status":null,"mobile":null,"createTime":"2019-06-12 15:19:20","email":null,"qq":null,"source":null,"token":"ZdAx67vBDN2vsDpw6Oq6kg==","regtype":null,"loginFlag":null,"custType":null,"isBindBank":null,"isinarr":null,"version":null,"wxOpenId":null,"customId":null,"accountId":null,"totleBalance":"0","balance":"0","custCode":null,"address":null,"parentLocker":null,"oweTotalFee":"0","userIntegral":0,"userImg":null,"walletAmount":null,"deviceId":null,"refreshToken":null,"tokenExpireDate":"2019-06-13 18:51:36","generalBalance":"0","authorizeList":null}
     * userOrderProductInfo : null
     * code : 1
     * AAAResult : {"reqtime":"20190613104727","acl":"0000","userid":"","deviceid":"8731204033541662","nonce":"Ooeel7z9dKP7","token":"ZdAx67vBDN2vsDpw6Oq6kg==","sid":"6oksTPO6ynOEh9vVmXBR2w==","playurl":"","aclDest":"无权限","errorcodeDesc":"当前账号已在其他设备登录，请重新登录后重试","expiredtime":"20190613104727","authType":"0000","programid":null,"errorcode":"1002"}
     * productInfoList : [{"id":1,"cmboId":"1642701","offerId":"311192_23186301","pName":"QQ音乐","imgUrl":null,"price":1,"type":"0","status":0,"orderCycle":"M","orderBg":null,"cycleCount":1,"relationCmboId":"","relationStatus":"1"}]
     */

    private String cmboIds;
    private String msg;
    private UserInfoBean userInfo;
    private int code;
    private AAAResultBean AAAResult;
    private List<ProductInfoListBean> productInfoList;
    private List<UserOrderProductInfo> userOrderProductInfo;

    public String getCmboIds() {
        return cmboIds;
    }

    public void setCmboIds(String cmboIds) {
        this.cmboIds = cmboIds;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public List<UserOrderProductInfo> getUserOrderProductInfo() {
        return userOrderProductInfo;
    }

    public void setUserOrderProductInfo(List<UserOrderProductInfo> userOrderProductInfo) {
        this.userOrderProductInfo = userOrderProductInfo;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public AAAResultBean getAAAResult() {
        return AAAResult;
    }

    public void setAAAResult(AAAResultBean AAAResult) {
        this.AAAResult = AAAResult;
    }

    public List<ProductInfoListBean> getProductInfoList() {
        return productInfoList;
    }

    public void setProductInfoList(List<ProductInfoListBean> productInfoList) {
        this.productInfoList = productInfoList;
    }

    public boolean isPurchased() {
        return (getCode() == 0 || getCode() == 2);
    }

    public static class UserInfoBean {
        /**
         * userId : 14
         * identityNo : null
         * userName : null
         * catvid : null
         * keyNo : 8731204033541662
         * branchNo : null
         * userPsw : null
         * servid :
         * verifyType : null
         * business : null
         * status : null
         * mobile : null
         * createTime : 2019-06-12 15:19:20
         * email : null
         * qq : null
         * source : null
         * token : ZdAx67vBDN2vsDpw6Oq6kg==
         * regtype : null
         * loginFlag : null
         * custType : null
         * isBindBank : null
         * isinarr : null
         * version : null
         * wxOpenId : null
         * customId : null
         * accountId : null
         * totleBalance : 0
         * balance : 0
         * custCode : null
         * address : null
         * parentLocker : null
         * oweTotalFee : 0
         * userIntegral : 0
         * userImg : null
         * walletAmount : null
         * deviceId : null
         * refreshToken : null
         * tokenExpireDate : 2019-06-13 18:51:36
         * generalBalance : 0
         * authorizeList : null
         */

        private int userId;
        private Object identityNo;
        private Object userName;
        private Object catvid;
        private String keyNo;
        private Object branchNo;
        private Object userPsw;
        private String servid;
        private Object verifyType;
        private Object business;
        private Object status;
        private Object mobile;
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
        private Object accountId;
        private String totleBalance;
        private String balance;
        private Object custCode;
        private Object address;
        private Object parentLocker;
        private String oweTotalFee;
        private int userIntegral;
        private Object userImg;
        private Object walletAmount;
        private Object deviceid;
        private Object refreshToken;
        private String tokenExpireDate;
        private String generalBalance;
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

        public Object getUserName() {
            return userName;
        }

        public void setUserName(Object userName) {
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

        public Object getBranchNo() {
            return branchNo;
        }

        public void setBranchNo(Object branchNo) {
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

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
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

        public Object getAccountId() {
            return accountId;
        }

        public void setAccountId(Object accountId) {
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

        public Object getCustCode() {
            return custCode;
        }

        public void setCustCode(Object custCode) {
            this.custCode = custCode;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
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

        public Object getDeviceid() {
            return deviceid;
        }

        public void setDeviceid(Object deviceid) {
            this.deviceid = deviceid;
        }

        public Object getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(Object refreshToken) {
            this.refreshToken = refreshToken;
        }

        public String getTokenExpireDate() {
            return tokenExpireDate;
        }

        public void setTokenExpireDate(String tokenExpireDate) {
            this.tokenExpireDate = tokenExpireDate;
        }

        public String getGeneralBalance() {
            return generalBalance;
        }

        public void setGeneralBalance(String generalBalance) {
            this.generalBalance = generalBalance;
        }

        public Object getAuthorizeList() {
            return authorizeList;
        }

        public void setAuthorizeList(Object authorizeList) {
            this.authorizeList = authorizeList;
        }
    }

    public static class AAAResultBean {
        /**
         * reqtime : 20190613104727
         * acl : 0000
         * userid :
         * deviceid : 8731204033541662
         * nonce : Ooeel7z9dKP7
         * token : ZdAx67vBDN2vsDpw6Oq6kg==
         * sid : 6oksTPO6ynOEh9vVmXBR2w==
         * playurl :
         * aclDest : 无权限
         * errorcodeDesc : 当前账号已在其他设备登录，请重新登录后重试
         * expiredtime : 20190613104727
         * authType : 0000
         * programid : null
         * errorcode : 1002
         */

        private String reqtime;
        private String acl;
        private String userid;
        private String deviceid;
        private String nonce;
        private String token;
        private String sid;
        private String playurl;
        private String aclDest;
        private String errorcodeDesc;
        private String expiredtime;
        private String authType;
        private Object programid;
        private String errorcode;

        public String getReqtime() {
            return reqtime;
        }

        public void setReqtime(String reqtime) {
            this.reqtime = reqtime;
        }

        public String getAcl() {
            return acl;
        }

        public void setAcl(String acl) {
            this.acl = acl;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getDeviceid() {
            return deviceid;
        }

        public void setDeviceid(String deviceid) {
            this.deviceid = deviceid;
        }

        public String getNonce() {
            return nonce;
        }

        public void setNonce(String nonce) {
            this.nonce = nonce;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getPlayurl() {
            return playurl;
        }

        public void setPlayurl(String playurl) {
            this.playurl = playurl;
        }

        public String getAclDest() {
            return aclDest;
        }

        public void setAclDest(String aclDest) {
            this.aclDest = aclDest;
        }

        public String getErrorcodeDesc() {
            return errorcodeDesc;
        }

        public void setErrorcodeDesc(String errorcodeDesc) {
            this.errorcodeDesc = errorcodeDesc;
        }

        public String getExpiredtime() {
            return expiredtime;
        }

        public void setExpiredtime(String expiredtime) {
            this.expiredtime = expiredtime;
        }

        public String getAuthType() {
            return authType;
        }

        public void setAuthType(String authType) {
            this.authType = authType;
        }

        public Object getProgramid() {
            return programid;
        }

        public void setProgramid(Object programid) {
            this.programid = programid;
        }

        public String getErrorcode() {
            return errorcode;
        }

        public void setErrorcode(String errorcode) {
            this.errorcode = errorcode;
        }
    }

    public static class ProductInfoListBean {
        /**
         * id : 1
         * cmboId : 1642701
         * offerId : 311192_23186301
         * pName : QQ音乐
         * imgUrl : null
         * price : 1
         * type : 0
         * status : 0
         * orderCycle : M
         * orderBg : null
         * cycleCount : 1
         * relationCmboId :
         * relationStatus : 1
         */

        private int id;
        private String cmboId;
        private String offerId;
        private String pName;
        private Object imgUrl;
        private float price;
        private String type;
        private int status;
        private String orderCycle;
        private Object orderBg;
        private int cycleCount;
        private String relationCmboId;
        private String relationStatus;

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

        public Object getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(Object imgUrl) {
            this.imgUrl = imgUrl;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
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

        public Object getOrderBg() {
            return orderBg;
        }

        public void setOrderBg(Object orderBg) {
            this.orderBg = orderBg;
        }

        public int getCycleCount() {
            return cycleCount;
        }

        public void setCycleCount(int cycleCount) {
            this.cycleCount = cycleCount;
        }

        public String getRelationCmboId() {
            return relationCmboId;
        }

        public void setRelationCmboId(String relationCmboId) {
            this.relationCmboId = relationCmboId;
        }

        public String getRelationStatus() {
            return relationStatus;
        }

        public void setRelationStatus(String relationStatus) {
            this.relationStatus = relationStatus;
        }
    }
}
