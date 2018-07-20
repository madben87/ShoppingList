package com.ben.shoppinglist.ui.main;

import com.ben.shoppinglist.core.App;
import com.ben.shoppinglist.data.DataManager;
import com.ben.shoppinglist.data.ListPresenterCallback;
import com.ben.shoppinglist.data.room.model.ShoppingItem;
import com.ben.shoppinglist.util.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

public class MainPresenterImpl implements MainPresenter<MainView>, ListPresenterCallback {

    private MainView view;
    @Inject
    public DataManager dataManager;

    public MainPresenterImpl() {
        App.getAppInjector().inject(this);
    }

    @Override
    public void attachView(MainView mainView) {
        this.view = mainView;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void markAsPurchased(ShoppingItem item) {

    }

    @Override
    public void markAsPurchased(List<ShoppingItem> item) {
        dataManager.markAsPurchased(this, item);
    }

    @Override
    public void itemIsAdded(ShoppingItem item) {

    }

    @Override
    public void itemNotAdded() {

    }

    @Override
    public void itemMarkAsPurchased() {
        EventBus.getDefault().post(new MessageEvent(MessageEvent.ITEMS_IS_PURCHASED));
        EventBus.getDefault().post(new MessageEvent(MessageEvent.ITEMS_IS_PURCHASED));
    }

    @Override
    public void itemNotMarkAsPurchased() {

    }

    @Override
    public void showList(List<?> list) {

    }
}
