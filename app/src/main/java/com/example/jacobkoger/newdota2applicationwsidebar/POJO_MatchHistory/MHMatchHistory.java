package com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchHistory;


import com.google.gson.annotations.SerializedName;

public class MHMatchHistory {

    @SerializedName("result")
    private MHResult MHResult;

    public MHResult getMHResult() {
        return MHResult;
    }

    public void setMHResult(MHResult MHResult) {
        this.MHResult = MHResult;
    }

    @Override
    public String toString() {
        return "MHMatchHistory{" +
                "MHResult=" + MHResult +
                '}';
    }

}