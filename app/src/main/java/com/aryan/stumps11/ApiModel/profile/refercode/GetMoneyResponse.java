package com.aryan.stumps11.ApiModel.profile.refercode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetMoneyResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private Data data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
