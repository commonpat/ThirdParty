package com.utvgo.huya.beans;

import java.util.List;

    public class BeanPlayHistoryPageList
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
            private List<Historys> historys;

            public void setHistorys(List<Historys> historys){
                this.historys = historys;
            }
            public List<Historys> getHistorys(){
                return this.historys;
            }
            public class Historys
            {
                private String keyNo;

                private int totalTime;

                private String videoName;

                private String programName;

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
                public void setTotalTime(int totalTime){
                    this.totalTime = totalTime;
                }
                public int getTotalTime(){
                    return this.totalTime;
                }
                public void setVideoName(String videoName){
                    this.videoName = videoName;
                }
                public String getVideoName(){
                    return this.videoName;
                }
                public void setProgramName(String programName){
                    this.programName = programName;
                }
                public String getProgramName(){
                    return this.programName;
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







