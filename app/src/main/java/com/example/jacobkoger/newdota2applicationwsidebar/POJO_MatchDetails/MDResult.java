package com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MDResult {

    @SerializedName("player")
    @Expose
    private List<MDPlayer> MDPlayers = new ArrayList<>();
    @SerializedName("radiant_win")
    @Expose
    private Boolean radiantWin;
    @SerializedName("duration")
    @Expose
    private int duration;
    @SerializedName("pre_game_duration")
    @Expose
    private int preGameDuration;
    @SerializedName("start_time")
    @Expose
    private int startTime;
    @SerializedName("match_id")
    @Expose
    private String matchId;
    @SerializedName("match_seq_num")
    @Expose
    private String matchSeqNum;
    @SerializedName("tower_status_radiant")
    @Expose
    private int towerStatusRadiant;
    @SerializedName("tower_status_dire")
    @Expose
    private int towerStatusDire;
    @SerializedName("barracks_status_radiant")
    @Expose
    private int barracksStatusRadiant;
    @SerializedName("barracks_status_dire")
    @Expose
    private int barracksStatusDire;
    @SerializedName("cluster")
    @Expose
    private int cluster;
    @SerializedName("first_blood_time")
    @Expose
    private int firstBloodTime;
    @SerializedName("lobby_type")
    @Expose
    private int lobbyType;
    @SerializedName("human_players")
    @Expose
    private int humanPlayers;
    @SerializedName("leagueid")
    @Expose
    private int leagueid;
    @SerializedName("positive_votes")
    @Expose
    private int positiveVotes;
    @SerializedName("negative_votes")
    @Expose
    private int negativeVotes;
    @SerializedName("game_mode")
    @Expose
    private int gameMode;
    @SerializedName("flags")
    @Expose
    private int flags;
    @SerializedName("engine")
    @Expose
    private int engine;
    @SerializedName("radiant_score")
    @Expose
    private int radiantScore;
    @SerializedName("dire_score")
    @Expose
    private int direScore;


    public List<MDPlayer> getMDPlayers() {
        return MDPlayers;
    }

    public void setMDPlayers(List<MDPlayer> MDPlayers) {
        this.MDPlayers = MDPlayers;
    }

    public Boolean getRadiantWin() {
        return radiantWin;
    }

    public void setRadiantWin(Boolean radiantWin) {
        this.radiantWin = radiantWin;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPreGameDuration() {
        return preGameDuration;
    }

    public void setPreGameDuration(int preGameDuration) {
        this.preGameDuration = preGameDuration;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getMatchSeqNum() {
        return matchSeqNum;
    }

    public void setMatchSeqNum(String matchSeqNum) {
        this.matchSeqNum = matchSeqNum;
    }

    public int getTowerStatusRadiant() {
        return towerStatusRadiant;
    }

    public void setTowerStatusRadiant(int towerStatusRadiant) {
        this.towerStatusRadiant = towerStatusRadiant;
    }

    public int getTowerStatusDire() {
        return towerStatusDire;
    }

    public void setTowerStatusDire(int towerStatusDire) {
        this.towerStatusDire = towerStatusDire;
    }

    public int getBarracksStatusRadiant() {
        return barracksStatusRadiant;
    }

    public void setBarracksStatusRadiant(int barracksStatusRadiant) {
        this.barracksStatusRadiant = barracksStatusRadiant;
    }

    public int getBarracksStatusDire() {
        return barracksStatusDire;
    }

    public void setBarracksStatusDire(int barracksStatusDire) {
        this.barracksStatusDire = barracksStatusDire;
    }

    public int getCluster() {
        return cluster;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

    public int getFirstBloodTime() {
        return firstBloodTime;
    }

    public void setFirstBloodTime(int firstBloodTime) {
        this.firstBloodTime = firstBloodTime;
    }

    public int getLobbyType() {
        return lobbyType;
    }

    public void setLobbyType(int lobbyType) {
        this.lobbyType = lobbyType;
    }

    public int getHumanPlayers() {
        return humanPlayers;
    }

    public void setHumanPlayers(int humanPlayers) {
        this.humanPlayers = humanPlayers;
    }

    public int getLeagueid() {
        return leagueid;
    }

    public void setLeagueid(int leagueid) {
        this.leagueid = leagueid;
    }

    public int getPositiveVotes() {
        return positiveVotes;
    }

    public void setPositiveVotes(int positiveVotes) {
        this.positiveVotes = positiveVotes;
    }

    public int getNegativeVotes() {
        return negativeVotes;
    }

    public void setNegativeVotes(int negativeVotes) {
        this.negativeVotes = negativeVotes;
    }

    public int getGameMode() {
        return gameMode;
    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getEngine() {
        return engine;
    }

    public void setEngine(int engine) {
        this.engine = engine;
    }

    public int getRadiantScore() {
        return radiantScore;
    }

    public void setRadiantScore(int radiantScore) {
        this.radiantScore = radiantScore;
    }

    public int getDireScore() {
        return direScore;
    }

    public void setDireScore(int direScore) {
        this.direScore = direScore;
    }

}