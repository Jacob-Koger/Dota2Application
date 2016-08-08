package com.jacob.dota2.dota2Application.data.lobbies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LobbiesList {

    @SerializedName("lobbies")
    @Expose
    private List<Lobby> lobbies = new ArrayList<Lobby>();

    /**
     *
     * @return
     * The lobbies
     */
    public List<Lobby> getLobbies() {
        return lobbies;
    }

    /**
     *
     * @param lobbies
     * The lobbies
     */
    public void setLobbies(List<Lobby> lobbies) {
        this.lobbies = lobbies;
    }

}