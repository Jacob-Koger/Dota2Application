package com.jacob.dota2.dota2Application.data.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Items {

    @SerializedName("itemdata")
    @Expose
    private List<ItemData> itemData = new ArrayList<ItemData>();

    /**
     * @return The itemdata
     */
    public List<ItemData> getItemData() {
        return itemData;
    }

    /**
     * @param itemdata The itemdata
     */
    public void setItemdata(List<ItemData> itemdata) {
        this.itemData = itemdata;
    }

}
