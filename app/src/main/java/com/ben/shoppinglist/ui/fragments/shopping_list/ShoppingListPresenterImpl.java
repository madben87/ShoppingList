package com.ben.shoppinglist.ui.fragments.shopping_list;

import com.ben.shoppinglist.core.MVPView;
import com.ben.shoppinglist.data.ListPresenterCallback;
import com.ben.shoppinglist.data.room.model.ShoppingItem;

import java.util.List;

public class ShoppingListPresenterImpl implements ShoppingListPresenter, ListPresenterCallback {
    @Override
    public void attachView(MVPView mvpView) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void itemIsAdded() {

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
