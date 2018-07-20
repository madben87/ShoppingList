package com.ben.shoppinglist.data;

import com.ben.shoppinglist.data.room.model.ShoppingItem;

import java.util.List;

public interface ListPresenterCallback {

    void itemIsAdded(ShoppingItem item);
    void itemNotAdded();
    void itemMarkAsPurchased();
    void itemNotMarkAsPurchased();
    void showList(List<?> list);
}
