package com.aryan.stumps11.ApiModel.profile.withdraw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AmountWithdrawResponse {
    @SerializedName("response")
    @Expose
    private AmountResponse amountResponse;

    public AmountResponse getAmountResponse() {
        return amountResponse;
    }

    public void setAmountResponse(AmountResponse amountResponse) {
        this.amountResponse = amountResponse;
    }
}
