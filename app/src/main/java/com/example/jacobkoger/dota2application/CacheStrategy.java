package com.example.jacobkoger.dota2application;

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * Used to determine how to cache our network responses
 */
public enum CacheStrategy {

    /** Used to request cached data for up to 1 minute before requesting new data */
    SOFT("max-age=" + MINUTES.toSeconds(1)),
    /** Used to request only cached data for up to 1 day */
    HARD("only-if-cached, max-age=" + DAYS.toSeconds(1)),
    /** Used to request fresh data */
    NONE("no-cache");

    /** The header value */
    public final String value;

    /**
     * Constructor for {@code CacheStrategy}
     *
     * @param value The header value
     */
    CacheStrategy(String value) {
        this.value = value;
    }

    /** @return The header value */
    public String value() {
        return value;
    }

}
