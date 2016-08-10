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
    @SerializedName("item5")
    @Expose
    private String item5;
    @SerializedName("item6")
    @Expose
    private String item6;


    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem3() {
        return item3;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }


    public String getItem4() {
        return item4;
    }


    public void setItem4(String item4) {
        this.item4 = item4;
    }

    public String getItem5() {
        return item5;
    }

    public void setItem5(String item5) {
        this.item5 = item5;
    }

    public String getItem6() {
        return item6;
    }

    public void setItem6(String item6) {
        this.item6 = item6;
    }
}
