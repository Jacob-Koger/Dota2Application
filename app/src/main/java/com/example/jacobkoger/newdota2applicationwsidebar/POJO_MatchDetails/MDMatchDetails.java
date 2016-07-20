package com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchDetails;


import com.google.gson.annotations.SerializedName;

public class MDMatchDetails {

    @SerializedName("result")
    private MDResult MDResult;

    public MDResult getMDResult() {
        return MDResult;
    }

    @Override
    public String toString() {
        return "MDMatchDetails{" +
                "MDResult=" + MDResult +
                '}';
    }

}