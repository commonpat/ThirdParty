package com.utvgo.huya.beans;

import android.os.Parcel;
import android.os.Parcelable;

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
            public static class Historys implements Parcelable {
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

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.keyNo);
                    dest.writeInt(this.totalTime);
                    dest.writeString(this.videoName);
                    dest.writeString(this.programName);
                    dest.writeString(this.multiSetType);
                    dest.writeInt(this.videoId);
                    dest.writeInt(this.id);
                    dest.writeInt(this.playPoint);
                    dest.writeInt(this.programId);
                    dest.writeInt(this.channelId);
                }

                public Historys() {
                }

                protected Historys(Parcel in) {
                    this.keyNo = in.readString();
                    this.totalTime = in.readInt();
                    this.videoName = in.readString();
                    this.programName = in.readString();
                    this.multiSetType = in.readString();
                    this.videoId = in.readInt();
                    this.id = in.readInt();
                    this.playPoint = in.readInt();
                    this.programId = in.readInt();
                    this.channelId = in.readInt();
                }

                public static final Parcelable.Creator<Historys> CREATOR = new Parcelable.Creator<Historys>() {
                    @Override
                    public Historys createFromParcel(Parcel source) {
                        return new Historys(source);
                    }

                    @Override
                    public Historys[] newArray(int size) {
                        return new Historys[size];
                    }
                };
            }
        }

    }







