package com.aryan.stumps11.Signup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("data")
    @Expose
    private MobileModelResponse mobileModelResponse;

    public MobileModelResponse getMobileModelResponse() {
        return mobileModelResponse;
    }

    public void setMobileModelResponse(MobileModelResponse mobileModelResponse) {
        this.mobileModelResponse = mobileModelResponse;
    }
}
