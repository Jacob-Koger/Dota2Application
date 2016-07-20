package com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchHistory;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MHMatch {

    @SerializedName("match_seq_num")
    private String matchSeqNum;
    @SerializedName("start_time")
    private String startTime;
    @SerializedName("lobby_type")
    private String lobbyType;
    @SerializedName("radiant_team_id")
    private String radiantTeamId;
    @SerializedName("dire_team_id")
    private String direTeamId;
    @SerializedName("players")
    private List<MHPlayer> MHPlayers = new ArrayList<>();

    @Override
    public String toString() {
        return "MHMatch{" +
                "matchSeqNum='" + matchSeqNum + '\'' +
                ", startTime=" + startTime +
                ", lobbyType=" + lobbyType +
                ", radiantTeamId=" + radiantTeamId +
                ", direTeamId=" + direTeamId +
                ", MHPlayers=" + MHPlayers +
                '}';
    }

    public String getMatchSeqNum() {
        return matchSeqNum;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getLobbyType() {
        return lobbyType;
    }

    public String getRadiantTeamId() {
        return radiantTeamId;
    }

    public String getDireTeamId() {
        return direTeamId;
    }

    public List<MHPlayer> getMHPlayers() {
        return MHPlayers;
    }
}
