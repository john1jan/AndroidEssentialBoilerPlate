package com.johnfrancis.samplearch.app;

import android.app.Application;
import android.content.Context;

import com.johnfrancis.samplearch.BuildConfig;
import com.johnfrancis.samplearch.logger.FileLog;
import com.johnfrancis.samplearch.logger.Log;

import java.io.File;

/**
 * Created by john.francis on 14/05/16.
 */
public class MainApplication extends Application {
    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        assignLogger();
        context = this;
    }


    private void assignLogger() {
        final int LOG_FILE_SIZE = 1000000; //1mb
        if (BuildConfig.BUILD_TYPE.equals("debug")) {
            FileLog.open("sdcard/" + File.separator + BuildConfig.APPLICATION_ID + ".log",
                    android.util.Log.VERBOSE, LOG_FILE_SIZE);
            Log.plant(new Log.DebugTree());
        } else if (BuildConfig.BUILD_TYPE.equals("preRelease")) {
            Log.plant(new Log.ErrorWarningTree());
        } else {
            Log.plant(new CrashReportingTree());
        }
    }

    /**
     * A tree which logs important information for crash reporting.
     */
    private static class CrashReportingTree extends Log.HollowTree {
        @Override
        public void i(String message, Object... args) {
            // TODO e.g., Crashlytics.log(String.format(message, args));
        }

        @Override
        public void i(Throwable t, String message, Object... args) {
            i(message, args); // Just add to the log.
        }

        @Override
        public void e(String message, Object... args) {
            i("ERROR: " + message, args); // Just add to the log.
        }

        @Override
        public void e(Throwable t, String message, Object... args) {
            e(message, args);

            // TODO e.g., Crashlytics.logException(t);
        }
    }


    public static Context getContext() {
        return context;
    }
}
