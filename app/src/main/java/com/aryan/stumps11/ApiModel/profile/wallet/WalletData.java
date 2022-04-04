package com.aryan.stumps11.ApiModel.profile.wallet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WalletData {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("wallet")
    @Expose
    private Integer wallet;

    @SerializedName("bonusWallet")
    @Expose
    private Integer bounsWallet;

    @SerializedName("winningWallet")
    @Expose
    private Integer winningWallet;

    @SerializedName("addedCash")
    @Expose
    private Integer addCash;

    public Integer getAddCash() {
        return addCash;
    }

    public void setAddCash(Integer addCash) {
        this.addCash = addCash;
    }

    public Integer getBounsWallet() {
        return bounsWallet;
    }

    public void setBounsWallet(Integer bounsWallet) {
        this.bounsWallet = bounsWallet;
    }

    public Integer getWinningWallet() {
        return winningWallet;
    }

    public void setWinningWallet(Integer winningWallet) {
        this.winningWallet = winningWallet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getWallet() {
        return wallet;
    }

    public void setWallet(Integer wallet) {
        this.wallet = wallet;
    }
}
