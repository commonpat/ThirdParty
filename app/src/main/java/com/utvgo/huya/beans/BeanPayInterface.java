package com.utvgo.huya.beans;

import java.util.List;

/**
 * Created by dev on 2018/1/22.
 */

public class BeanPayInterface {


    /**
     * ret : 0
     * retInfo : OK
     * datas : [{"version":"V001","pramKey":"NET_INNER_IP","pramValue":"10.10.10.10","rank":0},{"version":"V001","pramKey":"NET_OUTER_IP","pramValue":"8.8.8.8","rank":0},{"version":"V001","pramKey":"PAY_ADDR","pramValue":"http://epg.interface.gzgd/nn_cms/data/webapp/jmxxb/iPG/common/spay.html","rank":0},{"version":"V001","pramKey":"LOGICNUM_URL","pramValue":"http://10.69.5.199:8080/ulms/logicInfoList","rank":0},{"version":"V001","pramKey":"SEVEN_PLAYBACK_KEY_FML","pramValue":"#Intent;action=com.gzgd.webapp.start;launchFlags=0x10000000;S.broadcast=false;S.url=http://epg.interface.gzgd/nn_cms/data/webapp/jmxxb/fml2/template/btv/index.htm;end","rank":0},{"version":"V001","pramKey":"VOD_PLAY_ADDR","pramValue":"http://epg.interface.gzgd/nn_cms/data/webapp/jmxxb/fml2/template/vodctrl_new/vodplay.htm","rank":0},{"version":"V001","pramKey":"VOD_PAYMENT_ADDR","pramValue":"tvtaobao://home?module=common&page=https://tvos.taobao.com/wow/yunos/act/guizhou&from=all_gzzw_1122¬showloading=true","rank":0},{"version":"V001","pramKey":"VOD_ELF_PLAY_ADDR","pramValue":"http://epg.interface.gzgd/nn_cms/data/webapp/jmxxb/elf/template/vodctrl_new/vodplay.htm","rank":0},{"version":"V001","pramKey":"SEVEN_PLAYBACK","pramValue":"#Intent;action=com.gzgd.webapp.start;launchFlags=0x10000000;S.broadcast=false;S.url=http://epg.interface.gzgd/nn_cms/data/webapp/jmxxb/elf/template/btv/index.html;end","rank":0},{"version":"V001","pramKey":"PING_OUTER_IP","pramValue":"8.8.8.8:80","rank":0},{"version":"V001","pramKey":"PING_INNER_IP","pramValue":"10.10.10.10:80","rank":0},{"version":"V001","pramKey":"DEVICE_AUTH","pramValue":"http://aaa.interface.gzgd","rank":0},{"version":"V001","pramKey":"WEATHER_AREA","pramValue":"32:438&33:439&34:437&35:812&36:809&37:808&38:811&39:810","rank":0},{"version":"V001","pramKey":"ADP_URL","pramValue":"http://58.251.142.157:9200/&ULMS_URL=http://10.69.5.199:8080/ulms/logicInfoList","rank":0},{"version":"V001","pramKey":"WEATHER_URL","pramValue":"http://10.2.4.187","rank":0},{"version":"V001","pramKey":"WEATHER_PICTURE_URL","pramValue":"http://10.2.4.199","rank":0},{"version":"V001","pramKey":"LAN_URL","pramValue":"10.2.4.60","rank":0},{"version":"V001","pramKey":"WLAN_URL","pramValue":"http://www.baidu.com","rank":0},{"version":"V001","pramKey":"DEPG_URL","pramValue":"http://10.69.5.197:9595","rank":0},{"version":"V001","pramKey":"ADP_YAHA_URL","pramValue":"http://121.14.85.221:9400/ott-ads/","rank":0},{"version":"V001","pramKey":"AUTH_VOD_URL","pramValue":"http://172.20.37.16:8080","rank":0},{"version":"V001","pramKey":"LOGIC_URL","pramValue":"http://cibn-ulms.coship.com","rank":0},{"version":"V001","pramKey":"USM_URL","pramValue":"http://172.18.93.21:9282/services/BM1Service","rank":0},{"version":"V001","pramKey":"playURLShare_Time","pramValue":"7200","rank":0},{"version":"V001","pramKey":"playURLExpire_Time","pramValue":"3600","rank":0},{"version":"V001","pramKey":"GAME_URL","pramValue":"http://payorder.coship.com","rank":0},{"version":"V001","pramKey":"FUC_URL","pramValue":"http://passport.coship.com","rank":0},{"version":"V001","pramKey":"SZ","pramValue":"http://cibn-live.coship.com:8090/","rank":0},{"version":"V001","pramKey":"SPECIALACT_URL","pramValue":"http://cibn-zt.longvisionmedia.com:8180","rank":0},{"version":"V001","pramKey":"SEARCH_URL","pramValue":"http://cibn-search.coship.com:9696","rank":0},{"version":"V001","pramKey":"IUC_URL","pramValue":"http://127.0.0.1:8080","rank":0},{"version":"V001","pramKey":"ISS_URL","pramValue":"http://cibn-iss1.longvisionmedia.com:8088","rank":0},{"version":"V001","pramKey":"EPG_URL","pramValue":"http://gzgd.ao.com","rank":0},{"version":"V001","pramKey":"CIBN_URL","pramValue":"http://tms.cibntest.ysten.com:8080","rank":0},{"version":"V001","pramKey":"BJ","pramValue":"http://cibn-live-bj.coship.com:8090/","rank":0},{"version":"V001","pramKey":"APP_URL","pramValue":"http://app.longvisionmedia.com:80","rank":0},{"version":"V001","pramKey":"APP_UPDATE_URL","pramValue":"http://10.69.5.210","rank":0},{"version":"V001","pramKey":"ADS_URL","pramValue":"http://cibn-ad.longvisionmedia.com:8080","rank":0}]
     */

    private String ret;
    private String retInfo;
    private List<DatasBean> datas;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getRetInfo() {
        return retInfo;
    }

    public void setRetInfo(String retInfo) {
        this.retInfo = retInfo;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * version : V001
         * pramKey : NET_INNER_IP
         * pramValue : 10.10.10.10
         * rank : 0
         */

        private String version;
        private String pramKey;
        private String pramValue;
        private int rank;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getPramKey() {
            return pramKey;
        }

        public void setPramKey(String pramKey) {
            this.pramKey = pramKey;
        }

        public String getPramValue() {
            return pramValue;
        }

        public void setPramValue(String pramValue) {
            this.pramValue = pramValue;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }
    }
}
