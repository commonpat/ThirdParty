package com.utvgo.huya.beans;

import java.util.List;

public class BeanWalletLeave {


    /**
     * output : {"feesums":384,"feebooks":[{"fbname":"现金钱袋","fbid":"0","fbfees":384}]}
     * requestid : WXYYT00120160125787b7e2e
     * message : 执行成功
     * status : 0
     */

    private OutputBean output;
    private String requestid;
    private String message;
    private String status;

    public OutputBean getOutput() {
        return output;
    }

    public void setOutput(OutputBean output) {
        this.output = output;
    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }

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

    public static class OutputBean {
        /**
         * feesums : 384
         * feebooks : [{"fbname":"现金钱袋","fbid":"0","fbfees":384}]
         */

        private int feesums;
        private List<FeebooksBean> feebooks;

        public int getFeesums() {
            return feesums;
        }

        public void setFeesums(int feesums) {
            this.feesums = feesums;
        }

        public List<FeebooksBean> getFeebooks() {
            return feebooks;
        }

        public void setFeebooks(List<FeebooksBean> feebooks) {
            this.feebooks = feebooks;
        }

        public static class FeebooksBean {
            /**
             * fbname : 现金钱袋
             * fbid : 0
             * fbfees : 384
             */

            private String fbname;
            private String fbid;
            private int fbfees;

            public String getFbname() {
                return fbname;
            }

            public void setFbname(String fbname) {
                this.fbname = fbname;
            }

            public String getFbid() {
                return fbid;
            }

            public void setFbid(String fbid) {
                this.fbid = fbid;
            }

            public int getFbfees() {
                return fbfees;
            }

            public void setFbfees(int fbfees) {
                this.fbfees = fbfees;
            }
        }
    }
}
