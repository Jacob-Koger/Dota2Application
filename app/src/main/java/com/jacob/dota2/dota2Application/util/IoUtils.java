package com.jacob.dota2.dota2Application.util;

import android.support.annotation.Nullable;

import java.io.Closeable;
import java.io.IOException;

/**
 * I/O helpers
 */
public final class IoUtils {

    /** This class is never initialized */
    private IoUtils() {
        throw new AssertionError("No instances");
    }

    /** Used to close a {@link Closeable} ignoring any {@link IOException} */
    public static void closeSilently(@Nullable Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ignored) {
            // Nothing to do
        }
    }
}
