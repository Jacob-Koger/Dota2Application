package com.jacob.dota2.dota2Application.data.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ItemsList {

    @SerializedName("items")
    @Expose
    private List<Items> items = new ArrayList<Items>();

    /**
     *
     * @return
     * The items
     */
    public List<Items> getItems() {
        return items;
    }

    /**
     *
     * @param items
     * The items
     */
    public void setItems(List<Items> items) {
        this.items = items;
    }

}