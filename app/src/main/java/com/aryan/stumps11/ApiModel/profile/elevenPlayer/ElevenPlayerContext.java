package com.aryan.stumps11.ApiModel.profile.elevenPlayer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ElevenPlayerContext {


    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("teamA_tittle")
    @Expose
    private String teamATittle;
    @SerializedName("teamB_tittle")
    @Expose
    private String teamBTittle;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeamATittle() {
        return teamATittle;
    }

    public void setTeamATittle(String teamATittle) {
        this.teamATittle = teamATittle;
    }

    public String getTeamBTittle() {
        return teamBTittle;
    }

    public void setTeamBTittle(String teamBTittle) {
        this.teamBTittle = teamBTittle;
    }
}
