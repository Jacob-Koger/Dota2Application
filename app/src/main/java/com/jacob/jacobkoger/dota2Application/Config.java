package com.jacob.jacobkoger.dota2Application;

import com.example.jacobkoger.dota2Application.BuildConfig;

/**
 * App-wide constants
 */
public final class Config {

    /** Steam API key */
    public static final String STEAM_API_KEY = BuildConfig.API_KEY;
    /** Steam API endpoint */
    public static final String STEAM_ENDPOINT = "https://api.steampowered.com/";

    /** This class is never initialized */
    private Config() {
        throw new AssertionError("No instances");
    }

}
