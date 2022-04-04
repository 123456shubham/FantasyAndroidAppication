package com.aryan.stumps11.NewUiData.Activity.Model;

public class ModelBowlerList {
    private String BowlerName,bowlerOver,maidenOver,totalRun,totalWickets,overRunRate;

    public ModelBowlerList(String bowlerName, String bowlerOver, String maidenOver, String totalRun, String totalWickets, String overRunRate) {
        BowlerName = bowlerName;
        this.bowlerOver = bowlerOver;
        this.maidenOver = maidenOver;
        this.totalRun = totalRun;
        this.totalWickets = totalWickets;
        this.overRunRate = overRunRate;
    }

    public String getBowlerName() {
        return BowlerName;
    }

    public void setBowlerName(String bowlerName) {
        BowlerName = bowlerName;
    }

    public String getBowlerOver() {
        return bowlerOver;
    }

    public void setBowlerOver(String bowlerOver) {
        this.bowlerOver = bowlerOver;
    }

    public String getMaidenOver() {
        return maidenOver;
    }

    public void setMaidenOver(String maidenOver) {
        this.maidenOver = maidenOver;
    }

    public String getTotalRun() {
        return totalRun;
    }

    public void setTotalRun(String totalRun) {
        this.totalRun = totalRun;
    }

    public String getTotalWickets() {
        return totalWickets;
    }

    public void setTotalWickets(String totalWickets) {
        this.totalWickets = totalWickets;
    }

    public String getOverRunRate() {
        return overRunRate;
    }

    public void setOverRunRate(String overRunRate) {
        this.overRunRate = overRunRate;
    }
}

