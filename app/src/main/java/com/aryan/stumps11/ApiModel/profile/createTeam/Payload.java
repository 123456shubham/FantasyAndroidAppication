package com.aryan.stumps11.ApiModel.profile.createTeam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Payload {

    @SerializedName("teamId")
    @Expose
    private String teamId;
    @SerializedName("player11")
    @Expose
    private List<Player11> player11 = null;
    @SerializedName("userId")
    @Expose
    private String userId;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public List<Player11> getPlayer11() {
        return player11;
    }

    public void setPlayer11(List<Player11> player11) {
        this.player11 = player11;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
