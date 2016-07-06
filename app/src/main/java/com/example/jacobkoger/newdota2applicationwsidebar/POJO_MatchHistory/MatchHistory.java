package com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchHistory;


public class MatchHistory {

    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MatchHistory{" +
                "result=" + result +
                '}';
    }

}