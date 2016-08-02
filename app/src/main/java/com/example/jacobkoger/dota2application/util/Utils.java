package com.example.jacobkoger.dota2application.util;

import android.support.annotation.NonNull;

/**
 * General helpers
 */
public final class Utils {

    /** This class is never initialized */
    private Utils() {
        throw new AssertionError("No instances");
    }

    /** @return {@code o} if non-null, or throws {@code NullPointerException} */
    public static <T> T requireNonNull(T o, @NonNull String message) {
        if (o == null) {
            throw new NullPointerException(message);
        }
        return o;
    }

}
