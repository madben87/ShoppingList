package com.ben.shoppinglist.data.room.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ben.shoppinglist.data.room.model.HistoryItem;
import com.ben.shoppinglist.data.room.model.ShoppingItem;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public interface HistoryItemDao {
    @Query("SELECT * FROM history_items")
    Flowable<List<HistoryItem>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(HistoryItem item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<HistoryItem> items);

    @Delete
    void delete(HistoryItem item);

    @Delete
    void delete(List<HistoryItem>items);
}
