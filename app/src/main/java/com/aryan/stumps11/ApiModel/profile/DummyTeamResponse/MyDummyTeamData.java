package com.aryan.stumps11.ApiModel.profile.DummyTeamResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyDummyTeamData {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("teamId")
    @Expose
    private Integer teamId;
    @SerializedName("player11")
    @Expose
    private List<MyDummyTeamPlayer11> dummyTeamPlayer11s ;
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("totalPoint")
    @Expose
    private Integer totalPoint;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private Integer v;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public List<MyDummyTeamPlayer11> getDummyTeamPlayer11s() {
        return dummyTeamPlayer11s;
    }

    public void setDummyTeamPlayer11s(List<MyDummyTeamPlayer11> dummyTeamPlayer11s) {
        this.dummyTeamPlayer11s = dummyTeamPlayer11s;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(Integer totalPoint) {
        this.totalPoint = totalPoint;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
