package com.utvgo.huya.beans;

public class BeanUserBindingInfo extends BaseResponse {

    /**
     * code : 1
     * data : {"pkId":1,"keyNo":"xxxx","musicId":"1152921504910510913","musicKey":"EFDC797972D9D5C59B02C86B5823DA945816D803B2BE15F2EA08281636961582","wxAccessToken":"20_G6t3eCK7XfRTjGhGct3pCYvWYDObESJv2IKUhTDnL7rYYHbllPDpkLLxNcglzHCBvo1VzT9lkRefOPpqlCoEJA","wxOpenid":"opCFJwwF-ymHn5x-yo2LYILineSw","wxRefreshToken":"20_fgmyvLnXbyS7ZJn9CCb7cLgZCkC_fWhmuIyEE4aIfj6SSlGA9XAyNpCS4RJ_PesHtZb6usq8wonrEuD20YOBdg","wxUnionid":"oqFLxsnhuifYk7U4W98739R1a2pY","wxRefreshTime":3600,"wxExpireTime":"2019-04-09 16:15:33","wxHeadImgUrl":"http://thirdwx.qlogo.cn/mmopen/vi_32/piaQFZ0kTaOGCtUic1K1TnSNiaFe9YFSBMhHsXMolthgzicuYXxboIT7NCrW0m20HwBAXojsQpyOnibaIXBIgD8JcEQ/132","wxNickName":"西方","qqOpenAppId":"","qqAccessToken":"","qqOpenid":"","qqRefreshToken":"","qqHeadImgUrl":"","qqExpiresIn":0,"qqExpireTime":null,"qqNickname":"","newLoginType":"1","createTime":null,"updateTime":"2019-04-09 15:23:18"}
     * message : success
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pkId : 1
         * keyNo : xxxx
         * musicId : 1152921504910510913
         * musicKey : EFDC797972D9D5C59B02C86B5823DA945816D803B2BE15F2EA08281636961582
         * wxAccessToken : 20_G6t3eCK7XfRTjGhGct3pCYvWYDObESJv2IKUhTDnL7rYYHbllPDpkLLxNcglzHCBvo1VzT9lkRefOPpqlCoEJA
         * wxOpenid : opCFJwwF-ymHn5x-yo2LYILineSw
         * wxRefreshToken : 20_fgmyvLnXbyS7ZJn9CCb7cLgZCkC_fWhmuIyEE4aIfj6SSlGA9XAyNpCS4RJ_PesHtZb6usq8wonrEuD20YOBdg
         * wxUnionid : oqFLxsnhuifYk7U4W98739R1a2pY
         * wxRefreshTime : 3600
         * wxExpireTime : 2019-04-09 16:15:33
         * wxHeadImgUrl : http://thirdwx.qlogo.cn/mmopen/vi_32/piaQFZ0kTaOGCtUic1K1TnSNiaFe9YFSBMhHsXMolthgzicuYXxboIT7NCrW0m20HwBAXojsQpyOnibaIXBIgD8JcEQ/132
         * wxNickName : 西方
         * qqOpenAppId :
         * qqAccessToken :
         * qqOpenid :
         * qqRefreshToken :
         * qqHeadImgUrl :
         * qqExpiresIn : 0
         * qqExpireTime : null
         * qqNickname :
         * newLoginType : 1
         * createTime : null
         * updateTime : 2019-04-09 15:23:18
         */

        private int pkId;
        private String keyNo;
        private String musicId;
        private String musicKey;
        private String wxAccessToken;
        private String wxOpenid;
        private String wxRefreshToken;
        private String wxUnionid;
        private int wxRefreshTime;
        private String wxExpireTime;
        private String wxHeadImgUrl;
        private String wxNickName;
        private String qqOpenAppId;
        private String qqAccessToken;
        private String qqOpenid;
        private String qqRefreshToken;
        private String qqHeadImgUrl;
        private int qqExpiresIn;
        private String qqExpireTime;
        private String qqNickname;
        private String newLoginType;
        private String createTime;
        private String updateTime;

        public int getPkId() {
            return pkId;
        }

        public void setPkId(int pkId) {
            this.pkId = pkId;
        }

        public String getKeyNo() {
            return keyNo;
        }

        public void setKeyNo(String keyNo) {
            this.keyNo = keyNo;
        }

        public String getMusicId() {
            return musicId;
        }

        public void setMusicId(String musicId) {
            this.musicId = musicId;
        }

        public String getMusicKey() {
            return musicKey;
        }

        public void setMusicKey(String musicKey) {
            this.musicKey = musicKey;
        }

        public String getWxAccessToken() {
            return wxAccessToken;
        }

        public void setWxAccessToken(String wxAccessToken) {
            this.wxAccessToken = wxAccessToken;
        }

        public String getWxOpenid() {
            return wxOpenid;
        }

        public void setWxOpenid(String wxOpenid) {
            this.wxOpenid = wxOpenid;
        }

        public String getWxRefreshToken() {
            return wxRefreshToken;
        }

        public void setWxRefreshToken(String wxRefreshToken) {
            this.wxRefreshToken = wxRefreshToken;
        }

        public String getWxUnionid() {
            return wxUnionid;
        }

        public void setWxUnionid(String wxUnionid) {
            this.wxUnionid = wxUnionid;
        }

        public int getWxRefreshTime() {
            return wxRefreshTime;
        }

        public void setWxRefreshTime(int wxRefreshTime) {
            this.wxRefreshTime = wxRefreshTime;
        }

        public String getWxExpireTime() {
            return wxExpireTime;
        }

        public void setWxExpireTime(String wxExpireTime) {
            this.wxExpireTime = wxExpireTime;
        }

        public String getWxHeadImgUrl() {
            return wxHeadImgUrl;
        }

        public void setWxHeadImgUrl(String wxHeadImgUrl) {
            this.wxHeadImgUrl = wxHeadImgUrl;
        }

        public String getWxNickName() {
            return wxNickName;
        }

        public void setWxNickName(String wxNickName) {
            this.wxNickName = wxNickName;
        }

        public String getQqOpenAppId() {
            return qqOpenAppId;
        }

        public void setQqOpenAppId(String qqOpenAppId) {
            this.qqOpenAppId = qqOpenAppId;
        }

        public String getQqAccessToken() {
            return qqAccessToken;
        }

        public void setQqAccessToken(String qqAccessToken) {
            this.qqAccessToken = qqAccessToken;
        }

        public String getQqOpenid() {
            return qqOpenid;
        }

        public void setQqOpenid(String qqOpenid) {
            this.qqOpenid = qqOpenid;
        }

        public String getQqRefreshToken() {
            return qqRefreshToken;
        }

        public void setQqRefreshToken(String qqRefreshToken) {
            this.qqRefreshToken = qqRefreshToken;
        }

        public String getQqHeadImgUrl() {
            return qqHeadImgUrl;
        }

        public void setQqHeadImgUrl(String qqHeadImgUrl) {
            this.qqHeadImgUrl = qqHeadImgUrl;
        }

        public int getQqExpiresIn() {
            return qqExpiresIn;
        }

        public void setQqExpiresIn(int qqExpiresIn) {
            this.qqExpiresIn = qqExpiresIn;
        }

        public String getQqExpireTime() {
            return qqExpireTime;
        }

        public void setQqExpireTime(String qqExpireTime) {
            this.qqExpireTime = qqExpireTime;
        }

        public String getQqNickname() {
            return qqNickname;
        }

        public void setQqNickname(String qqNickname) {
            this.qqNickname = qqNickname;
        }

        public String getNewLoginType() {
            return newLoginType;
        }

        public void setNewLoginType(String newLoginType) {
            this.newLoginType = newLoginType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
