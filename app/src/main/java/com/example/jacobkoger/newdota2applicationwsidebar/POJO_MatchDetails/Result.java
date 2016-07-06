package com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchDetails;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Result {

    @SerializedName("radiant_score")
    private String radiantScore;
    @SerializedName("dire_score")
    private String direScore;
    @SerializedName("match_id")
    private String matchId;

    /**
     *
     * @return
     * The radiantScore
     */
    public String getRadiantScore() {
        return radiantScore;
    }



    /**
     *
     * @return
     * The direScore
     */
    public String getDireScore() {
        return direScore;
    }

    public String getMatchId() {
        return matchId;
    }

}