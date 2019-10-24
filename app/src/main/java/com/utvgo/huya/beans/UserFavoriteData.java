package com.utvgo.huya.beans;

import java.io.Serializable;
import java.util.List;

public class UserFavoriteData implements Serializable {

    private List<Item> collectList;

    public void setCollectList(List<Item> collectList){
        this.collectList = collectList;
    }
    public List<Item> getCollectList(){
        return this.collectList;
    }

    public  static class Item implements Serializable
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

        public ProgramInfoBase toProgram()
        {
            ProgramInfoBase programInfoBase = new ProgramInfoBase();
            programInfoBase.setPkId(this.programId);
            programInfoBase.setName(this.programName);
            programInfoBase.setChannelId(this.channelId);
            return programInfoBase;
        }
    }

}




