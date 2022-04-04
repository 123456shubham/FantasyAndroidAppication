package com.aryan.stumps11.NewUiData.Activity.Model;

public class ModelFallOffWickets {
    String playerName,secondTeamScore,secondTeamOverNo;

    public ModelFallOffWickets(String playerName, String secondTeamScore, String secondTeamOverNo) {
        this.playerName = playerName;
        this.secondTeamScore = secondTeamScore;
        this.secondTeamOverNo = secondTeamOverNo;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getSecondTeamScore() {
        return secondTeamScore;
    }

    public void setSecondTeamScore(String secondTeamScore) {
        this.secondTeamScore = secondTeamScore;
    }

    public String getSecondTeamOverNo() {
        return secondTeamOverNo;
    }

    public void setSecondTeamOverNo(String secondTeamOverNo) {
        this.secondTeamOverNo = secondTeamOverNo;
    }
}
