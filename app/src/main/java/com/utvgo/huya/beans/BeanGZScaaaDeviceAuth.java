package com.utvgo.huya.beans;


/**
 * Created by dev on 2018/3/14.
 */

public class BeanGZScaaaDeviceAuth {


    /**
     * result : {"state":300000,"sub_state":300003,"reason":" "}
     * auth : {"web_token":"59558a23f455a83850b35670e8d97835","expires_in":4569,"valid_time":"2016-10-28T21:02:20+0800","refresh_time":"2000","token":","}
     * user : {"id":"lj","name":"lj","rank":"1","email":"","telephone":"","addr":"","age":"","sex":"","occupation":"","device_id":"d1","headimgurl":"","first_use_time":"0000-00-00 00:00:00","attribution":"","user_from":"0","user_level":"1","boss_user_group":"0","user_vip_level_detail_info":{"nns_id":"57ce361ed0c3e8985a3edb1c8d091d05","nns_vip_level":"1","nns_vip_name":"","nns_status":"0","nns_is_have_advertisement":"0","nns_img_v":"","nns_img_h":"","nns_img_s":"","nns_desc":"","nns_extend_language":null,"nns_create_time":"2016-09-06 11:21:02","nns_modify_time":"2016-09-06 11:21:02"},"user_growth_value":"","user_level_begin_time":"","user_level_end_time":"","boss_top_box_id":"","user_is_category":"0","boss_area_code":"","boss_area_bind":"NO","area_code":"","nns_last_login_version":"STB","nns_last_login_mac":"MAC"}
     * device : {"id":"d1","name":"device1","first_use_time":"2016-07-12 10:28:39","create_time":"2015-12-23 10:12:52","is_add_device":1}
     * client_ip : 0.0.0.0
     */

    private ResultBean result;
    private AuthBean auth;
    private UserBean user;
    private DeviceBean device;
    private String client_ip;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public AuthBean getAuth() {
        return auth;
    }

    public void setAuth(AuthBean auth) {
        this.auth = auth;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public DeviceBean getDevice() {
        return device;
    }

    public void setDevice(DeviceBean device) {
        this.device = device;
    }

    public String getClient_ip() {
        return client_ip;
    }

    public void setClient_ip(String client_ip) {
        this.client_ip = client_ip;
    }

    public static class ResultBean {
        /**
         * state : 300000
         * sub_state : 300003
         * reason :
         */

        private int state;
        private int sub_state;
        private String reason;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getSub_state() {
            return sub_state;
        }

        public void setSub_state(int sub_state) {
            this.sub_state = sub_state;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }

    public static class AuthBean {
        /**
         * web_token : 59558a23f455a83850b35670e8d97835
         * expires_in : 4569
         * valid_time : 2016-10-28T21:02:20+0800
         * refresh_time : 2000
         * token : ,
         */

        private String web_token;
        private int expires_in;
        private String valid_time;
        private String refresh_time;
        private String token;

        public String getWeb_token() {
            return web_token;
        }

        public void setWeb_token(String web_token) {
            this.web_token = web_token;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }

        public String getValid_time() {
            return valid_time;
        }

        public void setValid_time(String valid_time) {
            this.valid_time = valid_time;
        }

        public String getRefresh_time() {
            return refresh_time;
        }

        public void setRefresh_time(String refresh_time) {
            this.refresh_time = refresh_time;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    public static class UserBean {
        /**
         * id : lj
         * name : lj
         * rank : 1
         * email :
         * telephone :
         * addr :
         * age :
         * sex :
         * occupation :
         * device_id : d1
         * headimgurl :
         * first_use_time : 0000-00-00 00:00:00
         * attribution :
         * user_from : 0
         * user_level : 1
         * boss_user_group : 0
         * user_vip_level_detail_info : {"nns_id":"57ce361ed0c3e8985a3edb1c8d091d05","nns_vip_level":"1","nns_vip_name":"","nns_status":"0","nns_is_have_advertisement":"0","nns_img_v":"","nns_img_h":"","nns_img_s":"","nns_desc":"","nns_extend_language":null,"nns_create_time":"2016-09-06 11:21:02","nns_modify_time":"2016-09-06 11:21:02"}
         * user_growth_value :
         * user_level_begin_time :
         * user_level_end_time :
         * boss_top_box_id :
         * user_is_category : 0
         * boss_area_code :
         * boss_area_bind : NO
         * area_code :
         * nns_last_login_version : STB
         * nns_last_login_mac : MAC
         */

        private String id;
        private String name;
        private String rank;
        private String email;
        private String telephone;
        private String addr;
        private String age;
        private String sex;
        private String occupation;
        private String device_id;
        private String headimgurl;
        private String first_use_time;
        private String attribution;
        private String user_from;
        private String user_level;
        private String boss_user_group;
        private UserVipLevelDetailInfoBean user_vip_level_detail_info;
        private String user_growth_value;
        private String user_level_begin_time;
        private String user_level_end_time;
        private String boss_top_box_id;
        private String user_is_category;
        private String boss_area_code;
        private String boss_area_bind;
        private String area_code;
        private String nns_last_login_version;
        private String nns_last_login_mac;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getOccupation() {
            return occupation;
        }

        public void setOccupation(String occupation) {
            this.occupation = occupation;
        }

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getFirst_use_time() {
            return first_use_time;
        }

        public void setFirst_use_time(String first_use_time) {
            this.first_use_time = first_use_time;
        }

        public String getAttribution() {
            return attribution;
        }

        public void setAttribution(String attribution) {
            this.attribution = attribution;
        }

        public String getUser_from() {
            return user_from;
        }

        public void setUser_from(String user_from) {
            this.user_from = user_from;
        }

        public String getUser_level() {
            return user_level;
        }

        public void setUser_level(String user_level) {
            this.user_level = user_level;
        }

        public String getBoss_user_group() {
            return boss_user_group;
        }

        public void setBoss_user_group(String boss_user_group) {
            this.boss_user_group = boss_user_group;
        }

        public UserVipLevelDetailInfoBean getUser_vip_level_detail_info() {
            return user_vip_level_detail_info;
        }

        public void setUser_vip_level_detail_info(UserVipLevelDetailInfoBean user_vip_level_detail_info) {
            this.user_vip_level_detail_info = user_vip_level_detail_info;
        }

        public String getUser_growth_value() {
            return user_growth_value;
        }

        public void setUser_growth_value(String user_growth_value) {
            this.user_growth_value = user_growth_value;
        }

        public String getUser_level_begin_time() {
            return user_level_begin_time;
        }

        public void setUser_level_begin_time(String user_level_begin_time) {
            this.user_level_begin_time = user_level_begin_time;
        }

        public String getUser_level_end_time() {
            return user_level_end_time;
        }

        public void setUser_level_end_time(String user_level_end_time) {
            this.user_level_end_time = user_level_end_time;
        }

        public String getBoss_top_box_id() {
            return boss_top_box_id;
        }

        public void setBoss_top_box_id(String boss_top_box_id) {
            this.boss_top_box_id = boss_top_box_id;
        }

        public String getUser_is_category() {
            return user_is_category;
        }

        public void setUser_is_category(String user_is_category) {
            this.user_is_category = user_is_category;
        }

        public String getBoss_area_code() {
            return boss_area_code;
        }

        public void setBoss_area_code(String boss_area_code) {
            this.boss_area_code = boss_area_code;
        }

        public String getBoss_area_bind() {
            return boss_area_bind;
        }

        public void setBoss_area_bind(String boss_area_bind) {
            this.boss_area_bind = boss_area_bind;
        }

        public String getArea_code() {
            return area_code;
        }

        public void setArea_code(String area_code) {
            this.area_code = area_code;
        }

        public String getNns_last_login_version() {
            return nns_last_login_version;
        }

        public void setNns_last_login_version(String nns_last_login_version) {
            this.nns_last_login_version = nns_last_login_version;
        }

        public String getNns_last_login_mac() {
            return nns_last_login_mac;
        }

        public void setNns_last_login_mac(String nns_last_login_mac) {
            this.nns_last_login_mac = nns_last_login_mac;
        }

        public static class UserVipLevelDetailInfoBean {
            /**
             * nns_id : 57ce361ed0c3e8985a3edb1c8d091d05
             * nns_vip_level : 1
             * nns_vip_name :
             * nns_status : 0
             * nns_is_have_advertisement : 0
             * nns_img_v :
             * nns_img_h :
             * nns_img_s :
             * nns_desc :
             * nns_extend_language : null
             * nns_create_time : 2016-09-06 11:21:02
             * nns_modify_time : 2016-09-06 11:21:02
             */

            private String nns_id;
            private String nns_vip_level;
            private String nns_vip_name;
            private String nns_status;
            private String nns_is_have_advertisement;
            private String nns_img_v;
            private String nns_img_h;
            private String nns_img_s;
            private String nns_desc;
            private Object nns_extend_language;
            private String nns_create_time;
            private String nns_modify_time;

            public String getNns_id() {
                return nns_id;
            }

            public void setNns_id(String nns_id) {
                this.nns_id = nns_id;
            }

            public String getNns_vip_level() {
                return nns_vip_level;
            }

            public void setNns_vip_level(String nns_vip_level) {
                this.nns_vip_level = nns_vip_level;
            }

            public String getNns_vip_name() {
                return nns_vip_name;
            }

            public void setNns_vip_name(String nns_vip_name) {
                this.nns_vip_name = nns_vip_name;
            }

            public String getNns_status() {
                return nns_status;
            }

            public void setNns_status(String nns_status) {
                this.nns_status = nns_status;
            }

            public String getNns_is_have_advertisement() {
                return nns_is_have_advertisement;
            }

            public void setNns_is_have_advertisement(String nns_is_have_advertisement) {
                this.nns_is_have_advertisement = nns_is_have_advertisement;
            }

            public String getNns_img_v() {
                return nns_img_v;
            }

            public void setNns_img_v(String nns_img_v) {
                this.nns_img_v = nns_img_v;
            }

            public String getNns_img_h() {
                return nns_img_h;
            }

            public void setNns_img_h(String nns_img_h) {
                this.nns_img_h = nns_img_h;
            }

            public String getNns_img_s() {
                return nns_img_s;
            }

            public void setNns_img_s(String nns_img_s) {
                this.nns_img_s = nns_img_s;
            }

            public String getNns_desc() {
                return nns_desc;
            }

            public void setNns_desc(String nns_desc) {
                this.nns_desc = nns_desc;
            }

            public Object getNns_extend_language() {
                return nns_extend_language;
            }

            public void setNns_extend_language(Object nns_extend_language) {
                this.nns_extend_language = nns_extend_language;
            }

            public String getNns_create_time() {
                return nns_create_time;
            }

            public void setNns_create_time(String nns_create_time) {
                this.nns_create_time = nns_create_time;
            }

            public String getNns_modify_time() {
                return nns_modify_time;
            }

            public void setNns_modify_time(String nns_modify_time) {
                this.nns_modify_time = nns_modify_time;
            }
        }
    }

    public static class DeviceBean {
        /**
         * id : d1
         * name : device1
         * first_use_time : 2016-07-12 10:28:39
         * create_time : 2015-12-23 10:12:52
         * is_add_device : 1
         */

        private String id;
        private String name;
        private String first_use_time;
        private String create_time;
        private int is_add_device;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFirst_use_time() {
            return first_use_time;
        }

        public void setFirst_use_time(String first_use_time) {
            this.first_use_time = first_use_time;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getIs_add_device() {
            return is_add_device;
        }

        public void setIs_add_device(int is_add_device) {
            this.is_add_device = is_add_device;
        }
    }
}
