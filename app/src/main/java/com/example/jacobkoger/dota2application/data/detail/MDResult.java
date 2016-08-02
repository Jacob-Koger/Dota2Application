package com.example.jacobkoger.dota2application.data.detail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MDResult {

    private List<MDPlayer> players;
    @SerializedName("radiant_win")
    private Boolean radiantWin;
    private int duration;
    @SerializedName("pre_game_duration")
    private int preGameDuration;
    @SerializedName("start_time")
    private int startTime;
    @SerializedName("match_id")
    private String matchId;
    @SerializedName("match_seq_num")
    private String matchSeqNum;
    @SerializedName("tower_status_radiant")
    private int towerStatusRadiant;
    @SerializedName("tower_status_dire")
    private int towerStatusDire;
    @SerializedName("barracks_status_radiant")
    private int barracksStatusRadiant;
    @SerializedName("barracks_status_dire")
    private int barracksStatusDire;
    private int cluster;
    @SerializedName("first_blood_time")
    private int firstBloodTime;
    @SerializedName("lobby_type")
    private int lobbyType;
    @SerializedName("human_players")
    private int humanPlayers;
    private int leagueid;
    @SerializedName("positive_votes")
    private int positiveVotes;
    @SerializedName("negative_votes")
    private int negativeVotes;
    @SerializedName("game_mode")
    private int gameMode;
    private int flags;
    private int engine;
    @SerializedName("radiant_score")
    private int radiantScore;
    @SerializedName("dire_score")
    private int direScore;

    public List<MDPlayer> getPlayers() {
        return players;
    }

    public Boolean getRadiantWin() {
        return radiantWin;
    }

    public int getDuration() {
        return duration;
    }

    public int getPreGameDuration() {
        return preGameDuration;
    }

    public int getStartTime() {
        return startTime;
    }

    public String getMatchId() {
        return matchId;
    }

    public String getMatchSeqNum() {
        return matchSeqNum;
    }

    public int getTowerStatusRadiant() {
        return towerStatusRadiant;
    }

    public int getTowerStatusDire() {
        return towerStatusDire;
    }

    public int getBarracksStatusRadiant() {
        return barracksStatusRadiant;
    }

    public int getBarracksStatusDire() {
        return barracksStatusDire;
    }

    public int getCluster() {
        return cluster;
    }

    public int getFirstBloodTime() {
        return firstBloodTime;
    }

    public int getLobbyType() {
        return lobbyType;
    }

    public int getHumanPlayers() {
        return humanPlayers;
    }

    public int getLeagueid() {
        return leagueid;
    }

    public int getPositiveVotes() {
        return positiveVotes;
    }

    public int getNegativeVotes() {
        return negativeVotes;
    }

    public int getGameMode() {
        return gameMode;
    }

    public int getFlags() {
        return flags;
    }

    public int getEngine() {
        return engine;
    }

    public int getRadiantScore() {
        return radiantScore;
    }

    public int getDireScore() {
        return direScore;
    }

}
