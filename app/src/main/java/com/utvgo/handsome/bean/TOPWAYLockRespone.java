package com.utvgo.handsome.bean;

public class TOPWAYLockRespone {

        private Result result;
        private User_child_locker_detail_info user_child_locker_detail_info;
        public void setResult(Result result) {
            this.result = result;
        }
        public Result getResult() {
            return result;
        }

        public void setUser_child_locker_detail_info(User_child_locker_detail_info user_child_locker_detail_info) {
            this.user_child_locker_detail_info = user_child_locker_detail_info;
        }
        public User_child_locker_detail_info getUser_child_locker_detail_info() {
            return user_child_locker_detail_info;
        }

    public class Result {

        private long state;
        private long sub_state;
        private String reason;
        public void setState(long state) {
            this.state = state;
        }
        public long getState() {
            return state;
        }

        public void setSub_state(long sub_state) {
            this.sub_state = sub_state;
        }
        public long getSub_state() {
            return sub_state;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
        public String getReason() {
            return reason;
        }

    }

    public class User_child_locker_detail_info {

        private String nns_state;
        public void setNns_state(String nns_state) {
            this.nns_state = nns_state;
        }
        public String getNns_state() {
            return nns_state;
        }

    }
}
