package com.example.jacobkoger.newdota2applicationwsidebar.POJO_Heroes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hero {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("localized_name")
    @Expose
    private String localizedName;

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The localizedName
     */
    public String getLocalizedName() {
        return localizedName;
    }

    /**
     * @param localizedName The localized_name
     */
    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

}