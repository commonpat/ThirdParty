package com.utvgo.huya.beans;

import java.io.Serializable;
import java.util.List;

public class ProgramListData implements Serializable {
    private String gridType = "1";
    private String channelName = "";
    private String imageProfix = "";
    private List<ProgramInfoBase> programs;

    public String getGridType() {
        return gridType;
    }

    public void setGridType(String gridType) {
        this.gridType = gridType;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getImageProfix() {
        return imageProfix;
    }

    public void setImageProfix(String imageProfix) {
        this.imageProfix = imageProfix;
    }

    public List<ProgramInfoBase> getPrograms() {
        return programs;
    }

    public void setPrograms(List<ProgramInfoBase> programs) {
        this.programs = programs;
    }
}
