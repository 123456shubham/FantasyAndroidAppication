package com.aryan.stumps11.ContestClick.lederboard_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LeaderBoardResponse {

    @SerializedName("data")
    @Expose
    private List<LeadeboardData> data = null;

    public List<LeadeboardData> getData() {
        return data;
    }

    public void setData(List<LeadeboardData> data) {
        this.data = data;
    }
}
