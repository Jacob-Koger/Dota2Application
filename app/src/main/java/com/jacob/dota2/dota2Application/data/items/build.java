package com.jacob.dota2.dota2Application.data.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Build {

    @SerializedName("item1")
    @Expose
    private String item1;
    @SerializedName("item2")
    @Expose
    private String item2;
    @SerializedName("item3")
    @Expose
    private String item3;
    @SerializedName("item4")
    @Expose
    private String item4;

    /**
     *
     * @return
     * The item1
     */
    public String getItem1() {
        return item1;
    }

    /**
     *
     * @param item1
     * The item1
     */
    public void setItem1(String item1) {
        this.item1 = item1;
    }

    /**
     *
     * @return
     * The item2
     */
    public String getItem2() {
        return item2;
    }

    /**
     *
     * @param item2
     * The item2
     */
    public void setItem2(String item2) {
        this.item2 = item2;
    }

    /**
     *
     * @return
     * The item3
     */
    public String getItem3() {
        return item3;
    }

    /**
     *
     * @param item3
     * The item3
     */
    public void setItem3(String item3) {
        this.item3 = item3;
    }

    /**
     *
     * @return
     * The item4
     */
    public String getItem4() {
        return item4;
    }

    /**
     *
     * @param item4
     * The item4
     */
    public void setItem4(String item4) {
        this.item4 = item4;
    }

}
