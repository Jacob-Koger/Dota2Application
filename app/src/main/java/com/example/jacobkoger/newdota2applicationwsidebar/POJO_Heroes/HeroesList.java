package com.example.jacobkoger.newdota2applicationwsidebar.POJO_Heroes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class HeroesList {

    @SerializedName("heroes")
    @Expose
    private List<Hero> heroes = new ArrayList<>();

    /**
     * @return The heroes
     */
    public List<Hero> getHeroes() {
        return heroes;
    }

    /**
     * @param heroes The heroes
     */
    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

}