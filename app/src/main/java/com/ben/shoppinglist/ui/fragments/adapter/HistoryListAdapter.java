package com.ben.shoppinglist.ui.fragments.adapter;

import com.ben.shoppinglist.data.room.model.HistoryItem;

import java.util.List;

public interface HistoryListAdapter {
    void addList(List<HistoryItem> list);
}
