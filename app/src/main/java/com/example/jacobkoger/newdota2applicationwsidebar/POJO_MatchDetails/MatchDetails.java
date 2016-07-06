package com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchDetails;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MatchDetails {

    private Result result;

    public Result getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "MatchDetails{" +
                "result=" + result +
                '}';
    }

}