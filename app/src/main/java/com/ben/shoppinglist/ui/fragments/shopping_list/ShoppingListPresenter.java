package com.ben.shoppinglist.ui.fragments.shopping_list;

import com.ben.shoppinglist.core.Presenter;

public interface ShoppingListPresenter<V extends ShoppingListView> extends Presenter<V> {

    void updateList();
}
