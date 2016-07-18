package com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchDetails;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AbilityUpgrade {

    @SerializedName("ability")
    @Expose
    private Integer ability;
    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("level")
    @Expose
    private Integer level;

    /**
     * @return The ability
     */
    public Integer getAbility() {
        return ability;
    }

    /**
     * @param ability The ability
     */
    public void setAbility(Integer ability) {
        this.ability = ability;
    }

    /**
     * @return The time
     */
    public Integer getTime() {
        return time;
    }

    /**
     * @param time The time
     */
    public void setTime(Integer time) {
        this.time = time;
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

}