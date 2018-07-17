package com.ben.shoppinglist.data;

import com.ben.shoppinglist.data.room.model.ShoppingItem;

import java.util.List;

public interface DataManager {

    //void newShoppingItem(ShoppingItem item);
    void newShoppingItem(ListPresenterCallback callback, ShoppingItem item);
    void getShoppingItems(ListPresenterCallback callback);
    void getHistoryItems(ListPresenterCallback callback);
    void markAsPurchased(ListPresenterCallback callback, ShoppingItem item);
    void markAsPurchased(ListPresenterCallback callback, List<ShoppingItem> item);
}
