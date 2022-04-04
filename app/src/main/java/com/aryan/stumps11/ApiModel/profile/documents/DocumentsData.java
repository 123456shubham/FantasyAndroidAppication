package com.aryan.stumps11.ApiModel.profile.documents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocumentsData {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("panCardNumber")
    @Expose
    private String panCardNumber;
    @SerializedName("panCardImage")
    @Expose
    private String panCardImage;
    @SerializedName("panCardImageVerfiy")
    @Expose
    private String panCardImageVerfiy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getPanCardImageVerfiy() {
        return panCardImageVerfiy;
    }

    public void setPanCardImageVerfiy(String panCardImageVerfiy) {
        this.panCardImageVerfiy = panCardImageVerfiy;
    }
}
