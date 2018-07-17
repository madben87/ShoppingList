package com.ben.shoppinglist.core;

import android.app.Application;

import com.ben.shoppinglist.module.ContextModule;
import com.ben.shoppinglist.module.DataModule;
import com.ben.shoppinglist.module.PresenterModule;
import com.ben.shoppinglist.ui.main.MainPresenterImpl;

public class App extends Application {

    private static App appInstance;
    private AppInjector appInjector;
    private ScreenInjector screenInjector;

    public static App getAppInstance() {
        return appInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appInstance = this;

        appInjector = DaggerAppInjector
                .builder()
                .contextModule(new ContextModule(this))
                .dataModule(new DataModule())
                .build();
        screenInjector = DaggerScreenInjector
                .builder()
                .presenterModule(new PresenterModule())
                .build();
    }

    public static AppInjector getAppInjector() {
        return getAppInstance().appInjector;
    }

    public static ScreenInjector getScreenInjector() {
        return getAppInstance().screenInjector;
    }
}
