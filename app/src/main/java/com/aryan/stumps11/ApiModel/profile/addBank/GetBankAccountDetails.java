package com.aryan.stumps11.ApiModel.profile.addBank;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetBankAccountDetails {


    @SerializedName("data")
    @Expose
    private GetBankData getBankData;

    public GetBankData getGetBankData() {
        return getBankData;
    }

    public void setGetBankData(GetBankData getBankData) {
        this.getBankData = getBankData;
    }
}
