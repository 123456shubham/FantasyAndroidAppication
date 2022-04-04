package com.aryan.stumps11.ApiModel.profile.context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContextResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<ContextData> contextData = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ContextData> getContextData() {
        return contextData;
    }

    public void setContextData(List<ContextData> contextData) {
        this.contextData = contextData;
    }
}
