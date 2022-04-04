package com.aryan.stumps11.Winners.winnerRes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WinnerData {


    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("contestType")
    @Expose
    private String contestType;
    @SerializedName("winnerPrice")
    @Expose
    private Integer winnerPrice;
    @SerializedName("place")
    @Expose
    private String place;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContestType() {
        return contestType;
    }

    public void setContestType(String contestType) {
        this.contestType = contestType;
    }

    public Integer getWinnerPrice() {
        return winnerPrice;
    }

    public void setWinnerPrice(Integer winnerPrice) {
        this.winnerPrice = winnerPrice;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

}
