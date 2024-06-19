package com.utvgo.handsome.bean;

/**
 * @author wzb
 * @description:
 * @date : 2022/9/4 15:55
 */
public class BeanGetPlayUrl {
    /**
     * playFlag : 1
     * anCiFlag : 0
     * playUrl : rtsp://192.168.14.60/88888888/16/20180220/271883269/271883269.ts?rrsip=192.168.14.60&SRMSessionID=20180614101626!1!1!20028882000365145!9950000003120164!172.18.5.88&PurchaseToken=&NetType=IP&accountinfo=,9950000003120164,172.18.5.88,&SRMSIP=192.168.13.11&SRMSPORT=33700?/H.264^0^-1^春节坚守日记：“快递小哥”的默默坚守^185^1^1
     * reportUrl : http://192.168.14.113:8082/EPG/jsp/STBStateReport.jsp?sessID=20180614101626!1!1!20028882000365145!9950000003120164!172.18.5.88
     */

    private String playFlag;
    private String anCiFlag;
    private String playUrl;
    private String reportUrl;
    private String message;
    private String confirmUrl;
    private int retCode;
    private String parentVodId;

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getParentVodId() {
        return parentVodId;
    }

    public void setParentVodId(String parentVodId) {
        this.parentVodId = parentVodId;
    }

    public String getConfirmUrl() {
        return confirmUrl;
    }

    public void setConfirmUrl(String confirmUrl) {
        this.confirmUrl = confirmUrl;
    }

    public String getPlayFlag() {
        return playFlag;
    }

    public void setPlayFlag(String playFlag) {
        this.playFlag = playFlag;
    }

    public String getAnCiFlag() {
        return anCiFlag;
    }

    public void setAnCiFlag(String anCiFlag) {
        this.anCiFlag = anCiFlag;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
