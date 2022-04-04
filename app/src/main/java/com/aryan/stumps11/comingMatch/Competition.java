package com.aryan.stumps11.comingMatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Competition {
    @SerializedName("cid")
    @Expose
    private Integer cid;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("abbr")
    @Expose
    private String abbr;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("match_format")
    @Expose
    private String matchFormat;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("season")
    @Expose
    private String season;
    @SerializedName("datestart")
    @Expose
    private String datestart;
    @SerializedName("dateend")
    @Expose
    private String dateend;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("total_matches")
    @Expose
    private String totalMatches;
    @SerializedName("total_rounds")
    @Expose
    private String totalRounds;
    @SerializedName("total_teams")
    @Expose
    private String totalTeams;
    public Integer getCid() {
        return cid;
    }
    public void setCid(Integer cid) {
        this.cid = cid;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAbbr() {
        return abbr;
    }
    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getMatchFormat() {
        return matchFormat;
    }
    public void setMatchFormat(String matchFormat) {
        this.matchFormat = matchFormat;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getSeason() {
        return season;
    }
    public void setSeason(String season) {
        this.season = season;
    }
    public String getDatestart() {
        return datestart;
    }
    public void setDatestart(String datestart) {
        this.datestart = datestart;
    }
    public String getDateend() {
        return dateend;
    }
    public void setDateend(String dateend) {
        this.dateend = dateend;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getTotalMatches() {
        return totalMatches;
    }
    public void setTotalMatches(String totalMatches) {
        this.totalMatches = totalMatches;
    }
    public String getTotalRounds() {
        return totalRounds;
    }
    public void setTotalRounds(String totalRounds) {
        this.totalRounds = totalRounds;
    }
    public String getTotalTeams() {
        return totalTeams;
    }
    public void setTotalTeams(String totalTeams) {
        this.totalTeams = totalTeams;
    }
}
