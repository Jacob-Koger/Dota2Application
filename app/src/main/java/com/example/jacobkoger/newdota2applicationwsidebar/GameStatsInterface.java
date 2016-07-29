package com.example.jacobkoger.newdota2applicationwsidebar;

import com.example.jacobkoger.newdota2applicationwsidebar.POJO_GameStats.GameStats;

import retrofit2.Call;
import retrofit2.http.GET;


public interface GameStatsInterface {
    @GET("/IPlayerService/GetOwnedGames/v0001/")
    Call<GameStats> getResponse();
}
