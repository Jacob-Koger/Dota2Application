package com.example.jacobkoger.newdota2applicationwsidebar.POJO_GameStats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Game {

    @SerializedName("appid")
    @Expose
    private Integer appid;
    @SerializedName("playtime_forever")
    @Expose
    private Integer playtimeForever;
    @SerializedName("playtime_2weeks")
    @Expose
    private Integer playtime2weeks;

    /**
     * @return The appid
     */
    public Integer getAppid() {
        return appid;
    }

    /**
     * @param appid The appid
     */
    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    /**
     * @return The playtimeForever
     */
    public Integer getPlaytimeForever() {
        return playtimeForever;
    }

    /**
     * @param playtimeForever The playtime_forever
     */
    public void setPlaytimeForever(Integer playtimeForever) {
        this.playtimeForever = playtimeForever;
    }

    /**
     * @return The playtime2weeks
     */
    public Integer getPlaytime2weeks() {
        return playtime2weeks;
    }

    /**
     * @param playtime2weeks The playtime_2weeks
     */
    public void setPlaytime2weeks(Integer playtime2weeks) {
        this.playtime2weeks = playtime2weeks;
    }
}