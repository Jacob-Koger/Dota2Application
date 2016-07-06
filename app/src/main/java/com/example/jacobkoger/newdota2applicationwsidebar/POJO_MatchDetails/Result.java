package com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchDetails;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Result {

    @SerializedName("players")
    @Expose
    private List<Player> players = new ArrayList<Player>();
    @SerializedName("radiant_win")
    @Expose
    private Boolean radiantWin;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("pre_game_duration")
    @Expose
    private Integer preGameDuration;
    @SerializedName("start_time")
    @Expose
    private Integer startTime;
    @SerializedName("match_id")
    @Expose
    private Integer matchId;
    @SerializedName("match_seq_num")
    @Expose
    private Integer matchSeqNum;
    @SerializedName("tower_status_radiant")
    @Expose
    private Integer towerStatusRadiant;
    @SerializedName("tower_status_dire")
    @Expose
    private Integer towerStatusDire;
    @SerializedName("barracks_status_radiant")
    @Expose
    private Integer barracksStatusRadiant;
    @SerializedName("barracks_status_dire")
    @Expose
    private Integer barracksStatusDire;
    @SerializedName("cluster")
    @Expose
    private Integer cluster;
    @SerializedName("first_blood_time")
    @Expose
    private Integer firstBloodTime;
    @SerializedName("lobby_type")
    @Expose
    private Integer lobbyType;
    @SerializedName("human_players")
    @Expose
    private Integer humanPlayers;
    @SerializedName("leagueid")
    @Expose
    private Integer leagueid;
    @SerializedName("positive_votes")
    @Expose
    private Integer positiveVotes;
    @SerializedName("negative_votes")
    @Expose
    private Integer negativeVotes;
    @SerializedName("game_mode")
    @Expose
    private Integer gameMode;
    @SerializedName("flags")
    @Expose
    private Integer flags;
    @SerializedName("engine")
    @Expose
    private Integer engine;
    @SerializedName("radiant_score")
    @Expose
    private Integer radiantScore;
    @SerializedName("dire_score")
    @Expose
    private Integer direScore;

    /**
     *
     * @return
     * The players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     *
     * @param players
     * The players
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /**
     *
     * @return
     * The radiantWin
     */
    public Boolean getRadiantWin() {
        return radiantWin;
    }

    /**
     *
     * @param radiantWin
     * The radiant_win
     */
    public void setRadiantWin(Boolean radiantWin) {
        this.radiantWin = radiantWin;
    }

    /**
     *
     * @return
     * The duration
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     *
     * @param duration
     * The duration
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     *
     * @return
     * The preGameDuration
     */
    public Integer getPreGameDuration() {
        return preGameDuration;
    }

    /**
     *
     * @param preGameDuration
     * The pre_game_duration
     */
    public void setPreGameDuration(Integer preGameDuration) {
        this.preGameDuration = preGameDuration;
    }

    /**
     *
     * @return
     * The startTime
     */
    public Integer getStartTime() {
        return startTime;
    }

    /**
     *
     * @param startTime
     * The start_time
     */
    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    /**
     *
     * @return
     * The matchId
     */
    public Integer getMatchId() {
        return matchId;
    }

    /**
     *
     * @param matchId
     * The match_id
     */
    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    /**
     *
     * @return
     * The matchSeqNum
     */
    public Integer getMatchSeqNum() {
        return matchSeqNum;
    }

    /**
     *
     * @param matchSeqNum
     * The match_seq_num
     */
    public void setMatchSeqNum(Integer matchSeqNum) {
        this.matchSeqNum = matchSeqNum;
    }

    /**
     *
     * @return
     * The towerStatusRadiant
     */
    public Integer getTowerStatusRadiant() {
        return towerStatusRadiant;
    }

    /**
     *
     * @param towerStatusRadiant
     * The tower_status_radiant
     */
    public void setTowerStatusRadiant(Integer towerStatusRadiant) {
        this.towerStatusRadiant = towerStatusRadiant;
    }

    /**
     *
     * @return
     * The towerStatusDire
     */
    public Integer getTowerStatusDire() {
        return towerStatusDire;
    }

    /**
     *
     * @param towerStatusDire
     * The tower_status_dire
     */
    public void setTowerStatusDire(Integer towerStatusDire) {
        this.towerStatusDire = towerStatusDire;
    }

    /**
     *
     * @return
     * The barracksStatusRadiant
     */
    public Integer getBarracksStatusRadiant() {
        return barracksStatusRadiant;
    }

    /**
     *
     * @param barracksStatusRadiant
     * The barracks_status_radiant
     */
    public void setBarracksStatusRadiant(Integer barracksStatusRadiant) {
        this.barracksStatusRadiant = barracksStatusRadiant;
    }

    /**
     *
     * @return
     * The barracksStatusDire
     */
    public Integer getBarracksStatusDire() {
        return barracksStatusDire;
    }

    /**
     *
     * @param barracksStatusDire
     * The barracks_status_dire
     */
    public void setBarracksStatusDire(Integer barracksStatusDire) {
        this.barracksStatusDire = barracksStatusDire;
    }

    /**
     *
     * @return
     * The cluster
     */
    public Integer getCluster() {
        return cluster;
    }

    /**
     *
     * @param cluster
     * The cluster
     */
    public void setCluster(Integer cluster) {
        this.cluster = cluster;
    }

    /**
     *
     * @return
     * The firstBloodTime
     */
    public Integer getFirstBloodTime() {
        return firstBloodTime;
    }

    /**
     *
     * @param firstBloodTime
     * The first_blood_time
     */
    public void setFirstBloodTime(Integer firstBloodTime) {
        this.firstBloodTime = firstBloodTime;
    }

    /**
     *
     * @return
     * The lobbyType
     */
    public Integer getLobbyType() {
        return lobbyType;
    }

    /**
     *
     * @param lobbyType
     * The lobby_type
     */
    public void setLobbyType(Integer lobbyType) {
        this.lobbyType = lobbyType;
    }

    /**
     *
     * @return
     * The humanPlayers
     */
    public Integer getHumanPlayers() {
        return humanPlayers;
    }

    /**
     *
     * @param humanPlayers
     * The human_players
     */
    public void setHumanPlayers(Integer humanPlayers) {
        this.humanPlayers = humanPlayers;
    }

    /**
     *
     * @return
     * The leagueid
     */
    public Integer getLeagueid() {
        return leagueid;
    }

    /**
     *
     * @param leagueid
     * The leagueid
     */
    public void setLeagueid(Integer leagueid) {
        this.leagueid = leagueid;
    }

    /**
     *
     * @return
     * The positiveVotes
     */
    public Integer getPositiveVotes() {
        return positiveVotes;
    }

    /**
     *
     * @param positiveVotes
     * The positive_votes
     */
    public void setPositiveVotes(Integer positiveVotes) {
        this.positiveVotes = positiveVotes;
    }

    /**
     *
     * @return
     * The negativeVotes
     */
    public Integer getNegativeVotes() {
        return negativeVotes;
    }

    /**
     *
     * @param negativeVotes
     * The negative_votes
     */
    public void setNegativeVotes(Integer negativeVotes) {
        this.negativeVotes = negativeVotes;
    }

    /**
     *
     * @return
     * The gameMode
     */
    public Integer getGameMode() {
        return gameMode;
    }

    /**
     *
     * @param gameMode
     * The game_mode
     */
    public void setGameMode(Integer gameMode) {
        this.gameMode = gameMode;
    }

    /**
     *
     * @return
     * The flags
     */
    public Integer getFlags() {
        return flags;
    }

    /**
     *
     * @param flags
     * The flags
     */
    public void setFlags(Integer flags) {
        this.flags = flags;
    }

    /**
     *
     * @return
     * The engine
     */
    public Integer getEngine() {
        return engine;
    }

    /**
     *
     * @param engine
     * The engine
     */
    public void setEngine(Integer engine) {
        this.engine = engine;
    }

    /**
     *
     * @return
     * The radiantScore
     */
    public Integer getRadiantScore() {
        return radiantScore;
    }

    /**
     *
     * @param radiantScore
     * The radiant_score
     */
    public void setRadiantScore(Integer radiantScore) {
        this.radiantScore = radiantScore;
    }

    /**
     *
     * @return
     * The direScore
     */
    public Integer getDireScore() {
        return direScore;
    }

    /**
     *
     * @param direScore
     * The dire_score
     */
    public void setDireScore(Integer direScore) {
        this.direScore = direScore;
    }

}