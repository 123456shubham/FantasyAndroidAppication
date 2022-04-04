package com.aryan.stumps11.ApiModel.profile.wallet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WalletResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private WalletData data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public WalletData getData() {
        return data;
    }

    public void setData(WalletData data) {
        this.data = data;
    }
}
