package com.aryan.stumps11.ApiModel.profile.createTeam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player11 {

    @SerializedName("pid")
    @Expose
    private String pid;
    @SerializedName("credit")
    @Expose
    private String credit;
    @SerializedName("captain")
    @Expose
    private Boolean captain;
    @SerializedName("vcaptain")
    @Expose
    private Boolean vcaptain;
    @SerializedName("name")
    @Expose
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
