package com.aryan.stumps11.ApiModel.profile.createTeamTamp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamTempPayload {

    @SerializedName("teamId")
    @Expose
    private String teamId;
    @SerializedName("player11")
    @Expose
    private List<TeamTempPlayer11> teamTempPlayer11s = null;
    @SerializedName("userId")
    @Expose
    private String userId;


    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public List<TeamTempPlayer11> getTeamTempPlayer11s() {
        return teamTempPlayer11s;
    }

    public void setTeamTempPlayer11s(List<TeamTempPlayer11> teamTempPlayer11s) {
        this.teamTempPlayer11s = teamTempPlayer11s;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
