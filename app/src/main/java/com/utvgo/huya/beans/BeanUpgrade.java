package com.utvgo.huya.beans;

/**
 * Auto-generated: 2020-09-07 14:53:31
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class BeanUpgrade {

    private String code;
    private Data data;
    private String message;
    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public Data getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public class Data {

        private int pkId;
        private int currentVersionCode;
        private String currentVersionName;
        private int minVersionCode;
        private String minVersionName;
        private String upgradeTips;
        private String minUpgradeTips;
        private String projectType;
        private String createTime;
        private String updateTime;
        public void setPkId(int pkId) {
            this.pkId = pkId;
        }
        public int getPkId() {
            return pkId;
        }

        public void setCurrentVersionCode(int currentVersionCode) {
            this.currentVersionCode = currentVersionCode;
        }
        public int getCurrentVersionCode() {
            return currentVersionCode;
        }

        public void setCurrentVersionName(String currentVersionName) {
            this.currentVersionName = currentVersionName;
        }
        public String getCurrentVersionName() {
            return currentVersionName;
        }

        public void setMinVersionCode(int minVersionCode) {
            this.minVersionCode = minVersionCode;
        }
        public int getMinVersionCode() {
            return minVersionCode;
        }

        public void setMinVersionName(String minVersionName) {
            this.minVersionName = minVersionName;
        }
        public String getMinVersionName() {
            return minVersionName;
        }

        public void setUpgradeTips(String upgradeTips) {
            this.upgradeTips = upgradeTips;
        }
        public String getUpgradeTips() {
            return upgradeTips;
        }

        public void setMinUpgradeTips(String minUpgradeTips) {
            this.minUpgradeTips = minUpgradeTips;
        }
        public String getMinUpgradeTips() {
            return minUpgradeTips;
        }

        public void setProjectType(String projectType) {
            this.projectType = projectType;
        }
        public String getProjectType() {
            return projectType;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
        public String getCreateTime() {
            return createTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
        public String getUpdateTime() {
            return updateTime;
        }

    }
}
