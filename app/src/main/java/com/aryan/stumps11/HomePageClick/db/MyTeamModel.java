package com.aryan.stumps11.HomePageClick.db;

public class MyTeamModel {
    private String teamNameA,teamNameB,teamTotalPlayerA,teamTotalPlayerB,teamCaptainName,teamViceCaptainName;
    private int captainImage,viceCaptainImage;


    public MyTeamModel() {
    }

    public MyTeamModel(String teamNameA, String teamNameB, String teamTotalPlayerA, String teamTotalPlayerB, String teamCaptainName, String teamViceCaptainName, int captainImage, int viceCaptainImage) {
        this.teamNameA = teamNameA;
        this.teamNameB = teamNameB;
        this.teamTotalPlayerA = teamTotalPlayerA;
        this.teamTotalPlayerB = teamTotalPlayerB;
        this.teamCaptainName = teamCaptainName;
        this.teamViceCaptainName = teamViceCaptainName;
        this.captainImage = captainImage;
        this.viceCaptainImage = viceCaptainImage;
    }

    public String getTeamNameA() {
        return teamNameA;
    }

    public void setTeamNameA(String teamNameA) {
        this.teamNameA = teamNameA;
    }

    public String getTeamNameB() {
        return teamNameB;
    }

    public void setTeamNameB(String teamNameB) {
        this.teamNameB = teamNameB;
    }

    public String getTeamTotalPlayerA() {
        return teamTotalPlayerA;
    }

    public void setTeamTotalPlayerA(String teamTotalPlayerA) {
        this.teamTotalPlayerA = teamTotalPlayerA;
    }

    public String getTeamTotalPlayerB() {
        return teamTotalPlayerB;
    }

    public void setTeamTotalPlayerB(String teamTotalPlayerB) {
        this.teamTotalPlayerB = teamTotalPlayerB;
    }

    public String getTeamCaptainName() {
        return teamCaptainName;
    }

    public void setTeamCaptainName(String teamCaptainName) {
        this.teamCaptainName = teamCaptainName;
    }

    public String getTeamViceCaptainName() {
        return teamViceCaptainName;
    }

    public void setTeamViceCaptainName(String teamViceCaptainName) {
        this.teamViceCaptainName = teamViceCaptainName;
    }

    public int getCaptainImage() {
        return captainImage;
    }

    public void setCaptainImage(int captainImage) {
        this.captainImage = captainImage;
    }

    public int getViceCaptainImage() {
        return viceCaptainImage;
    }

    public void setViceCaptainImage(int viceCaptainImage) {
        this.viceCaptainImage = viceCaptainImage;
    }
}
