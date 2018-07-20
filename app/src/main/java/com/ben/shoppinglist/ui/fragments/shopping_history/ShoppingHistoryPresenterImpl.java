package com.ben.shoppinglist.ui.fragments.shopping_history;

import com.ben.shoppinglist.core.App;
import com.ben.shoppinglist.data.DataManager;
import com.ben.shoppinglist.data.ListPresenterCallback;
import com.ben.shoppinglist.data.room.model.HistoryItem;
import com.ben.shoppinglist.data.room.model.ShoppingItem;

import java.util.List;

import javax.inject.Inject;

public class ShoppingHistoryPresenterImpl implements ShoppingHistoryPresenter<ShoppingHistoryView>, ListPresenterCallback {

    private ShoppingHistoryView view;

    @Inject
    public DataManager dataManager;

    public ShoppingHistoryPresenterImpl() {
        App.getAppInjector().inject(this);
    }

    @Override
    public void attachView(ShoppingHistoryView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void updateList() {
        dataManager.getHistoryItems(this);
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
    public void showList(List<?> list) {
        view.getListAdapter().addList((List<HistoryItem>) list);
        view.getListAdapter().notifyDataSetChanged();
    }
}
