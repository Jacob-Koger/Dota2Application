package com.jacob.jacobkoger.dota2Application.services;

import com.jacob.jacobkoger.dota2Application.data.accountinfo.AccountInfo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AccountDetailsService {
    @GET("/ISteamUser/GetPlayerSummaries/v0002/")
    Call<AccountInfo> getResponse();
}
