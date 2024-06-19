package com.utvgo.handsome.bean;

/**
 * @author wzb
 * @description:
 * @date : 2020/9/29 8:56
 */
public class BeanContent {
    private String cp_id, action,source,cp_video_id,video_name,is_preview,video_type,cp_package_name,cp_video_index;

    public BeanContent(String action, String source, String cp_video_id, String video_type, String cp_package_name, String cp_video_index, String is_preview) {
        this.cp_id = cp_id;
        this.action = action;
        this.source = source;
        this.cp_video_id = cp_video_id;
        this.video_name = video_name;
        this.is_preview = is_preview;
        this.video_type = video_type;
        this.cp_package_name = cp_package_name;
        this.cp_video_index = cp_video_index;
    }
    public BeanContent(String action, String source, String cp_video_id, String video_type, String cp_package_name, String cp_video_index) {
        this.cp_id = cp_id;
        this.action = action;
        this.source = source;
        this.cp_video_id = cp_video_id;
        this.video_name = video_name;
        this.is_preview = is_preview;
        this.video_type = video_type;
        this.cp_package_name = cp_package_name;
        this.cp_video_index = cp_video_index;
    }

    public String getCp_video_id() {
        return cp_video_id;
    }

    public void setCp_video_id(String cp_video_id) {
        this.cp_video_id = cp_video_id;
    }

    public String getCp_video_index() {
        return cp_video_index;
    }

    public void setCp_video_index(String cp_video_index) {
        this.cp_video_index = cp_video_index;
    }

    public String getCp_id() {
        return cp_id;
    }

    public void setCp_id(String cp_id) {
        this.cp_id = cp_id;
    }

    public String getIs_preview() {
        return is_preview;
    }

    public void setIs_preview(String is_preview) {
        this.is_preview = is_preview;
    }

    public void setAction(String action) {
        this.action = action;
    }
    public String getAction() {
        return action;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getSource() {
        return source;
    }
    public void setVideo_name(String video_name) {
        this.video_name = video_name;
    }
    public String getVideo_name() {
        return video_name;
    }
    public void setVideo_type(String video_type) {
        this.video_type = video_type;
    }
    public String getVideo_type() {
        return video_type;
    }
    public void setCp_package_name(String cp_package_name) {
        this.cp_package_name = cp_package_name;
    }
    public String getCp_package_name() {
        return cp_package_name;
    }
}