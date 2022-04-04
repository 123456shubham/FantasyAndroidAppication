package com.aryan.stumps11.ApiModel.profile.transaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyTransactionResponse {

        @SerializedName("data")
        @Expose
        private List<MyTransactionData> transactionData = null;


    public List<MyTransactionData> getTransactionData() {
        return transactionData;
    }

    public void setTransactionData(List<MyTransactionData> transactionData) {
        this.transactionData = transactionData;
    }
}
