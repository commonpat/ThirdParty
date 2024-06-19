package com.utvgo.huya.constant;

/**
 * Created by Administrator on 2016/3/17.
 */
public class ConstantEnumHuya {

    public static final int TAGReWaiLiuqAlbumMid = 3000;

    public static final int TAGRecommendExit = 3007;
    public static final int ReqActivity = 3008;
    public static final String WEB = "0";
    public static final String AlbumDetail = "1";
    public static final String MVAlbum = "2";
    public static final String MVSingleVideo = "3";
    public static final String AudioList = "4";
    public static final String VideoList = "5";
    public static final String SINGER = "6";
    public static final String TOPIC = "7";
    public static final String ACTIVITY = "8";
    public static final String COLLECTT = "9";
    public static final String RECORDLIST = "10";
    public static final String TOPICCOLLECTION = "12";
    public static final String LIVE = "13";
    public static final String More = "14";
    public static final String Return = "15";
    public static final String Focus = "16";
    public static final String SignActivity = "18";


    public static final String CHILDSELECT1 = "45";
    public static final String CHILDSELECT2 = "46";
    public static final String CHILDSELECT3 = "47";


    public static final String Asset_Id = "电竞";
    public static final String Category_Id = "游戏";

    /**
     * 瀑布流首页
     * */
    public static final String INDEXONE = "33";
    public static final String INDEXTWO = "34";
    public static final String INDEXTHREE = "35";



    /**
     * 天威瀑布流首页
     * */
    public static final String TINDEXONE = "48";
    public static final String TINDEXTWO = "49";
    public static final String TINDEXTHREE = "50";
    public static final String TINDEXFOUR = "51";
    public static final String TINDEXFIVE = "52";
    public static final String TINDEXONENEW = "53";
    public static final String TINDEXTWONEW = "54";
    public static final String TINDEXTHREENEW = "55";
    public static final String TINDEXFOURNEW = "56";
    public static final String TINDEXFIVENEW = "57";
    public static final String TINDEXSIXNEW = "58";
    public static final String TINDEXSEVENNEW = "59";
    public static final String TINDEXEIGHTNEW = "60";
    public static final String TINDEXNINENEW = "61";


    public enum MediaType {
        audio, video
    }
    public enum ShowType {
        web,albumDetail,mvAlbum,topic;
        public static int showType2Id(String s){
            switch (s) {
                case "0":
            }
            return 0;
        }
    }
    public enum AlbumButton {
        web,focus,btnBack,btnMore;
        public static AlbumButton forValue(String s){
            AlbumButton albumButton = web;
            switch (s){
                case "0":
                    albumButton = web;
                    break;
                case "1":
                    albumButton = focus;
                    break;
                case "2":
                    albumButton = btnBack;
                    break;
                case "3":
                    albumButton = btnMore;
                    break;
            }
            return albumButton;
        }

    }

    public static final int DATA_TYPE_PORTAL=11;//主页事件
    public static final int DATA_TYPE_BTV=12;//回看
    public static final int DATA_TYPE_VOD_WATCHING=15;//点播播放
    public static final int DATA_TYPE_VIDEO_ACTION=18;//视频控制
    public static final int DATA_TYPE_PAGE_VIEW=19;//点播页面事件
    public static final int DATA_TYPE_PAY=21;//订购事件
    public static final int DATA_TYPE_APP_START_END=257;//应用启动和关闭事件
    public static final int DATA_TYPE_APP_INFO=258;//机顶盒信息
    public static final int DATA_TYPE_APP_CRASH=254;//应用崩溃
    public static final int DATA_TYPE_COLLECTION=256;//应用通用广播


    public static final int APP_START=0;//应用开启
    public static final int APP_END=1;//应用退出

    public static final int USER_ACTION_VIEW=0;//订购展示
    public static final int USER_ACTION_BUY=1;//执行订购
    public static final int USER_ACTION_CANCEL=2;//订购取消


    public static final int VOD_PAGEVIEW=1;////页面事件
    public static final int VOD_SP_PLAY=2;//播放事件
    public static final int VOD_SP_SEARCH=3;//搜索事件
    public static final int VOD_SP_COLLECTION_RECOMMEND=5;//收藏&推荐事件
    public static final int VOD_SP_PORTAL=6;//页面点击位置事件
    public static final int PRODUCT_BUY=10;//产品订购;
    public static final int PRODUCT_BUY_PAGEVIEW=11;//产品订购页面



    public static final int KEY_HOME=0;//home键退出
    public static final int KEY_BACK=1;//返回键退出
    public static final int KEY_LOAD_FINISH=2;//


    public static final String INIT = "p_system_init";////入口初始化
    public static final String INDEX="p_index";//首页
    public static final String VIDEO_DETAIL="p_detail_video";//影片详情页
    public static final String PLAYER="p_player";//播放页
    public static final String SEARCH="p_search";//搜索页面
    public static final String ORDER="p_product_order";//r产品订购页面;
    public static final String STARLIST="p_list_star";//r明星列表页
    public static final String STARTDETAIL = "p_detail_star";//明星详情页
    public static final String VIDEOLIST = "p_list_video";//影片列表页
    public static final String USERCENTER = "p_user_center";//用户中心页面
    public static final String COLLECT ="p_user_collect";//我的片单页面
    public static final String HISTORY = "p_history";//历史记录页
    public static final String LOGIN="p_login";//用户登录页面
    public static final String REGISTER="p_register";//用户注册页面
    public static final String FILLTERVIDEO="p_fillter_video";//筛选视频

}
