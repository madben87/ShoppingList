package com.ben.shoppinglist.data.room.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ben.shoppinglist.data.room.model.ShoppingItem;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface HistoryItemDao {
    @Query("SELECT * FROM history_items")
    Maybe<List<ShoppingItem>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ShoppingItem item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<ShoppingItem> items);

    @Delete
    void delete(ShoppingItem item);

    @Delete
    void delete(List<ShoppingItem>items);
}
