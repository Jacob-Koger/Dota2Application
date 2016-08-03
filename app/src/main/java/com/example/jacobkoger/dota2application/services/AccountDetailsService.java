package com.example.jacobkoger.dota2application.services;

import com.example.jacobkoger.dota2application.data.accountinfo.AccountInfo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AccountDetailsService {
    @GET("/ISteamUser/GetPlayerSummaries/v0002/")
    Call<AccountInfo> getResponse();
}
