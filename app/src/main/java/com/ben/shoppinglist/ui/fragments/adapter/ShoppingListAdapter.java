package com.ben.shoppinglist.ui.fragments.adapter;

import com.ben.shoppinglist.data.room.model.ShoppingItem;

import java.util.List;

public interface ShoppingListAdapter {

    void addList(List<ShoppingItem> list);
    void addItem(ShoppingItem item);
    void removeList(List<ShoppingItem> list);
    void removeItem(ShoppingItem item);
}
