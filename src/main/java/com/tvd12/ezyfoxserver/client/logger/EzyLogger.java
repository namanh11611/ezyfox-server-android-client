package com.tvd12.ezyfoxserver.client.logger;

import android.util.Log;

public final class EzyLogger {

    public static final String TAG = "ezyfox-client";
    public static final byte LEVEL_DEBUG = 1;
    public static final byte LEVEL_VERBOSE = 2;
    public static final byte LEVEL_INFO = 3;
    public static final byte LEVEL_WARN = 4;
    public static final byte LEVEL_ERROR = 5;
    public static final byte LEVEL_FATAL = 6;

    private static byte level = LEVEL_DEBUG;


    private EzyLogger () {}

    public static void setLevel (byte level) {
        EzyLogger.level = level;
    }

    public static void debug (String msg) {
        if (level <= LEVEL_DEBUG) {
            Log.d(TAG, standardizedMessage(msg));
        }
    }

    public static void verbose (String msg) {
        if (level <= LEVEL_VERBOSE) {
            Log.v(TAG, standardizedMessage(msg));
        }
    }

    public static void info (String msg) {
        if (level <= LEVEL_INFO) {
            Log.i(TAG, standardizedMessage(msg));
        }
    }

    public static void warn (String msg) {
        if (level <= LEVEL_WARN) {
            Log.w(TAG, standardizedMessage(msg));
        }
    }

    public static void warn (String msg, Throwable e) {
        if (level <= LEVEL_WARN) {
            Log.w(TAG, standardizedMessage(msg), e);
        }
    }

    public static void error (String msg) {
        if (level <= LEVEL_ERROR) {
            Log.e(TAG, standardizedMessage(msg));
        }
    }

    public static void error (String msg, Throwable e) {
        if (level <= LEVEL_ERROR) {
            Log.e(TAG, standardizedMessage(msg), e);
        }
    }

    public static void fatal (String msg, Throwable e) {
        if (level <= LEVEL_FATAL) {
            Log.wtf(TAG, standardizedMessage(msg), e);
        }
    }

    private static String standardizedMessage (String message) {
        return Thread.currentThread().getName() +
            " | " +
            message;
    }
}
