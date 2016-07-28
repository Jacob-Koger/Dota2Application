package com.example.jacobkoger.newdota2applicationwsidebar.POJO_AccountInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Response {

    @SerializedName("players")
    @Expose
    private List<Player> players = new ArrayList<Player>();

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

}
