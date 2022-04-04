package com.aryan.stumps11.ApiModel.profile.dummyCreateRes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DummyResponse {


    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private DummyData dummyData;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DummyData getDummyData() {
        return dummyData;
    }

    public void setDummyData(DummyData dummyData) {
        this.dummyData = dummyData;
    }
}
