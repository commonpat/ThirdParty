package com.utvgo.huya.beans;

import java.util.List;

public class BeanCollectPageList
{
    private String code;

    private Data data;

    private int totalPage;

    private int count;

    private int currentPage;

    private String message;

    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setData(Data data){
        this.data = data;
    }
    public Data getData(){
        return this.data;
    }
    public void setTotalPage(int totalPage){
        this.totalPage = totalPage;
    }
    public int getTotalPage(){
        return this.totalPage;
    }
    public void setCount(int count){
        this.count = count;
    }
    public int getCount(){
        return this.count;
    }
    public void setCurrentPage(int currentPage){
        this.currentPage = currentPage;
    }
    public int getCurrentPage(){
        return this.currentPage;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }

    public static class Data
    {
        private List<CollectList> collectList;

        public void setCollectList(List<CollectList> collectList){
            this.collectList = collectList;
        }
        public List<CollectList> getCollectList(){
            return this.collectList;
        }
        public  static class CollectList
        {
            private String keyNo;

            private String programName;

            private String videoName;

            private String multiSetType;

            private int videoId;

            private int id;

            private int playPoint;

            private int programId;

            private int channelId;

            public void setKeyNo(String keyNo){
                this.keyNo = keyNo;
            }
            public String getKeyNo(){
                return this.keyNo;
            }
            public void setProgramName(String programName){
                this.programName = programName;
            }
            public String getProgramName(){
                return this.programName;
            }
            public void setVideoName(String videoName){
                this.videoName = videoName;
            }
            public String getVideoName(){
                return this.videoName;
            }
            public void setMultiSetType(String multiSetType){
                this.multiSetType = multiSetType;
            }
            public String getMultiSetType(){
                return this.multiSetType;
            }
            public void setVideoId(int videoId){
                this.videoId = videoId;
            }
            public int getVideoId(){
                return this.videoId;
            }
            public void setId(int id){
                this.id = id;
            }
            public int getId(){
                return this.id;
            }
            public void setPlayPoint(int playPoint){
                this.playPoint = playPoint;
            }
            public int getPlayPoint(){
                return this.playPoint;
            }
            public void setProgramId(int programId){
                this.programId = programId;
            }
            public int getProgramId(){
                return this.programId;
            }
            public void setChannelId(int channelId){
                this.channelId = channelId;
            }
            public int getChannelId(){
                return this.channelId;
            }
        }
    }

}




