package com.aryan.stumps11.ApiModel.profile.createTeamTamp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateTeamTempDataResponse {
    @SerializedName("payload")
    @Expose
    private TeamTempPayload teamTempPayload;

    public TeamTempPayload getTeamTempPayload() {
        return teamTempPayload;
    }

    public void setTeamTempPayload(TeamTempPayload teamTempPayload) {
        this.teamTempPayload = teamTempPayload;
    }
}
