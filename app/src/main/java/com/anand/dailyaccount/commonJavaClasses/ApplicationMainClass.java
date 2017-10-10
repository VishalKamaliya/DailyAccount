package com.anand.dailyaccount.commonJavaClasses;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Anand Jakhaniya on 08-10-2017.
 * @author Anand Jakhaniya
 */

public class ApplicationMainClass extends Application {

    private final String TAG = this.getClass().getSimpleName();
    private static ApplicationMainClass ourInstance = null;
    /* Access preference file */
    public static SharedPreferences sharedPreferences;

    public static ApplicationMainClass getInstance() {
        if (ourInstance == null) {
            ourInstance = new ApplicationMainClass();
        }
        return ourInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        DatabaseSourceImpl.getInstance();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DatabaseSourceImpl baseDataSourceImpl = DatabaseSourceImpl.getInstance();
        baseDataSourceImpl.close();
    }
}
