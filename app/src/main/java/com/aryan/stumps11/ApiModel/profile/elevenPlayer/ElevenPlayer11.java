package com.aryan.stumps11.ApiModel.profile.elevenPlayer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ElevenPlayer11 {
    @SerializedName("pid")
    @Expose
    private String pid;
    @SerializedName("credit")
    @Expose
    private String credit;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("captain")
    @Expose
    private Boolean captain;
    @SerializedName("vcaptain")
    @Expose
    private Boolean vcaptain;
    @SerializedName("point")
    @Expose
    private Integer point;
    @SerializedName("_id")
    @Expose
    private String id;


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCaptain() {
        return captain;
    }

    public void setCaptain(Boolean captain) {
        this.captain = captain;
    }

    public Boolean getVcaptain() {
        return vcaptain;
    }

    public void setVcaptain(Boolean vcaptain) {
        this.vcaptain = vcaptain;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
