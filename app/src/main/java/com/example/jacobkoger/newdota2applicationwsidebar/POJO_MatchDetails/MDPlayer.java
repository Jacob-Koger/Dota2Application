package com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchDetails;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class MDPlayer {
    @SerializedName("account_id")
    @Expose
    private long accountId;
    @SerializedName("player_slot")
    @Expose
    private Integer playerSlot;
    @SerializedName("hero_id")
    @Expose
    private Integer heroId;
    @SerializedName("item_0")
    @Expose
    private Integer item0;
    @SerializedName("item_1")
    @Expose
    private Integer item1;
    @SerializedName("item_2")
    @Expose
    private Integer item2;
    @SerializedName("item_3")
    @Expose
    private Integer item3;
    @SerializedName("item_4")
    @Expose
    private Integer item4;
    @SerializedName("item_5")
    @Expose
    private Integer item5;
    @SerializedName("kills")
    @Expose
    private Integer kills;
    @SerializedName("deaths")
    @Expose
    private Integer deaths;
    @SerializedName("assists")
    @Expose
    private Integer assists;
    @SerializedName("leaver_status")
    @Expose
    private Integer leaverStatus;
    @SerializedName("last_hits")
    @Expose
    private Integer lastHits;
    @SerializedName("denies")
    @Expose
    private Integer denies;
    @SerializedName("gold_per_min")
    @Expose
    private Integer goldPerMin;
    @SerializedName("xp_per_min")
    @Expose
    private Integer xpPerMin;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("gold")
    @Expose
    private Integer gold;
    @SerializedName("gold_spent")
    @Expose
    private Integer goldSpent;
    @SerializedName("hero_damage")
    @Expose
    private Integer heroDamage;
    @SerializedName("tower_damage")
    @Expose
    private Integer towerDamage;
    @SerializedName("hero_healing")
    @Expose
    private Integer heroHealing;
    @SerializedName("ability_upgrades")
    @Expose
    private List<MDAbilityUpgrade> abilityUpgrades = new ArrayList<MDAbilityUpgrade>();

    /**
     * @return The accountId
     */
    public long getAccountId() {
        return accountId;
    }

    /**
     * @param accountId The account_id
     */
    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    /**
     * @return The playerSlot
     */
    public Integer getPlayerSlot() {
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
    public Integer getHeroId() {
        return heroId;
    }

    /**
     * @param heroId The hero_id
     */
    public void setHeroId(Integer heroId) {
        this.heroId = heroId;
    }

    /**
     * @return The item0
     */
    public Integer getItem0() {
        return item0;
    }

    /**
     * @param item0 The item_0
     */
    public void setItem0(Integer item0) {
        this.item0 = item0;
    }

    /**
     * @return The item1
     */
    public Integer getItem1() {
        return item1;
    }

    /**
     * @param item1 The item_1
     */
    public void setItem1(Integer item1) {
        this.item1 = item1;
    }

    /**
     * @return The item2
     */
    public Integer getItem2() {
        return item2;
    }

    /**
     * @param item2 The item_2
     */
    public void setItem2(Integer item2) {
        this.item2 = item2;
    }

    /**
     * @return The item3
     */
    public Integer getItem3() {
        return item3;
    }

    /**
     * @param item3 The item_3
     */
    public void setItem3(Integer item3) {
        this.item3 = item3;
    }

    /**
     * @return The item4
     */
    public Integer getItem4() {
        return item4;
    }

    /**
     * @param item4 The item_4
     */
    public void setItem4(Integer item4) {
        this.item4 = item4;
    }

    /**
     * @return The item5
     */
    public Integer getItem5() {
        return item5;
    }

    /**
     * @param item5 The item_5
     */
    public void setItem5(Integer item5) {
        this.item5 = item5;
    }

    /**
     * @return The kills
     */
    public Integer getKills() {
        return kills;
    }

    /**
     * @param kills The kills
     */
    public void setKills(Integer kills) {
        this.kills = kills;
    }

    /**
     * @return The deaths
     */
    public Integer getDeaths() {
        return deaths;
    }

    /**
     * @param deaths The deaths
     */
    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    /**
     * @return The assists
     */
    public Integer getAssists() {
        return assists;
    }

    /**
     * @param assists The assists
     */
    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    /**
     * @return The leaverStatus
     */
    public Integer getLeaverStatus() {
        return leaverStatus;
    }

    /**
     * @param leaverStatus The leaver_status
     */
    public void setLeaverStatus(Integer leaverStatus) {
        this.leaverStatus = leaverStatus;
    }

    /**
     * @return The lastHits
     */
    public Integer getLastHits() {
        return lastHits;
    }

    /**
     * @param lastHits The last_hits
     */
    public void setLastHits(Integer lastHits) {
        this.lastHits = lastHits;
    }

    /**
     * @return The denies
     */
    public Integer getDenies() {
        return denies;
    }

    /**
     * @param denies The denies
     */
    public void setDenies(Integer denies) {
        this.denies = denies;
    }

    /**
     * @return The goldPerMin
     */
    public Integer getGoldPerMin() {
        return goldPerMin;
    }

    /**
     * @param goldPerMin The gold_per_min
     */
    public void setGoldPerMin(Integer goldPerMin) {
        this.goldPerMin = goldPerMin;
    }

    /**
     * @return The xpPerMin
     */
    public Integer getXpPerMin() {
        return xpPerMin;
    }

    /**
     * @param xpPerMin The xp_per_min
     */
    public void setXpPerMin(Integer xpPerMin) {
        this.xpPerMin = xpPerMin;
    }

    /**
     * @return The level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level The level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * @return The gold
     */
    public Integer getGold() {
        return gold;
    }

    /**
     * @param gold The gold
     */
    public void setGold(Integer gold) {
        this.gold = gold;
    }

    /**
     * @return The goldSpent
     */
    public Integer getGoldSpent() {
        return goldSpent;
    }

    /**
     * @param goldSpent The gold_spent
     */
    public void setGoldSpent(Integer goldSpent) {
        this.goldSpent = goldSpent;
    }

    /**
     * @return The heroDamage
     */
    public Integer getHeroDamage() {
        return heroDamage;
    }

    /**
     * @param heroDamage The hero_damage
     */
    public void setHeroDamage(Integer heroDamage) {
        this.heroDamage = heroDamage;
    }

    /**
     * @return The towerDamage
     */
    public Integer getTowerDamage() {
        return towerDamage;
    }

    /**
     * @param towerDamage The tower_damage
     */
    public void setTowerDamage(Integer towerDamage) {
        this.towerDamage = towerDamage;
    }

    /**
     * @return The heroHealing
     */
    public Integer getHeroHealing() {
        return heroHealing;
    }

    /**
     * @param heroHealing The hero_healing
     */
    public void setHeroHealing(Integer heroHealing) {
        this.heroHealing = heroHealing;
    }

    /**
     * @return The abilityUpgrades
     */
    public List<MDAbilityUpgrade> getAbilityUpgrades() {
        return abilityUpgrades;
    }

    /**
     * @param abilityUpgrades The ability_upgrades
     */
    public void setAbilityUpgrades(List<MDAbilityUpgrade> abilityUpgrades) {
        this.abilityUpgrades = abilityUpgrades;
    }

}