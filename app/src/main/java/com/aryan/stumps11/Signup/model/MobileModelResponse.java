package com.aryan.stumps11.Signup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MobileModelResponse {


    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("new_user")
    @Expose
    private Boolean newUser;
    @SerializedName("update_profile")
    @Expose
    private Boolean updateProfile;
    @SerializedName("data")
    @Expose
    private MobileResponse data;

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

    public Boolean getNewUser() {
        return newUser;
    }

    public void setNewUser(Boolean newUser) {
        this.newUser = newUser;
    }

    public Boolean getUpdateProfile() {
        return updateProfile;
    }

    public void setUpdateProfile(Boolean updateProfile) {
        this.updateProfile = updateProfile;
    }

    public MobileResponse getData() {
        return data;
    }

    public void setData(MobileResponse data) {
        this.data = data;
    }
}
