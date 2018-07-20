package com.ben.shoppinglist.ui.fragments.shopping_list;

import com.ben.shoppinglist.core.App;
import com.ben.shoppinglist.core.MVPView;
import com.ben.shoppinglist.data.DataManager;
import com.ben.shoppinglist.data.ListPresenterCallback;
import com.ben.shoppinglist.data.room.model.ShoppingItem;

import java.util.List;

import javax.inject.Inject;

public class ShoppingListPresenterImpl implements ShoppingListPresenter<ShoppingListView>, ListPresenterCallback {

    private ShoppingListView view;

    @Inject
    public DataManager dataManager;

    public ShoppingListPresenterImpl() {
        App.getAppInjector().inject(this);
    }

    @Override
    public void attachView(ShoppingListView mvpView) {
        this.view = mvpView;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void updateList() {
        dataManager.getShoppingItems(this);
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
        view.getListAdapter().addList((List<ShoppingItem>) list);
        view.getListAdapter().notifyDataSetChanged();
    }
}
