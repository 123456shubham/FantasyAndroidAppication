package com.aryan.stumps11.ApiModel.profile.refercode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Refer {
    @SerializedName("refer_type")
    @Expose
    private String referType;
    @SerializedName("refer_price")
    @Expose
    private Integer referPrice;
    @SerializedName("message")
    @Expose
    private String message;

    public String getReferType() {
        return referType;
    }

    public void setReferType(String referType) {
        this.referType = referType;
    }

    public Integer getReferPrice() {
        return referPrice;
    }

    public void setReferPrice(Integer referPrice) {
        this.referPrice = referPrice;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
