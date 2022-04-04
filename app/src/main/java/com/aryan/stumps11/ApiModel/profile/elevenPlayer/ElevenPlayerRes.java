package com.aryan.stumps11.ApiModel.profile.elevenPlayer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ElevenPlayerRes {


    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private ElevenPlayerData elevenPlayerData;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ElevenPlayerData getElevenPlayerData() {
        return elevenPlayerData;
    }

    public void setElevenPlayerData(ElevenPlayerData elevenPlayerData) {
        this.elevenPlayerData = elevenPlayerData;
    }
}
