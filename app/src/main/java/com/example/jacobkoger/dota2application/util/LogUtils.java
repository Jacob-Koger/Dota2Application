package com.example.jacobkoger.dota2application.util;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.example.jacobkoger.dota2application.BuildConfig;

import java.util.Arrays;

/**
 * A collection of helpers used for sending log output
 */
public final class LogUtils {

    /** Default log tag */
    private static final String DEFAULT_LOG_TAG = "Bugdroid";

    /** Used to determine when to the print logs */
    private static final boolean DEBUG = BuildConfig.DEBUG;

    /** The maximum log tag length */
    private static final int MAX_LOG_TAG_LENGTH = 23;

    /** This class is never initialized */
    private LogUtils() {
        throw new AssertionError("No instances");
    }

    /**
     * Send a DEBUG log message
     *
     * @param tag Used to identify the source of a log message
     * @param msg The message you would like logged
     */
    public static void d(String tag, Object... msg) {
        if (DEBUG) {
            Log.d(makeLogTag(tag), Arrays.toString(msg));
        }
    }

    /**
     * Send a ERROR log message
     *
     * @param tag Used to identify the source of a log message
     * @param msg The message you would like logged
     * @param tr  An {@link Throwable} to log
     */
    public static void e(String tag, Object msg, @Nullable Throwable tr) {
        if (DEBUG) {
            Log.e(makeLogTag(tag), String.valueOf(msg), tr);
        }
    }

    /**
     * Used to wrap a new log tag constrained to Android's maximum log tag
     * length
     *
     * @param tag The tag used to identify the source of the log messages
     * @return A log tag constrained the a maximum character length of
     * {@value #MAX_LOG_TAG_LENGTH}
     */
    private static String makeLogTag(String tag) {
        return TextUtils.isEmpty(tag)
               ? DEFAULT_LOG_TAG
               : tag.length() > MAX_LOG_TAG_LENGTH
               ? tag.substring(0, MAX_LOG_TAG_LENGTH - 1)
               : tag;
    }

}
