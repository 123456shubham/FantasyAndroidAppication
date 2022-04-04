package com.aryan.stumps11.comingMatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Teama {

    @SerializedName("team_id")
    @Expose
    private Integer teamId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("short_name")
    @Expose
    private String shortName;
    @SerializedName("logo_url")
    @Expose
    private String logoUrl;
    @SerializedName("scores_full")
    @Expose
    private String scoresFull;
    @SerializedName("scores")
    @Expose
    private String scores;
    @SerializedName("overs")
    @Expose
    private String overs;
    public Integer getTeamId() {
        return teamId;
    }
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getShortName() {
        return shortName;
    }
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    public String getLogoUrl() {
        return logoUrl;
    }
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
    public String getScoresFull() {
        return scoresFull;
    }
    public void setScoresFull(String scoresFull) {
        this.scoresFull = scoresFull;
    }
    public String getScores() {
        return scores;
    }
    public void setScores(String scores) {
        this.scores = scores;
    }
    public String getOvers() {
        return overs;
    }
    public void setOvers(String overs) {
        this.overs = overs;
    }

}
