package com.utvgo.handsome.bean;

import java.util.List;


public class BeanChongQingAuth {

    private String cmboIds;
    private UserInfo userInfo;
    private String userOrderProductInfo;
    private int code;
    private int isboss;
    private List<ProductInfoList> productInfoList;
    private String message;
    private boolean isbindingDevice;
    public void setCmboIds(String cmboIds) {
        this.cmboIds = cmboIds;
    }
    public String getCmboIds() {
        return cmboIds;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserOrderProductInfo(String userOrderProductInfo) {
        this.userOrderProductInfo = userOrderProductInfo;
    }
    public String getUserOrderProductInfo() {
        return userOrderProductInfo;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }

    public void setIsboss(int isboss) {
        this.isboss = isboss;
    }
    public int getIsboss() {
        return isboss;
    }

    public void setProductInfoList(List<ProductInfoList> productInfoList) {
        this.productInfoList = productInfoList;
    }
    public List<ProductInfoList> getProductInfoList() {
        return productInfoList;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setIsbindingDevice(boolean isbindingDevice) {
        this.isbindingDevice = isbindingDevice;
    }
    public boolean getIsbindingDevice() {
        return isbindingDevice;
    }

    public class UserInfo {

        private int userId;
        private String identityNo;
        private String userName;
        private String catvid;
        private String keyNo;
        private String branchNo;
        private String userPsw;
        private String servid;
        private String verifyType;
        private String business;
        private String status;
        private String mobile;
        private String createTime;
        private String email;
        private String qq;
        private String source;
        private String token;
        private String regtype;
        private String loginFlag;
        private String custType;
        private String isBindBank;
        private String isinarr;
        private String version;
        private String wxOpenId;
        private String customId;
        private String accountId;
        private String totleBalance;
        private String balance;
        private String custCode;
        private String address;
        private String parentLocker;
        private String oweTotalFee;
        private String userIntegral;
        private String userImg;
        private String walletAmount;
        private String deviceId;
        private String refreshToken;
        private String tokenExpireDate;
        private String generalBalance;
        private String authorizeList;
        public void setUserId(int userId) {
            this.userId = userId;
        }
        public int getUserId() {
            return userId;
        }

        @Override
        public String toString() {
            return "UserInfo{" +
                    "userId=" + userId +
                    ", identityNo='" + identityNo + '\'' +
                    ", userName='" + userName + '\'' +
                    ", catvid='" + catvid + '\'' +
                    ", keyNo='" + keyNo + '\'' +
                    ", branchNo='" + branchNo + '\'' +
                    ", userPsw='" + userPsw + '\'' +
                    ", servid='" + servid + '\'' +
                    ", verifyType='" + verifyType + '\'' +
                    ", business='" + business + '\'' +
                    ", status='" + status + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", email='" + email + '\'' +
                    ", qq='" + qq + '\'' +
                    ", source='" + source + '\'' +
                    ", token='" + token + '\'' +
                    ", regtype='" + regtype + '\'' +
                    ", loginFlag='" + loginFlag + '\'' +
                    ", custType='" + custType + '\'' +
                    ", isBindBank='" + isBindBank + '\'' +
                    ", isinarr='" + isinarr + '\'' +
                    ", version='" + version + '\'' +
                    ", wxOpenId='" + wxOpenId + '\'' +
                    ", customId='" + customId + '\'' +
                    ", accountId='" + accountId + '\'' +
                    ", totleBalance='" + totleBalance + '\'' +
                    ", balance='" + balance + '\'' +
                    ", custCode='" + custCode + '\'' +
                    ", address='" + address + '\'' +
                    ", parentLocker='" + parentLocker + '\'' +
                    ", oweTotalFee='" + oweTotalFee + '\'' +
                    ", userIntegral='" + userIntegral + '\'' +
                    ", userImg='" + userImg + '\'' +
                    ", walletAmount='" + walletAmount + '\'' +
                    ", deviceId='" + deviceId + '\'' +
                    ", refreshToken='" + refreshToken + '\'' +
                    ", tokenExpireDate='" + tokenExpireDate + '\'' +
                    ", generalBalance='" + generalBalance + '\'' +
                    ", authorizeList='" + authorizeList + '\'' +
                    '}';
        }

        public void setIdentityNo(String identityNo) {
            this.identityNo = identityNo;
        }
        public String getIdentityNo() {
            return identityNo;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
        public String getUserName() {
            return userName;
        }

        public void setCatvid(String catvid) {
            this.catvid = catvid;
        }
        public String getCatvid() {
            return catvid;
        }

        public void setKeyNo(String keyNo) {
            this.keyNo = keyNo;
        }
        public String getKeyNo() {
            return keyNo;
        }

        public void setBranchNo(String branchNo) {
            this.branchNo = branchNo;
        }
        public String getBranchNo() {
            return branchNo;
        }

        public void setUserPsw(String userPsw) {
            this.userPsw = userPsw;
        }
        public String getUserPsw() {
            return userPsw;
        }

        public void setServid(String servid) {
            this.servid = servid;
        }
        public String getServid() {
            return servid;
        }

        public void setVerifyType(String verifyType) {
            this.verifyType = verifyType;
        }
        public String getVerifyType() {
            return verifyType;
        }

        public void setBusiness(String business) {
            this.business = business;
        }
        public String getBusiness() {
            return business;
        }

        public void setStatus(String status) {
            this.status = status;
        }
        public String getStatus() {
            return status;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
        public String getMobile() {
            return mobile;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
        public String getCreateTime() {
            return createTime;
        }

        public void setEmail(String email) {
            this.email = email;
        }
        public String getEmail() {
            return email;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }
        public String getQq() {
            return qq;
        }

        public void setSource(String source) {
            this.source = source;
        }
        public String getSource() {
            return source;
        }

        public void setToken(String token) {
            this.token = token;
        }
        public String getToken() {
            return token;
        }

        public void setRegtype(String regtype) {
            this.regtype = regtype;
        }
        public String getRegtype() {
            return regtype;
        }

        public void setLoginFlag(String loginFlag) {
            this.loginFlag = loginFlag;
        }
        public String getLoginFlag() {
            return loginFlag;
        }

        public void setCustType(String custType) {
            this.custType = custType;
        }
        public String getCustType() {
            return custType;
        }

        public void setIsBindBank(String isBindBank) {
            this.isBindBank = isBindBank;
        }
        public String getIsBindBank() {
            return isBindBank;
        }

        public void setIsinarr(String isinarr) {
            this.isinarr = isinarr;
        }
        public String getIsinarr() {
            return isinarr;
        }

        public void setVersion(String version) {
            this.version = version;
        }
        public String getVersion() {
            return version;
        }

        public void setWxOpenId(String wxOpenId) {
            this.wxOpenId = wxOpenId;
        }
        public String getWxOpenId() {
            return wxOpenId;
        }

        public void setCustomId(String customId) {
            this.customId = customId;
        }
        public String getCustomId() {
            return customId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }
        public String getAccountId() {
            return accountId;
        }

        public void setTotleBalance(String totleBalance) {
            this.totleBalance = totleBalance;
        }
        public String getTotleBalance() {
            return totleBalance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }
        public String getBalance() {
            return balance;
        }

        public void setCustCode(String custCode) {
            this.custCode = custCode;
        }
        public String getCustCode() {
            return custCode;
        }

        public void setAddress(String address) {
            this.address = address;
        }
        public String getAddress() {
            return address;
        }

        public void setParentLocker(String parentLocker) {
            this.parentLocker = parentLocker;
        }
        public String getParentLocker() {
            return parentLocker;
        }

        public void setOweTotalFee(String oweTotalFee) {
            this.oweTotalFee = oweTotalFee;
        }
        public String getOweTotalFee() {
            return oweTotalFee;
        }

        public void setUserIntegral(String userIntegral) {
            this.userIntegral = userIntegral;
        }
        public String getUserIntegral() {
            return userIntegral;
        }

        public void setUserImg(String userImg) {
            this.userImg = userImg;
        }
        public String getUserImg() {
            return userImg;
        }

        public void setWalletAmount(String walletAmount) {
            this.walletAmount = walletAmount;
        }
        public String getWalletAmount() {
            return walletAmount;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }
        public String getDeviceId() {
            return deviceId;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }
        public String getRefreshToken() {
            return refreshToken;
        }

        public void setTokenExpireDate(String tokenExpireDate) {
            this.tokenExpireDate = tokenExpireDate;
        }
        public String getTokenExpireDate() {
            return tokenExpireDate;
        }

        public void setGeneralBalance(String generalBalance) {
            this.generalBalance = generalBalance;
        }
        public String getGeneralBalance() {
            return generalBalance;
        }

        public void setAuthorizeList(String authorizeList) {
            this.authorizeList = authorizeList;
        }
        public String getAuthorizeList() {
            return authorizeList;
        }

    }


    public class ProductInfoList {

        private int id;
        private String cmboId;
        private String offerId;
        private String pName;
        private String imgUrl;
        private int price;
        private String type;
        private int status;
        private String orderCycle;
        private String orderBg;
        private int cycleCount;
        private String relationCmboId;
        private String relationStatus;
        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return "ProductInfoList{" +
                    "id=" + id +
                    ", cmboId='" + cmboId + '\'' +
                    ", offerId='" + offerId + '\'' +
                    ", pName='" + pName + '\'' +
                    ", imgUrl='" + imgUrl + '\'' +
                    ", price=" + price +
                    ", type='" + type + '\'' +
                    ", status=" + status +
                    ", orderCycle='" + orderCycle + '\'' +
                    ", orderBg='" + orderBg + '\'' +
                    ", cycleCount=" + cycleCount +
                    ", relationCmboId='" + relationCmboId + '\'' +
                    ", relationStatus='" + relationStatus + '\'' +
                    '}';
        }

        public void setCmboId(String cmboId) {
            this.cmboId = cmboId;
        }
        public String getCmboId() {
            return cmboId;
        }

        public void setOfferId(String offerId) {
            this.offerId = offerId;
        }
        public String getOfferId() {
            return offerId;
        }

        public void setPName(String pName) {
            this.pName = pName;
        }
        public String getPName() {
            return pName;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
        public String getImgUrl() {
            return imgUrl;
        }

        public void setPrice(int price) {
            this.price = price;
        }
        public int getPrice() {
            return price;
        }

        public void setType(String type) {
            this.type = type;
        }
        public String getType() {
            return type;
        }

        public void setStatus(int status) {
            this.status = status;
        }
        public int getStatus() {
            return status;
        }

        public void setOrderCycle(String orderCycle) {
            this.orderCycle = orderCycle;
        }
        public String getOrderCycle() {
            return orderCycle;
        }

        public void setOrderBg(String orderBg) {
            this.orderBg = orderBg;
        }
        public String getOrderBg() {
            return orderBg;
        }

        public void setCycleCount(int cycleCount) {
            this.cycleCount = cycleCount;
        }
        public int getCycleCount() {
            return cycleCount;
        }

        public void setRelationCmboId(String relationCmboId) {
            this.relationCmboId = relationCmboId;
        }
        public String getRelationCmboId() {
            return relationCmboId;
        }

        public void setRelationStatus(String relationStatus) {
            this.relationStatus = relationStatus;
        }
        public String getRelationStatus() {
            return relationStatus;
        }

    }

    @Override
    public String toString() {
        return "BeanChongQingAuth{" +
                "cmboIds='" + cmboIds + '\'' +
                ", userInfo=" + userInfo +
                ", userOrderProductInfo='" + userOrderProductInfo + '\'' +
                ", code=" + code +
                ", isboss=" + isboss +
                ", productInfoList=" + productInfoList +
                ", message='" + message + '\'' +
                ", isbindingDevice=" + isbindingDevice +
                '}';
    }
}