package com.jacob.jacobkoger.dota2Application.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Network related helpers
 */
public final class NetworkUtils {

    /** This class is never initialized */
    private NetworkUtils() {
        throw new AssertionError("no instances");
    }

    /**
     * Indicates whether network connectivity exists and it is possible to establish
     * connections and pass data
     *
     * @param context The {@link Context} to use
     * @return True if we've established connectivity, false otherwise
     */
    public static boolean isConnected(Context context) {
        return isConnected((ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE));
    }

    /**
     * Indicates whether network connectivity exists and it is possible to establish
     * connections and pass data
     *
     * @param cm The {@link ConnectivityManager} to use
     * @return True if we've established connectivity, false otherwise
     */
    public static boolean isConnected(ConnectivityManager cm) {
        final NetworkInfo an = cm.getActiveNetworkInfo();
        return an != null && an.isConnected();
    }

}
