package com.ben.shoppinglist.module;

import android.content.Context;

import com.ben.shoppinglist.data.DataManager;
import com.ben.shoppinglist.data.room.database.RoomDataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Singleton
    @Provides
    public DataManager providesData(Context context) {
        return new RoomDataManager(context);
    }
}
