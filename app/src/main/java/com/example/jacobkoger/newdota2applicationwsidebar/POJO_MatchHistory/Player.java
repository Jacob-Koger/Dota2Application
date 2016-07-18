package com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Player {

    @SerializedName("account_id")
    @Expose
    private long accountId;
    @SerializedName("player_slot")
    @Expose
    private long playerSlot;
    @SerializedName("hero_id")
    @Expose
    private long heroId;

    /**
     * @return The accountId
     */
    public long getAccountId() {
        return accountId;
    }

    /**
     * @param accountId The account_id
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * @return The playerSlot
     */
    public long getPlayerSlot() {
        return playerSlot;
    }

    /**
     * @param playerSlot The player_slot
     */
    public void setPlayerSlot(Integer playerSlot) {
        this.playerSlot = playerSlot;
    }

    /**
     * @return The heroId
     */
    public long getHeroId() {
        return heroId;
    }

    /**
     * @param heroId The hero_id
     */
    public void setHeroId(Integer heroId) {
        this.heroId = heroId;
    }


    @Override
    public String toString() {
        return "Player{" +
                "accountId=" + accountId +
                ", playerSlot=" + playerSlot +
                ", heroId=" + heroId +
                '}';
    }

}