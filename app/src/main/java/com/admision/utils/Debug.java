package com.admision.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

public class Debug {

    public static final boolean DEBUG = true;
    public static final boolean USE_PUBNUB = false;

    public static void e(@NonNull String tag, @Nullable String msg) {
        if (DEBUG) {
            if (TextUtils.isEmpty(tag))
                tag = "unknown";

//            Log.e(tag, msg);
            final String finalTag = tag;
            log(msg, new LogCB() {
                @Override
                public void log(@NonNull String message) {
                    Log.e(finalTag, message);
                }
            });
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            if (TextUtils.isEmpty(tag))
                tag = "unknown";
//            Log.i(tag, msg);

            final String finalTag = tag;
            log(msg, new LogCB() {
                @Override
                public void log(@NonNull String message) {
                    Log.i(finalTag, message);
                }
            });
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUG) {
            if (TextUtils.isEmpty(tag))
                tag = "unknown";
//            Log.w(tag, msg);
            final String finalTag = tag;
            log(msg, new LogCB() {
                @Override
                public void log(@NonNull String message) {
                    Log.w(finalTag, message);
                }
            });
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            if (TextUtils.isEmpty(tag))
                tag = "unknown";
//            Log.d(tag, msg);
            final String finalTag = tag;
            log(msg, new LogCB() {
                @Override
                public void log(@NonNull String message) {
                    Log.d(finalTag, message);
                }
            });
        }
    }

    public static void v(String tag, String msg) {
        if (DEBUG) {
            if (TextUtils.isEmpty(tag))
                tag = "unknown";
//            Log.v(tag, msg);
            final String finalTag = tag;
            log(msg, new LogCB() {
                @Override
                public void log(@NonNull String message) {
                    Log.v(finalTag, message);
                }
            });
        }
    }

    private static final int MAX_LOG_LENGTH = 4000;

    private static void log(@Nullable String message, @NonNull LogCB callback) {
        if (message == null) {
            callback.log("null");
            return;
        }
        // Split by line, then ensure each line can fit into Log's maximum length.
        for (int i = 0, length = message.length(); i < length; i++) {
            int newline = message.indexOf('\n', i);
            newline = newline != -1 ? newline : length;
            do {
                int end = Math.min(newline, i + MAX_LOG_LENGTH);
                callback.log(message.substring(i, end));
                i = end;
            } while (i < newline);
        }
    }

    private static void log(@Nullable String message, @Nullable Throwable throwable, @NonNull LogCB callback) {
        if (throwable == null) {
            log(message, callback);
            return;
        }
        if (message != null) {
            log(message + "\n" + Log.getStackTraceString(throwable), callback);
        } else {
            log(Log.getStackTraceString(throwable), callback);
        }
    }

    private interface LogCB {
        void log(@NonNull String message);
    }

}