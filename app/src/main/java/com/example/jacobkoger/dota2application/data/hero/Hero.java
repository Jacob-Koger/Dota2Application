package com.example.jacobkoger.dota2application.data.hero;

import com.google.gson.annotations.SerializedName;

public class Hero {

    private String name;
    private int id;
    @SerializedName("localized_name")
    private String localizedName;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getLocalizedName() {
        return localizedName;
    }

}
