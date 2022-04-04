package com.aryan.stumps11.ApiModel.profile.rank;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RankData {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("ranks")
    @Expose
    private List<RankDetails> rankDetailsList = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<RankDetails> getRankDetailsList() {
        return rankDetailsList;
    }

    public void setRankDetailsList(List<RankDetails> rankDetailsList) {
        this.rankDetailsList = rankDetailsList;
    }
}
