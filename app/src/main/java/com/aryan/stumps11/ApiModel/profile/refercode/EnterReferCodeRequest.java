package com.aryan.stumps11.ApiModel.profile.refercode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EnterReferCodeRequest {
    @SerializedName("referCode")
    @Expose
    private String referCode;

    public String getReferCode() {
        return referCode;
    }

    public void setReferCode(String referCode) {
        this.referCode = referCode;
    }

}
