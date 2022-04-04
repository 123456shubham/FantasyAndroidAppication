package com.aryan.stumps11.ApiModel.profile.kyc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KycRequest {


    @SerializedName("panCardNumber")
    @Expose
    private String panCardNumber;
    @SerializedName("panCardImage")
    @Expose
    private String panCardImage;

    public String getPanCardNumber() {
        return panCardNumber;
    }

    public void setPanCardNumber(String panCardNumber) {
        this.panCardNumber = panCardNumber;
    }

    public String getPanCardImage() {
        return panCardImage;
    }

    public void setPanCardImage(String panCardImage) {
        this.panCardImage = panCardImage;
    }
}
