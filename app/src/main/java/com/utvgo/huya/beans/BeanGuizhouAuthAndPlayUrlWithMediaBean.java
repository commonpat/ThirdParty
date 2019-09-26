package com.utvgo.huya.beans;

import java.util.List;

/**
 * Created by dev on 2018/1/17.
 */

public class BeanGuizhouAuthAndPlayUrlWithMediaBean {


    /**
     * result : {"state":"0","new_state":"45","reason":"取串成功","order_url":null}
     * playurl : http://10.21.12.37:5005/nn_vod/nn_x64/aWQ9ODBjODA1OGNiZGNhM2I1YWExMzIzODhlOTE0YTg0ZmImdXJsX2MxPTMyMzAzMTM4MzAzODMwMzYyZjM1NjIzNjM4MzA2NDM5NjE2NDMwNjUzMDY1MzYzNTM0NjI2MTM4MzU2NTM0MzAzNTMwMzY2NTMzMzUzODM5NjIyZjY0MzAzMDMyMzc2NTYxNzY2ZTY5NmQ2MjZjNzU2NTcyNjE3OTJkMzAyZTZkMzM3NTM4MDAmbm5fYWs9MDFkNjU0YmMxNTE0OWM3MWVjNmY0MmVjZTBhYzhjZGZhNCZudHRsPTEmbnBpcHM9MTAuMjEuMTYuMTUyOjUxMDAmbmNtc2lkPTEwMDEyMzcmbmdzPTViODdhMjBlMDAwNDc4YjRkODAwN2RkYmYwYzg1Yzk0Jm5uZD1nemdkLmd1aXlhbmcmbnNkPWd6Z2QuY2VudGVyJm5mdD1tM3U4Jm5uX3VzZXJfaWQ9ODg1MTAwNDM2Mjk1NzI5NyZuZHQ9c3RiJm5kdj0xLjAuMC5TVEQuR1pHRC5BVVRPLk9UVDAxLlJlbGVhc2UmbnN0PWlwdHYmbmNhPSUyNm5jb2klM2RTMy1Yc2t5LVdlbkd1YW5nLTEtZW54dW4lMjZuYWklM2RRUU11c2ljTW92aWVJRGQwMDI3ZWF2bmltX2JsdWVfcmF5JTI2bm5fY3AlM2QxMDEwMg,,/80c8058cbdca3b5aa132388e914a84fb.m3u8
     * video : {"id":"5b680da62c60b38b6186dfc96727cfae","cp_id":"10102","is_support_ncl":0,"index":{"id":"5b680de238d45f3e80d79c303a30197d","index_num":0,"media":{"id":"5b680e1e1ae96d084d353c1376d0a721","url":"http://10.21.12.37:5005/nn_vod/nn_x64/aWQ9ODBjODA1OGNiZGNhM2I1YWExMzIzODhlOTE0YTg0ZmImdXJsX2MxPTMyMzAzMTM4MzAzODMwMzYyZjM1NjIzNjM4MzA2NDM5NjE2NDMwNjUzMDY1MzYzNTM0NjI2MTM4MzU2NTM0MzAzNTMwMzY2NTMzMzUzODM5NjIyZjY0MzAzMDMyMzc2NTYxNzY2ZTY5NmQ2MjZjNzU2NTcyNjE3OTJkMzAyZTZkMzM3NTM4MDAmbm5fYWs9MDFkNjU0YmMxNTE0OWM3MWVjNmY0MmVjZTBhYzhjZGZhNCZudHRsPTEmbnBpcHM9MTAuMjEuMTYuMTUyOjUxMDAmbmNtc2lkPTEwMDEyMzcmbmdzPTViODdhMjBlMDAwNDc4YjRkODAwN2RkYmYwYzg1Yzk0Jm5uZD1nemdkLmd1aXlhbmcmbnNkPWd6Z2QuY2VudGVyJm5mdD1tM3U4Jm5uX3VzZXJfaWQ9ODg1MTAwNDM2Mjk1NzI5NyZuZHQ9c3RiJm5kdj0xLjAuMC5TVEQuR1pHRC5BVVRPLk9UVDAxLlJlbGVhc2UmbnN0PWlwdHYmbmNhPSUyNm5jb2klM2RTMy1Yc2t5LVdlbkd1YW5nLTEtZW54dW4lMjZuYWklM2RRUU11c2ljTW92aWVJRGQwMDI3ZWF2bmltX2JsdWVfcmF5JTI2bm5fY3AlM2QxMDEwMg,,/80c8058cbdca3b5aa132388e914a84fb.m3u8","url_backup":"","filetype":"m3u8","quality":"std","begin_play_time":"","ts_limit_min":"","ts_default_pos":"","play_time_len":"","dimensions":0,"file_id":"QQMusicMovieIDd0027eavnim_blue_ray","import_source":"AM南方传媒","original_id":"QQMusicMovieIDd0027eavnim_blue_ray","is_video":1,"is_support_ncl":0,"media_other":[]}},"metadata":"","index_caption_data":"","drm_list":null,"media_other":[]}
     * arg_list : {"client_ip":"10.69.45.50","is_support_preview":0,"preview_time":0,"preview_infos":"","preview_free_index":"","is_support_coupon":0,"coupon_type_ids":"","coupon_info":"","get_playurl_error":"ok"}
     */

    private ResultBean result;
    private String playurl;
    private VideoBean video;
    private ArgListBean arg_list;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getPlayurl() {
        return playurl;
    }

    public void setPlayurl(String playurl) {
        this.playurl = playurl;
    }

    public VideoBean getVideo() {
        return video;
    }

    public void setVideo(VideoBean video) {
        this.video = video;
    }

    public ArgListBean getArg_list() {
        return arg_list;
    }

    public void setArg_list(ArgListBean arg_list) {
        this.arg_list = arg_list;
    }

    public static class ResultBean {
        /**
         * state : 0
         * new_state : 45
         * reason : 取串成功
         * order_url : null
         */

        private String state;
        private String new_state;
        private String reason;
        private Object order_url;

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getNew_state() {
            return new_state;
        }

        public void setNew_state(String new_state) {
            this.new_state = new_state;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public Object getOrder_url() {
            return order_url;
        }

        public void setOrder_url(Object order_url) {
            this.order_url = order_url;
        }
    }

    public static class VideoBean {
        /**
         * id : 5b680da62c60b38b6186dfc96727cfae
         * cp_id : 10102
         * is_support_ncl : 0
         * index : {"id":"5b680de238d45f3e80d79c303a30197d","index_num":0,"media":{"id":"5b680e1e1ae96d084d353c1376d0a721","url":"http://10.21.12.37:5005/nn_vod/nn_x64/aWQ9ODBjODA1OGNiZGNhM2I1YWExMzIzODhlOTE0YTg0ZmImdXJsX2MxPTMyMzAzMTM4MzAzODMwMzYyZjM1NjIzNjM4MzA2NDM5NjE2NDMwNjUzMDY1MzYzNTM0NjI2MTM4MzU2NTM0MzAzNTMwMzY2NTMzMzUzODM5NjIyZjY0MzAzMDMyMzc2NTYxNzY2ZTY5NmQ2MjZjNzU2NTcyNjE3OTJkMzAyZTZkMzM3NTM4MDAmbm5fYWs9MDFkNjU0YmMxNTE0OWM3MWVjNmY0MmVjZTBhYzhjZGZhNCZudHRsPTEmbnBpcHM9MTAuMjEuMTYuMTUyOjUxMDAmbmNtc2lkPTEwMDEyMzcmbmdzPTViODdhMjBlMDAwNDc4YjRkODAwN2RkYmYwYzg1Yzk0Jm5uZD1nemdkLmd1aXlhbmcmbnNkPWd6Z2QuY2VudGVyJm5mdD1tM3U4Jm5uX3VzZXJfaWQ9ODg1MTAwNDM2Mjk1NzI5NyZuZHQ9c3RiJm5kdj0xLjAuMC5TVEQuR1pHRC5BVVRPLk9UVDAxLlJlbGVhc2UmbnN0PWlwdHYmbmNhPSUyNm5jb2klM2RTMy1Yc2t5LVdlbkd1YW5nLTEtZW54dW4lMjZuYWklM2RRUU11c2ljTW92aWVJRGQwMDI3ZWF2bmltX2JsdWVfcmF5JTI2bm5fY3AlM2QxMDEwMg,,/80c8058cbdca3b5aa132388e914a84fb.m3u8","url_backup":"","filetype":"m3u8","quality":"std","begin_play_time":"","ts_limit_min":"","ts_default_pos":"","play_time_len":"","dimensions":0,"file_id":"QQMusicMovieIDd0027eavnim_blue_ray","import_source":"AM南方传媒","original_id":"QQMusicMovieIDd0027eavnim_blue_ray","is_video":1,"is_support_ncl":0,"media_other":[]}}
         * metadata :
         * index_caption_data :
         * drm_list : null
         * media_other : []
         */

        private String id;
        private String cp_id;
        private int is_support_ncl;
        private IndexBean index;
        private String metadata;
        private String index_caption_data;
        private Object drm_list;
        private List<?> media_other;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCp_id() {
            return cp_id;
        }

        public void setCp_id(String cp_id) {
            this.cp_id = cp_id;
        }

        public int getIs_support_ncl() {
            return is_support_ncl;
        }

        public void setIs_support_ncl(int is_support_ncl) {
            this.is_support_ncl = is_support_ncl;
        }

        public IndexBean getIndex() {
            return index;
        }

        public void setIndex(IndexBean index) {
            this.index = index;
        }

        public String getMetadata() {
            return metadata;
        }

        public void setMetadata(String metadata) {
            this.metadata = metadata;
        }

        public String getIndex_caption_data() {
            return index_caption_data;
        }

        public void setIndex_caption_data(String index_caption_data) {
            this.index_caption_data = index_caption_data;
        }

        public Object getDrm_list() {
            return drm_list;
        }

        public void setDrm_list(Object drm_list) {
            this.drm_list = drm_list;
        }

        public List<?> getMedia_other() {
            return media_other;
        }

        public void setMedia_other(List<?> media_other) {
            this.media_other = media_other;
        }

        public static class IndexBean {
            /**
             * id : 5b680de238d45f3e80d79c303a30197d
             * index_num : 0
             * media : {"id":"5b680e1e1ae96d084d353c1376d0a721","url":"http://10.21.12.37:5005/nn_vod/nn_x64/aWQ9ODBjODA1OGNiZGNhM2I1YWExMzIzODhlOTE0YTg0ZmImdXJsX2MxPTMyMzAzMTM4MzAzODMwMzYyZjM1NjIzNjM4MzA2NDM5NjE2NDMwNjUzMDY1MzYzNTM0NjI2MTM4MzU2NTM0MzAzNTMwMzY2NTMzMzUzODM5NjIyZjY0MzAzMDMyMzc2NTYxNzY2ZTY5NmQ2MjZjNzU2NTcyNjE3OTJkMzAyZTZkMzM3NTM4MDAmbm5fYWs9MDFkNjU0YmMxNTE0OWM3MWVjNmY0MmVjZTBhYzhjZGZhNCZudHRsPTEmbnBpcHM9MTAuMjEuMTYuMTUyOjUxMDAmbmNtc2lkPTEwMDEyMzcmbmdzPTViODdhMjBlMDAwNDc4YjRkODAwN2RkYmYwYzg1Yzk0Jm5uZD1nemdkLmd1aXlhbmcmbnNkPWd6Z2QuY2VudGVyJm5mdD1tM3U4Jm5uX3VzZXJfaWQ9ODg1MTAwNDM2Mjk1NzI5NyZuZHQ9c3RiJm5kdj0xLjAuMC5TVEQuR1pHRC5BVVRPLk9UVDAxLlJlbGVhc2UmbnN0PWlwdHYmbmNhPSUyNm5jb2klM2RTMy1Yc2t5LVdlbkd1YW5nLTEtZW54dW4lMjZuYWklM2RRUU11c2ljTW92aWVJRGQwMDI3ZWF2bmltX2JsdWVfcmF5JTI2bm5fY3AlM2QxMDEwMg,,/80c8058cbdca3b5aa132388e914a84fb.m3u8","url_backup":"","filetype":"m3u8","quality":"std","begin_play_time":"","ts_limit_min":"","ts_default_pos":"","play_time_len":"","dimensions":0,"file_id":"QQMusicMovieIDd0027eavnim_blue_ray","import_source":"AM南方传媒","original_id":"QQMusicMovieIDd0027eavnim_blue_ray","is_video":1,"is_support_ncl":0,"media_other":[]}
             */

            private String id;
            private int index_num;
            private MediaBean media;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getIndex_num() {
                return index_num;
            }

            public void setIndex_num(int index_num) {
                this.index_num = index_num;
            }

            public MediaBean getMedia() {
                return media;
            }

            public void setMedia(MediaBean media) {
                this.media = media;
            }

            public static class MediaBean {
                /**
                 * id : 5b680e1e1ae96d084d353c1376d0a721
                 * url : http://10.21.12.37:5005/nn_vod/nn_x64/aWQ9ODBjODA1OGNiZGNhM2I1YWExMzIzODhlOTE0YTg0ZmImdXJsX2MxPTMyMzAzMTM4MzAzODMwMzYyZjM1NjIzNjM4MzA2NDM5NjE2NDMwNjUzMDY1MzYzNTM0NjI2MTM4MzU2NTM0MzAzNTMwMzY2NTMzMzUzODM5NjIyZjY0MzAzMDMyMzc2NTYxNzY2ZTY5NmQ2MjZjNzU2NTcyNjE3OTJkMzAyZTZkMzM3NTM4MDAmbm5fYWs9MDFkNjU0YmMxNTE0OWM3MWVjNmY0MmVjZTBhYzhjZGZhNCZudHRsPTEmbnBpcHM9MTAuMjEuMTYuMTUyOjUxMDAmbmNtc2lkPTEwMDEyMzcmbmdzPTViODdhMjBlMDAwNDc4YjRkODAwN2RkYmYwYzg1Yzk0Jm5uZD1nemdkLmd1aXlhbmcmbnNkPWd6Z2QuY2VudGVyJm5mdD1tM3U4Jm5uX3VzZXJfaWQ9ODg1MTAwNDM2Mjk1NzI5NyZuZHQ9c3RiJm5kdj0xLjAuMC5TVEQuR1pHRC5BVVRPLk9UVDAxLlJlbGVhc2UmbnN0PWlwdHYmbmNhPSUyNm5jb2klM2RTMy1Yc2t5LVdlbkd1YW5nLTEtZW54dW4lMjZuYWklM2RRUU11c2ljTW92aWVJRGQwMDI3ZWF2bmltX2JsdWVfcmF5JTI2bm5fY3AlM2QxMDEwMg,,/80c8058cbdca3b5aa132388e914a84fb.m3u8
                 * url_backup :
                 * filetype : m3u8
                 * quality : std
                 * begin_play_time :
                 * ts_limit_min :
                 * ts_default_pos :
                 * play_time_len :
                 * dimensions : 0
                 * file_id : QQMusicMovieIDd0027eavnim_blue_ray
                 * import_source : AM南方传媒
                 * original_id : QQMusicMovieIDd0027eavnim_blue_ray
                 * is_video : 1
                 * is_support_ncl : 0
                 * media_other : []
                 */

                private String id;
                private String url;
                private String url_backup;
                private String filetype;
                private String quality;
                private String begin_play_time;
                private String ts_limit_min;
                private String ts_default_pos;
                private String play_time_len;
                private int dimensions;
                private String file_id;
                private String import_source;
                private String original_id;
                private int is_video;
                private int is_support_ncl;
                private List<?> media_other;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getUrl_backup() {
                    return url_backup;
                }

                public void setUrl_backup(String url_backup) {
                    this.url_backup = url_backup;
                }

                public String getFiletype() {
                    return filetype;
                }

                public void setFiletype(String filetype) {
                    this.filetype = filetype;
                }

                public String getQuality() {
                    return quality;
                }

                public void setQuality(String quality) {
                    this.quality = quality;
                }

                public String getBegin_play_time() {
                    return begin_play_time;
                }

                public void setBegin_play_time(String begin_play_time) {
                    this.begin_play_time = begin_play_time;
                }

                public String getTs_limit_min() {
                    return ts_limit_min;
                }

                public void setTs_limit_min(String ts_limit_min) {
                    this.ts_limit_min = ts_limit_min;
                }

                public String getTs_default_pos() {
                    return ts_default_pos;
                }

                public void setTs_default_pos(String ts_default_pos) {
                    this.ts_default_pos = ts_default_pos;
                }

                public String getPlay_time_len() {
                    return play_time_len;
                }

                public void setPlay_time_len(String play_time_len) {
                    this.play_time_len = play_time_len;
                }

                public int getDimensions() {
                    return dimensions;
                }

                public void setDimensions(int dimensions) {
                    this.dimensions = dimensions;
                }

                public String getFile_id() {
                    return file_id;
                }

                public void setFile_id(String file_id) {
                    this.file_id = file_id;
                }

                public String getImport_source() {
                    return import_source;
                }

                public void setImport_source(String import_source) {
                    this.import_source = import_source;
                }

                public String getOriginal_id() {
                    return original_id;
                }

                public void setOriginal_id(String original_id) {
                    this.original_id = original_id;
                }

                public int getIs_video() {
                    return is_video;
                }

                public void setIs_video(int is_video) {
                    this.is_video = is_video;
                }

                public int getIs_support_ncl() {
                    return is_support_ncl;
                }

                public void setIs_support_ncl(int is_support_ncl) {
                    this.is_support_ncl = is_support_ncl;
                }

                public List<?> getMedia_other() {
                    return media_other;
                }

                public void setMedia_other(List<?> media_other) {
                    this.media_other = media_other;
                }
            }
        }
    }

    public static class ArgListBean {
        /**
         * client_ip : 10.69.45.50
         * is_support_preview : 0
         * preview_time : 0
         * preview_infos :
         * preview_free_index :
         * is_support_coupon : 0
         * coupon_type_ids :
         * coupon_info :
         * get_playurl_error : ok
         */

        private String client_ip;
        private int is_support_preview;
        private int preview_time;
        private String preview_infos;
        private String preview_free_index;
        private int is_support_coupon;
        private String coupon_type_ids;
        private String coupon_info;
        private String get_playurl_error;

        public String getClient_ip() {
            return client_ip;
        }

        public void setClient_ip(String client_ip) {
            this.client_ip = client_ip;
        }

        public int getIs_support_preview() {
            return is_support_preview;
        }

        public void setIs_support_preview(int is_support_preview) {
            this.is_support_preview = is_support_preview;
        }

        public int getPreview_time() {
            return preview_time;
        }

        public void setPreview_time(int preview_time) {
            this.preview_time = preview_time;
        }

        public String getPreview_infos() {
            return preview_infos;
        }

        public void setPreview_infos(String preview_infos) {
            this.preview_infos = preview_infos;
        }

        public String getPreview_free_index() {
            return preview_free_index;
        }

        public void setPreview_free_index(String preview_free_index) {
            this.preview_free_index = preview_free_index;
        }

        public int getIs_support_coupon() {
            return is_support_coupon;
        }

        public void setIs_support_coupon(int is_support_coupon) {
            this.is_support_coupon = is_support_coupon;
        }

        public String getCoupon_type_ids() {
            return coupon_type_ids;
        }

        public void setCoupon_type_ids(String coupon_type_ids) {
            this.coupon_type_ids = coupon_type_ids;
        }

        public String getCoupon_info() {
            return coupon_info;
        }

        public void setCoupon_info(String coupon_info) {
            this.coupon_info = coupon_info;
        }

        public String getGet_playurl_error() {
            return get_playurl_error;
        }

        public void setGet_playurl_error(String get_playurl_error) {
            this.get_playurl_error = get_playurl_error;
        }
    }
}
