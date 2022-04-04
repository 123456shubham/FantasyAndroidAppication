package com.aryan.stumps11.ApiModel.profile.addMoney;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddMoneyRequest {
    @SerializedName("amount")
    @Expose
    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
