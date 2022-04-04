package com.aryan.stumps11.ApiModel.profile.banner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BannerResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<BannerList> bannerListList = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<BannerList> getBannerListList() {
        return bannerListList;
    }

    public void setBannerListList(List<BannerList> bannerListList) {
        this.bannerListList = bannerListList;
    }
}
