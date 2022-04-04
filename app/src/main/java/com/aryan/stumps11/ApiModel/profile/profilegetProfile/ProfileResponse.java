package com.aryan.stumps11.ApiModel.profile.profilegetProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileResponse {


    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private ProfileDatum data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ProfileDatum getData() {
        return data;
    }

    public void setData(ProfileDatum data) {
        this.data = data;
    }
}
