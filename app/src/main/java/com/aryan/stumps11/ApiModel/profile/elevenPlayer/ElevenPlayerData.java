package com.aryan.stumps11.ApiModel.profile.elevenPlayer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ElevenPlayerData {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("teamId")
    @Expose
    private Integer teamId;
    @SerializedName("player11")
    @Expose
    private List<ElevenPlayer11> elevenPlayer11List = null;
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("totalPoint")
    @Expose
    private Integer totalPoint;
    @SerializedName("contest")
    @Expose
    private ElevenPlayerContext elevenPlayerContext;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public List<ElevenPlayer11> getElevenPlayer11List() {
        return elevenPlayer11List;
    }

    public void setElevenPlayer11List(List<ElevenPlayer11> elevenPlayer11List) {
        this.elevenPlayer11List = elevenPlayer11List;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(Integer totalPoint) {
        this.totalPoint = totalPoint;
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

    public ElevenPlayerContext getElevenPlayerContext() {
        return elevenPlayerContext;
    }

    public void setElevenPlayerContext(ElevenPlayerContext elevenPlayerContext) {
        this.elevenPlayerContext = elevenPlayerContext;
    }
}
