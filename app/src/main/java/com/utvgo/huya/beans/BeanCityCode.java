package com.utvgo.huya.beans;

public class BeanCityCode {


    /**
     * message : 操作成功
     * status : 0000
     * output : {"citycode":"5201","keyno":"8851002690076087"}
     */

    private String message;
    private String status;
    private OutputBean output;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OutputBean getOutput() {
        return output;
    }

    public void setOutput(OutputBean output) {
        this.output = output;
    }

    public static class OutputBean {
        /**
         * citycode : 5201
         * keyno : 8851002690076087
         */

        private String citycode;
        private String keyno;

        public String getCitycode() {
            return citycode;
        }

        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }

        public String getKeyno() {
            return keyno;
        }

        public void setKeyno(String keyno) {
            this.keyno = keyno;
        }
    }
}
