package com.aryan.stumps11.comingMatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Item {

    @SerializedName("match_id")
    @Expose
    private Integer matchId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("short_title")
    @Expose
    private String shortTitle;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("format")
    @Expose
    private Integer format;
    @SerializedName("format_str")
    @Expose
    private String formatStr;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("status_str")
    @Expose
    private String statusStr;
    @SerializedName("status_note")
    @Expose
    private String statusNote;
    @SerializedName("verified")
    @Expose
    private String verified;
    @SerializedName("pre_squad")
    @Expose
    private String preSquad;
    @SerializedName("odds_available")
    @Expose
    private String oddsAvailable;
    @SerializedName("game_state")
    @Expose
    private Integer gameState;
    @SerializedName("game_state_str")
    @Expose
    private String gameStateStr;
    @SerializedName("domestic")
    @Expose
    private String domestic;
    @SerializedName("competition")
    @Expose
    private Competition competition;
    @SerializedName("teama")
    @Expose
    private Teama teama;
    @SerializedName("teamb")
    @Expose
    private Teamb teamb;
    @SerializedName("date_start")
    @Expose
    private String dateStart;
    @SerializedName("date_end")
    @Expose
    private String dateEnd;
    @SerializedName("timestamp_start")
    @Expose
    private Integer timestampStart;
    @SerializedName("timestamp_end")
    @Expose
    private Integer timestampEnd;
    @SerializedName("date_start_ist")
    @Expose
    private String dateStartIst;
    @SerializedName("date_end_ist")
    @Expose
    private String dateEndIst;
    @SerializedName("venue")
    @Expose
    private Venue venue;
    @SerializedName("umpires")
    @Expose
    private String umpires;
    @SerializedName("referee")
    @Expose
    private String referee;
    @SerializedName("equation")
    @Expose
    private String equation;
    @SerializedName("live")
    @Expose
    private String live;
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("result_type")
    @Expose
    private Integer resultType;
    @SerializedName("win_margin")
    @Expose
    private String winMargin;
    @SerializedName("winning_team_id")
    @Expose
    private Integer winningTeamId;
    @SerializedName("commentary")
    @Expose
    private Integer commentary;
    @SerializedName("wagon")
    @Expose
    private Integer wagon;
    @SerializedName("latest_inning_number")
    @Expose
    private Integer latestInningNumber;
    @SerializedName("presquad_time")
    @Expose
    private String presquadTime;
    @SerializedName("verify_time")
    @Expose
    private String verifyTime;
    @SerializedName("toss")
    @Expose
    private Toss toss;


    public Integer getMatchId() {
        return matchId;
    }
    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getShortTitle() {
        return shortTitle;
    }
    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }
    public String getSubtitle() {
        return subtitle;
    }
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    public Integer getFormat() {
        return format;
    }
    public void setFormat(Integer format) {
        this.format = format;
    }
    public String getFormatStr() {
        return formatStr;
    }
    public void setFormatStr(String formatStr) {
        this.formatStr = formatStr;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getStatusStr() {
        return statusStr;
    }
    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }
    public String getStatusNote() {
        return statusNote;
    }
    public void setStatusNote(String statusNote) {
        this.statusNote = statusNote;
    }
    public String getVerified() {
        return verified;
    }
    public void setVerified(String verified) {
        this.verified = verified;
    }
    public String getPreSquad() {
        return preSquad;
    }
    public void setPreSquad(String preSquad) {
        this.preSquad = preSquad;
    }
    public String getOddsAvailable() {
        return oddsAvailable;
    }
    public void setOddsAvailable(String oddsAvailable) {
        this.oddsAvailable = oddsAvailable;
    }
    public Integer getGameState() {
        return gameState;
    }
    public void setGameState(Integer gameState) {
        this.gameState = gameState;
    }
    public String getGameStateStr() {
        return gameStateStr;
    }
    public void setGameStateStr(String gameStateStr) {
        this.gameStateStr = gameStateStr;
    }
    public String getDomestic() {
        return domestic;
    }
    public void setDomestic(String domestic) {
        this.domestic = domestic;
    }
    public Competition getCompetition() {
        return competition;
    }
    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
    public Teama getTeama() {
        return teama;
    }
    public void setTeama(Teama teama) {
        this.teama = teama;
    }
    public Teamb getTeamb() {
        return teamb;
    }
    public void setTeamb(Teamb teamb) {
        this.teamb = teamb;
    }
    public String getDateStart() {
        return dateStart;
    }
    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }
    public String getDateEnd() {
        return dateEnd;
    }
    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
    public Integer getTimestampStart() {
        return timestampStart;
    }
    public void setTimestampStart(Integer timestampStart) {
        this.timestampStart = timestampStart;
    }
    public Integer getTimestampEnd() {
        return timestampEnd;
    }
    public void setTimestampEnd(Integer timestampEnd) {
        this.timestampEnd = timestampEnd;
    }
    public String getDateStartIst() {
        return dateStartIst;
    }
    public void setDateStartIst(String dateStartIst) {
        this.dateStartIst = dateStartIst;
    }
    public String getDateEndIst() {
        return dateEndIst;
    }
    public void setDateEndIst(String dateEndIst) {
        this.dateEndIst = dateEndIst;
    }
    public Venue getVenue() {
        return venue;
    }
    public void setVenue(Venue venue) {
        this.venue = venue;
    }
    public String getUmpires() {
        return umpires;
    }
    public void setUmpires(String umpires) {
        this.umpires = umpires;
    }
    public String getReferee() {
        return referee;
    }
    public void setReferee(String referee) {
        this.referee = referee;
    }
    public String getEquation() {
        return equation;
    }
    public void setEquation(String equation) {
        this.equation = equation;
    }
    public String getLive() {
        return live;
    }
    public void setLive(String live) {
        this.live = live;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public Integer getResultType() {
        return resultType;
    }
    public void setResultType(Integer resultType) {
        this.resultType = resultType;
    }
    public String getWinMargin() {
        return winMargin;
    }
    public void setWinMargin(String winMargin) {
        this.winMargin = winMargin;
    }
    public Integer getWinningTeamId() {
        return winningTeamId;
    }
    public void setWinningTeamId(Integer winningTeamId) {
        this.winningTeamId = winningTeamId;
    }
    public Integer getCommentary() {
        return commentary;
    }
    public void setCommentary(Integer commentary) {
        this.commentary = commentary;
    }
    public Integer getWagon() {
        return wagon;
    }
    public void setWagon(Integer wagon) {
        this.wagon = wagon;
    }
    public Integer getLatestInningNumber() {
        return latestInningNumber;
    }
    public void setLatestInningNumber(Integer latestInningNumber) {
        this.latestInningNumber = latestInningNumber;
    }
    public String getPresquadTime() {
        return presquadTime;
    }
    public void setPresquadTime(String presquadTime) {
        this.presquadTime = presquadTime;
    }
    public String getVerifyTime() {
        return verifyTime;
    }
    public void setVerifyTime(String verifyTime) {
        this.verifyTime = verifyTime;
    }
    public Toss getToss() {
        return toss;
    }
    public void setToss(Toss toss) {
        this.toss = toss;
    }
}
