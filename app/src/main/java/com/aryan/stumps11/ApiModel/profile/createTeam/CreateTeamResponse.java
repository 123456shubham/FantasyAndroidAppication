package com.aryan.stumps11.ApiModel.profile.createTeam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateTeamResponse {

    @SerializedName("payload")
    @Expose
    private Payload payload;

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }
}
