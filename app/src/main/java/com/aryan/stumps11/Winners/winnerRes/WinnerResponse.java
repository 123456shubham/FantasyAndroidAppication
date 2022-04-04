package com.aryan.stumps11.Winners.winnerRes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WinnerResponse {
    @SerializedName("data")
    @Expose
    private List<WinnerData> winnerData = null;

    public List<WinnerData> getWinnerData() {
        return winnerData;
    }

    public void setWinnerData(List<WinnerData> winnerData) {
        this.winnerData = winnerData;
    }
}
