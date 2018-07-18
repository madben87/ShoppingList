package com.ben.shoppinglist.ui.fragments.shopping_history;

import com.ben.shoppinglist.data.ListPresenterCallback;
import com.ben.shoppinglist.data.room.model.ShoppingItem;

import java.util.List;

public class ShoppingHistoryPresenterImpl implements ShoppingHistoryPresenter<ShoppingHistoryView>, ListPresenterCallback {

    private ShoppingHistoryView view;

    @Override
    public void attachView(ShoppingHistoryView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void itemIsAdded(ShoppingItem item) {

    }

    @Override
    public void itemNotAdded() {

    }

    @Override
    public void itemMarkAsPurchased() {

    }

    @Override
    public void itemNotMarkAsPurchased() {

    }

    @Override
    public void showList(List<ShoppingItem> list) {

    }
}
