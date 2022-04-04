package com.aryan.stumps11.ApiModel.profile.withdraw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AmountWithdrawData {
    @SerializedName("referenceId")
    @Expose
    private String referenceId;
    @SerializedName("utr")
    @Expose
    private String utr;
    @SerializedName("acknowledged")
    @Expose
    private Integer acknowledged;

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getUtr() {
        return utr;
    }

    public void setUtr(String utr) {
        this.utr = utr;
    }

    public Integer getAcknowledged() {
        return acknowledged;
    }

    public void setAcknowledged(Integer acknowledged) {
        this.acknowledged = acknowledged;
    }
}
