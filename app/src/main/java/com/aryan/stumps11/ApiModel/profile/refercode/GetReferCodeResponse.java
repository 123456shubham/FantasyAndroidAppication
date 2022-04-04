package com.aryan.stumps11.ApiModel.profile.refercode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetReferCodeResponse {
    @SerializedName("data")
    @Expose
    private GetDataReferCode data;

    public GetDataReferCode getData() {
        return data;
    }

    public void setData(GetDataReferCode data) {
        this.data = data;
    }
}
