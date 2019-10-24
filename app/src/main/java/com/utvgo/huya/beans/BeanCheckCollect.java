package com.utvgo.huya.beans;


public class BeanCheckCollect extends BaseResponse {

    private Data data;
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public static class Data{
        private String isCollect;
        public String getIsCollect() {
            return isCollect;
        }
        public void setIsCollect(String isCollect) {
            this.isCollect = isCollect;
        }
    }
}
