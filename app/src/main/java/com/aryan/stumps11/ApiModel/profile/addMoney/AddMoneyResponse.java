package com.aryan.stumps11.ApiModel.profile.addMoney;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddMoneyResponse {



    @SerializedName("paymentLink")
    @Expose
    private String paymentLink;
    @SerializedName("status")
    @Expose
    private String status;

    public String getPaymentLink() {
        return paymentLink;
    }

    public void setPaymentLink(String paymentLink) {
        this.paymentLink = paymentLink;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
