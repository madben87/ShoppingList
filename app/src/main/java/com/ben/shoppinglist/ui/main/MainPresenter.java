package com.ben.shoppinglist.ui.main;

import com.ben.shoppinglist.core.Presenter;
import com.ben.shoppinglist.data.room.model.ShoppingItem;

import java.util.List;

public interface MainPresenter<V extends MainView> extends Presenter<V> {

    //void addNewItem(ShoppingItem item);
    void markAsPurchased(ShoppingItem item);
    void markAsPurchased(List<ShoppingItem> item);
    //void showShoppingList();
}
