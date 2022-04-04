package com.aryan.stumps11.ApiModel.profile.verifyAccount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyAccountResponse {
    @SerializedName("beneficiary")
    @Expose
    private Boolean beneficiary;

    public Boolean getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Boolean beneficiary) {
        this.beneficiary = beneficiary;
    }
}
