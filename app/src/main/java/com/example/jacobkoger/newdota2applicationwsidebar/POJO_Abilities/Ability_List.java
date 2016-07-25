
package com.example.jacobkoger.newdota2applicationwsidebar.POJO_Abilities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Ability_List {

    @SerializedName("abilities")
    @Expose
    private List<Ability> abilities = new ArrayList<Ability>();

    /**
     *
     * @return
     * The abilities
     */
    public List<Ability> getAbilities() {
        return abilities;
    }

    /**
     *
     * @param abilities
     * The abilities
     */
    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

}