package com.aryan.stumps11.ApiModel.profile.dummyCreateRes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DummyPlayer11 {

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
    private String captain;
    @SerializedName("vcaptain")
    @Expose
    private String vcaptain;
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

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public String getVcaptain() {
        return vcaptain;
    }

    public void setVcaptain(String vcaptain) {
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
