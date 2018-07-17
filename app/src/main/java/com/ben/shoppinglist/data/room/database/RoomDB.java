package com.ben.shoppinglist.data.room.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.ben.shoppinglist.data.room.model.HistoryItem;
import com.ben.shoppinglist.data.room.model.ShoppingItem;

@Database(entities = {ShoppingItem.class, HistoryItem.class}, version = 1)
public abstract class RoomDB extends RoomDatabase {

    private static final String DB_NAME = "shopping_list_db";

    public abstract ShoppingItemsDao shoppingItemsDao();

    public abstract HistoryItemDao historyItemDao();

    public static String getDbName() {
        return DB_NAME;
    }
}
