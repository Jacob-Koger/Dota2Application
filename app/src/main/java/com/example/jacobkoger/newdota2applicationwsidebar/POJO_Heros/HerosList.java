package com.example.jacobkoger.newdota2applicationwsidebar.POJO_Heros;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HerosList {

    @SerializedName("heroes")
    @Expose
    private List heroes = new ArrayList<>();

    /**
     *
     * @return
     * The heroes
     */
    public List getHeroes() {
        return heroes;
    }

    /**
     *
     * @param heroes
     * The heroes
     */
    public void setHeroes(List heroes) {
        this.heroes = heroes;
    }

}