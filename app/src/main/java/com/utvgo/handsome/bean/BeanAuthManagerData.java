package com.utvgo.handsome.bean;



public class BeanAuthManagerData extends BaseResponse {


    private SData data;




    public void setData(SData data) {
        this.data = data;
    }

    public SData getData() {
        return data;
    }

    public static class SData {
        private String type;
        private String typeDes;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTypeDes() {
            return typeDes;
        }

        public void setTypeDes(String typeDes) {
            this.typeDes = typeDes;
        }
    }
}
