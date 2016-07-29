package com.example.jacobkoger.newdota2applicationwsidebar.POJO_GameStats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Response {

    @SerializedName("game_count")
    @Expose
    private Integer gameCount;
    @SerializedName("games")
    @Expose
    private List<Game> games = new ArrayList<Game>();

    /**
     * @return The gameCount
     */
    public Integer getGameCount() {
        return gameCount;
    }

    /**
     * @param gameCount The game_count
     */
    public void setGameCount(Integer gameCount) {
        this.gameCount = gameCount;
    }

    /**
     * @return The games
     */
    public List<Game> getGames() {
        return games;
    }

    /**
     * @param games The games
     */
    public void setGames(List<Game> games) {
        this.games = games;
    }

}