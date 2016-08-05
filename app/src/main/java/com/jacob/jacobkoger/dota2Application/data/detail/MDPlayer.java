package com.jacob.jacobkoger.dota2Application.data.detail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MDPlayer {

    @SerializedName("account_id")
    private long accountId;
    @SerializedName("player_slot")
    private int playerSlot;
    @SerializedName("hero_id")
    private int heroId;
    @SerializedName("item_0")
    private int item0;
    @SerializedName("item_1")
    private int item1;
    @SerializedName("item_2")
    private int item2;
    @SerializedName("item_3")
    private int item3;
    @SerializedName("item_4")
    private int item4;
    @SerializedName("item_5")
    private int item5;
    private int kills;
    private int deaths;
    private int assists;
    @SerializedName("leaver_status")
    private int leaverStatus;
    @SerializedName("last_hits")
    private int lastHits;
    private int denies;
    @SerializedName("gold_per_min")
    private int goldPerMin;
    @SerializedName("xp_per_min")
    private int xpPerMin;
    private int level;
    private int gold;
    @SerializedName("gold_spent")
    private int goldSpent;
    @SerializedName("hero_damage")
    private int heroDamage;
    @SerializedName("tower_damage")
    private int towerDamage;
    @SerializedName("hero_healing")
    private int heroHealing;
    @SerializedName("ability_upgrades")
    private List<MDAbilityUpgrade> abilityUpgrades;

    public long getAccountId() {
        return accountId;
    }

    public int getPlayerSlot() {
        return playerSlot;
    }

    public int getHeroId() {
        return heroId;
    }

    public int getItem0() {
        return item0;
    }

    public int getItem1() {
        return item1;
    }

    public int getItem2() {
        return item2;
    }

    public int getItem3() {
        return item3;
    }

    public int getItem4() {
        return item4;
    }

    public int getItem5() {
        return item5;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getAssists() {
        return assists;
    }

    public int getLeaverStatus() {
        return leaverStatus;
    }

    public int getLastHits() {
        return lastHits;
    }

    public int getDenies() {
        return denies;
    }

    public int getGoldPerMin() {
        return goldPerMin;
    }

    public int getXpPerMin() {
        return xpPerMin;
    }

    public int getLevel() {
        return level;
    }

    public int getGold() {
        return gold;
    }

    public int getGoldSpent() {
        return goldSpent;
    }

    public int getHeroDamage() {
        return heroDamage;
    }

    public int getTowerDamage() {
        return towerDamage;
    }

    public int getHeroHealing() {
        return heroHealing;
    }

    public List<MDAbilityUpgrade> getAbilityUpgrades() {
        return abilityUpgrades;
    }

}
