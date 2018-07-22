package com.lxw.mileageselector;

import android.app.Application;

/**
 * Created by paul on 2018/7/22.
 * Description:
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    private static App app;

    public static App getAppContext() {
        return app;
    }
}
