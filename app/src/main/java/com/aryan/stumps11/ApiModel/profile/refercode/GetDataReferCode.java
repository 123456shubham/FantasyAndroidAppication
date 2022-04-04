package com.aryan.stumps11.ApiModel.profile.refercode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDataReferCode {
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("message")
    @Expose
    private String message;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
