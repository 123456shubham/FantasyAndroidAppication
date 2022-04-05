package com.aryan.stumps11.joinContextList;

public class ListContextModel {
    String TourName,MatchStatus,teamAName,teamBName,joinTeam,iv_teamA,iv_teamB;


    public ListContextModel() {
    }

    public String getTourName() {
        return TourName;
    }

    public void setTourName(String tourName) {
        TourName = tourName;
    }

    public String getMatchStatus() {
        return MatchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        MatchStatus = matchStatus;
    }

    public String getTeamAName() {
        return teamAName;
    }

    public void setTeamAName(String teamAName) {
        this.teamAName = teamAName;
    }

    public String getTeamBName() {
        return teamBName;
    }

    public void setTeamBName(String teamBName) {
        this.teamBName = teamBName;
    }

    public String getJoinTeam() {
        return joinTeam;
    }

    public void setJoinTeam(String joinTeam) {
        this.joinTeam = joinTeam;
    }

    public String getIv_teamA() {
        return iv_teamA;
    }

    public void setIv_teamA(String iv_teamA) {
        this.iv_teamA = iv_teamA;
    }

    public String getIv_teamB() {
        return iv_teamB;
    }

    public void setIv_teamB(String iv_teamB) {
        this.iv_teamB = iv_teamB;
    }
}
