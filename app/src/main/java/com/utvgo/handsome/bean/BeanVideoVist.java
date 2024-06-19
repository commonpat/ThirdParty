package com.utvgo.handsome.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author wzb
 * @description:
 * @date : 2020/11/3 10:55
 */

public class BeanVideoVist {

    private String code;
    private List<Data> data;
    private String message;
    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
    public List<Data> getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    SimpleDateFormat simpleDateFormat  =new SimpleDateFormat();

    public class Data {

        private String singerName;
        private int duration;
        private String vodId;
        private int isFree;
        private int freeTime;
        private String name;
        private String mvMid;
        private String category;
        private String status;
        private String app;
        private String servicetype;
        private String provider_id;
        private String category_id;
        private String category_name;
        private String asset_id;
        private String asset_name;
        private String episodes;
        private String vodepisodes;
        private String program_time;
        private String ver;
        private String curtime;
        private String app_id;
        private String play_time;
        private String type;
        private String page_path;
        private String source_path;
        private String page_name;
        private String tsid;
        private String freq;

        public String getTsid() {
            return tsid = "".equals(tsid) || tsid ==null ? "52490" : tsid;

        }

        public void setTsid(String tsid) {
            this.tsid = tsid;
        }

        public String getFreq() {
            return freq = "".equals(freq) || freq ==null ? "754000" : freq;
        }

        public void setFreq(String freq) {
            this.freq = freq;
        }

        public String getApp() {
            return app = "".equals(app) || app ==null ? "com.topway.vod.VodPlayerActivity" : app;
        }

        public void setApp(String app) {
            this.app = app;
        }

        public String getServicetype() {
             return servicetype = "".equals(servicetype) || servicetype ==null ? "1" : servicetype;
        }

        public void setServicetype(String servicetype) {
            this.servicetype = servicetype;
        }

        public String getProvider_id() {
            return provider_id = "".equals(provider_id) || provider_id ==null ? "0" : provider_id;

        }

        public void setProvider_id(String provider_id) {
            this.provider_id = provider_id;
        }

        public String getCategory_id() {
            return category_id = "".equals(category_id) || category_id ==null ? "0" : category_id;

        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getCategory_name() {
            return category_name = "".equals(category_name) || category_name ==null ? "" : category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getAsset_id() {
            return asset_id = "".equals(asset_id) || asset_id ==null ? "TWSX0000000009063957" : asset_id;
        }

        public void setAsset_id(String asset_id) {
            this.asset_id = asset_id;
        }

        public String getAsset_name() {
            return asset_name = "".equals(asset_name) || asset_name ==null ? "虎牙一周秀第二期" : asset_name;
        }

        public void setAsset_name(String asset_name) {
            this.asset_name = asset_name;
        }

        public String getEpisodes() {
            return episodes = "".equals(episodes) || episodes ==null ? "" : episodes;
        }

        public void setEpisodes(String episodes) {
            this.episodes = episodes;
        }

        public String getVodepisodes() {
            return vodepisodes = "".equals(vodepisodes) || vodepisodes ==null ? "" : vodepisodes;
        }

        public void setVodepisodes(String vodepisodes) {
            this.vodepisodes = vodepisodes;
        }

        public String getProgram_time() {
            return program_time = "".equals(program_time) || program_time ==null ? "3229000" : program_time;
        }

        public void setProgram_time(String program_time) {
            this.program_time = program_time;
        }

        public String getVer() {
            return ver = "".equals(ver) || ver ==null ? "2.0.78" : ver;
        }

        public void setVer(String ver) {
            this.ver = ver;
        }

        public String getCurtime() {
            return curtime = "".equals(curtime) || curtime ==null ? simpleDateFormat.format(new Date()) : curtime;
        }

        public void setCurtime(String curtime) {
            this.curtime = curtime;
        }

        public String getApp_id() {
            return app_id = "".equals(app_id) || app_id ==null ? "ComponentInfo{com.topway.vod/com.topway.vod.ui.DramaDetailsActivityNew}" : app_id;
        }

        public void setApp_id(String app_id) {
            this.app_id = app_id;
        }

        public String getPlay_time() {
            return play_time = "".equals(play_time) || play_time ==null ? "52" : play_time;
        }

        public void setPlay_time(String play_time) {
            this.play_time = play_time;
        }

        public String getType() {
            return type = "".equals(type) || type ==null ? "1" : type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPage_path() {
            return page_path = "".equals(page_path) || page_path ==null ? "MANU0000000000000591" : page_path;
        }

        public void setPage_path(String page_path) {
            this.page_path = page_path;
        }

        public String getSource_path() {
            return source_path = "".equals(source_path) || source_path ==null ? "MANU0000000000000591" : source_path;
        }

        public void setSource_path(String source_path) {
            this.source_path = source_path;
        }

        public String getPage_name() {
            return page_name = "".equals(page_name) || page_name ==null ? "MANU0000000000284674" : page_name;
        }

        public void setPage_name(String page_name) {
            this.page_name = page_name;
        }

        public void setSingerName(String singerName) {
            this.singerName = singerName;
        }
        public String getSingerName() {
            return singerName = "".equals(singerName) || singerName ==null ? "" : singerName;

        }

        public void setDuration(int duration) {
            this.duration = duration;
        }
        public int getDuration() {
            return 0;
        }

        public void setVodId(String vodId) {
            this.vodId = vodId;
        }
        public String getVodId() {
            return vodId = "".equals(vodId) || vodId ==null ? "0" : vodId;

        }

        public void setIsFree(int isFree) {
            this.isFree = isFree;
        }
        public int getIsFree() {
            return 0;
        }

        public void setFreeTime(int freeTime) {
            this.freeTime = freeTime;
        }
        public int getFreeTime() {
            return 0;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name = "".equals(name) || name ==null ? "虎牙一周秀第二期" : name;
        }

        public void setMvMid(String mvMid) {
            this.mvMid = mvMid;
        }
        public String getMvMid() {
            return mvMid = "".equals(mvMid) || mvMid ==null ? "0" : mvMid;

        }

        public void setCategory(String category) {
            this.category = category;
        }
        public String getCategory() {
            return category = "".equals(category) || category ==null ? "0" : category;
        }

        public void setStatus(String status) {
            this.status = status;
        }
        public String getStatus() {
            return status = "".equals(status) || status ==null ? "0" : status;
        }

    }
}