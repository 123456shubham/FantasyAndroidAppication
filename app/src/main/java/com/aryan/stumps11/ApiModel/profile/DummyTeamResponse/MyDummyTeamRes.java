package com.aryan.stumps11.ApiModel.profile.DummyTeamResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyDummyTeamRes {


    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<MyDummyTeamData> dummyTeamData;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<MyDummyTeamData> getDummyTeamData() {
        return dummyTeamData;
    }

    public void setDummyTeamData(List<MyDummyTeamData> dummyTeamData) {
        this.dummyTeamData = dummyTeamData;
    }
}
