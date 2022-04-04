package com.aryan.stumps11.Model;

public class TransactionModel {
    private String messageType;
    private String Date ;
    private String time;
    private String trcPrice;

    public TransactionModel(String messageType, String date, String time, String trcPrice) {
        this.messageType = messageType;
        Date = date;
        this.time = time;
        this.trcPrice = trcPrice;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTrcPrice() {
        return trcPrice;
    }

    public void setTrcPrice(String trcPrice) {
        this.trcPrice = trcPrice;
    }

    @Override
    public String toString() {
        return "TransactionModel{" +
                "messageType='" + messageType + '\'' +
                ", Date='" + Date + '\'' +
                ", time='" + time + '\'' +
                ", trcPrice='" + trcPrice + '\'' +
                '}';
    }
}
