package com.ben.shoppinglist.ui.main;

import com.ben.shoppinglist.data.room.model.ShoppingItem;

import java.util.List;

public class MainPresenterImpl implements MainPresenter<MainView> {

    private MainView view;

    @Override
    public void attachView(MainView mainView) {
        this.view = mainView;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
