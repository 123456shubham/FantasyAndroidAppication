package com.aryan.stumps11.ApiModel.profile.createTeam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreateTeamReq {

    @SerializedName("teamId")
    @Expose
    private String teamId;
    @SerializedName("player11")
    @Expose
    private List<CreateReqData> player11 = null;

    @SerializedName("cid")
    @Expose
    private String cid;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public List<CreateReqData> getPlayer11() {
        return player11;
    }

    public void setPlayer11(List<CreateReqData> player11) {
        this.player11 = player11;
    }
}
