package com.aryan.stumps11.EditTeam;

public class EditCVCModel {

    private String Teamname1,pname1,pts1,role1;
    private String id;
    private boolean isCheck;
    private String captain,vc;

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public String getVc() {
        return vc;
    }

    public void setVc(String vc) {
        this.vc = vc;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamname1() {
        return Teamname1;
    }

    public void setTeamname1(String teamname1) {
        Teamname1 = teamname1;
    }

    public String getPname1() {
        return pname1;
    }

    public void setPname1(String pname1) {
        this.pname1 = pname1;
    }

    public String getPts1() {
        return pts1;
    }

    public void setPts1(String pts1) {
        this.pts1 = pts1;
    }

    public String getRole1() {
        return role1;
    }

    public void setRole1(String role1) {
        this.role1 = role1;
    }
}
