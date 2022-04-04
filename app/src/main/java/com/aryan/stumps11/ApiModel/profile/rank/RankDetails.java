package com.aryan.stumps11.ApiModel.profile.rank;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RankDetails {
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("percent")
    @Expose
    private Integer percent;
    @SerializedName("_id")
    @Expose
    private String id;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
