package com.aryan.stumps11.ApiModel.profile.rank;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RankResponse {

    @SerializedName("data")
    @Expose
    private RankData rankData;
    public RankData getRankData() {
        return rankData;
    }

    public void setRankData(RankData rankData) {
        this.rankData = rankData;
    }
}
