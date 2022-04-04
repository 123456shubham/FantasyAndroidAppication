package com.aryan.stumps11.ApiModel.profile.addBank;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddBankResponse {
    @SerializedName("User_Beneficiary")
    @Expose
    private String userBeneficiary;

    public String getUserBeneficiary() {
        return userBeneficiary;
    }

    public void setUserBeneficiary(String userBeneficiary) {
        this.userBeneficiary = userBeneficiary;
    }
}
