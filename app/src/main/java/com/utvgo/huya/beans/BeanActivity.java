package com.utvgo.huya.beans;

import java.util.List;

/**
 * Created by oo on 2018/3/26.
 */

public class BeanActivity {


    /**
     * code : 1
     * data : {"operateList":[{"id":1,"name":"提交按钮","normalImg":"2018/04/13/activity_5.png","focusImg":"2018/04/13/activity_4.png","type":2,"xPos":100,"yPos":324,"status":"1","themeId":"2"},{"id":2,"name":"输入框","normalImg":"","focusImg":"","type":1,"xPos":200,"yPos":300,"status":"1","themeId":"2"}],"infoImg":"activity_1.jpg","smallImg":"activity_3.png","name":"test2","bigBgImg":"activity_2.jpg","id":2,"templateId":9}
     * message : success
     */

    private String code;
    private DataBean data;
    private String message;

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

    public static class DataBean {
        /**
         * operateList : [{"id":1,"name":"提交按钮","normalImg":"2018/04/13/activity_5.png","focusImg":"2018/04/13/activity_4.png","type":2,"xPos":100,"yPos":324,"status":"1","themeId":"2"},{"id":2,"name":"输入框","normalImg":"","focusImg":"","type":1,"xPos":200,"yPos":300,"status":"1","themeId":"2"}]
         * infoImg : activity_1.jpg
         * smallImg : activity_3.png
         * name : test2
         * bigBgImg : activity_2.jpg
         * id : 2
         * templateId : 9
         */

        private String infoImg;
        private String smallImg;
        private String name;
        private String bigBgImg;
        private int id;
        private int templateId;
        private List<OperateListBean> operateList;

        public String getInfoImg() {
            return infoImg;
        }

        public void setInfoImg(String infoImg) {
            this.infoImg = infoImg;
        }

        public String getSmallImg() {
            return smallImg;
        }

        public void setSmallImg(String smallImg) {
            this.smallImg = smallImg;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBigBgImg() {
            return bigBgImg;
        }

        public void setBigBgImg(String bigBgImg) {
            this.bigBgImg = bigBgImg;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTemplateId() {
            return templateId;
        }

        public void setTemplateId(int templateId) {
            this.templateId = templateId;
        }

        public List<OperateListBean> getOperateList() {
            return operateList;
        }

        public void setOperateList(List<OperateListBean> operateList) {
            this.operateList = operateList;
        }

        public static class OperateListBean {
            /**
             * id : 1
             * name : 提交按钮
             * normalImg : 2018/04/13/activity_5.png
             * focusImg : 2018/04/13/activity_4.png
             * type : 2
             * xPos : 100
             * yPos : 324
             * status : 1
             * focusAble : 1
             * themeId : 2
             */

            private int id;
            private String name;
            private String normalImg;
            private String focusImg;
            private int type;
            private int focusAble;
            private int xPos;
            private int yPos;
            private String status;
            private String themeId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNormalImg() {
                return normalImg;
            }

            public void setNormalImg(String normalImg) {
                this.normalImg = normalImg;
            }

            public String getFocusImg() {
                return focusImg;
            }

            public void setFocusImg(String focusImg) {
                this.focusImg = focusImg;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getXPos() {
                return xPos;
            }

            public void setXPos(int xPos) {
                this.xPos = xPos;
            }

            public int getYPos() {
                return yPos;
            }

            public void setYPos(int yPos) {
                this.yPos = yPos;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getThemeId() {
                return themeId;
            }

            public void setThemeId(String themeId) {
                this.themeId = themeId;
            }

            public int getFocusAble() {
                return focusAble;
            }

            public void setFocusAble(int focusAble) {
                this.focusAble = focusAble;
            }
        }
    }
}
