package com.aryan.stumps11.NewUiData.Activity.Model;

public class ModelTeamScore {
    private String batsmanName,battingStatus,playerRun,bowl,four,six,strikeRunRate;

    public String getBatsmanName() {
        return batsmanName;
    }

    public void setBatsmanName(String batsmanName) {
        this.batsmanName = batsmanName;
    }

    public String getBattingStatus() {
        return battingStatus;
    }

    public void setBattingStatus(String battingStatus) {
        this.battingStatus = battingStatus;
    }

    public String getPlayerRun() {
        return playerRun;
    }

    public void setPlayerRun(String playerRun) {
        this.playerRun = playerRun;
    }

    public String getBowl() {
        return bowl;
    }

    public void setBowl(String bowl) {
        this.bowl = bowl;
    }

    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four;
    }

    public String getSix() {
        return six;
    }

    public void setSix(String six) {
        this.six = six;
    }

    public String getStrikeRunRate() {
        return strikeRunRate;
    }

    public void setStrikeRunRate(String strikeRunRate) {
        this.strikeRunRate = strikeRunRate;
    }

    public ModelTeamScore(String batsmanName, String battingStatus, String playerRun, String bowl, String four, String six, String strikeRunRate) {
        this.batsmanName = batsmanName;
        this.battingStatus = battingStatus;
        this.playerRun = playerRun;
        this.bowl = bowl;
        this.four = four;
        this.six = six;
        this.strikeRunRate = strikeRunRate;
    }
}
