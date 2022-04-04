package com.aryan.stumps11.ApiModel.profile.context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContextData {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("match_id")
    @Expose
    private String matchId;
    @SerializedName("categorieId")
    @Expose
    private String categorieId;
    @SerializedName("totalMember")
    @Expose
    private Integer totalMember;
    @SerializedName("winningPrice")
    @Expose
    private Integer winningPrice;
    @SerializedName("winningPercentUser")
    @Expose
    private Integer winningPercentUser;
    @SerializedName("entryPrice")
    @Expose
    private Integer entryPrice;
    @SerializedName("contestType")
    @Expose
    private String contestType;
    @SerializedName("date_start")
    @Expose
    private String dateStart;
    @SerializedName("date_end")
    @Expose
    private String dateEnd;
    @SerializedName("timestamp_start")
    @Expose
    private String timestampStart;
    @SerializedName("timestamp_end")
    @Expose
    private String timestampEnd;
    @SerializedName("ranks")
    @Expose
    private List<ContextRank> ranks = null;
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

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(String categorieId) {
        this.categorieId = categorieId;
    }

    public Integer getTotalMember() {
        return totalMember;
    }

    public void setTotalMember(Integer totalMember) {
        this.totalMember = totalMember;
    }

    public Integer getWinningPrice() {
        return winningPrice;
    }

    public void setWinningPrice(Integer winningPrice) {
        this.winningPrice = winningPrice;
    }

    public Integer getWinningPercentUser() {
        return winningPercentUser;
    }

    public void setWinningPercentUser(Integer winningPercentUser) {
        this.winningPercentUser = winningPercentUser;
    }

    public Integer getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(Integer entryPrice) {
        this.entryPrice = entryPrice;
    }

    public String getContestType() {
        return contestType;
    }

    public void setContestType(String contestType) {
        this.contestType = contestType;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getTimestampStart() {
        return timestampStart;
    }

    public void setTimestampStart(String timestampStart) {
        this.timestampStart = timestampStart;
    }

    public String getTimestampEnd() {
        return timestampEnd;
    }

    public void setTimestampEnd(String timestampEnd) {
        this.timestampEnd = timestampEnd;
    }

    public List<ContextRank> getRanks() {
        return ranks;
    }

    public void setRanks(List<ContextRank> ranks) {
        this.ranks = ranks;
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
