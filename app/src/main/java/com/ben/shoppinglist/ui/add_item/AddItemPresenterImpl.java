package com.ben.shoppinglist.ui.add_item;

import android.graphics.Bitmap;

import com.ben.shoppinglist.core.App;
import com.ben.shoppinglist.data.DataManager;
import com.ben.shoppinglist.data.ListPresenterCallback;
import com.ben.shoppinglist.data.room.model.ShoppingItem;
import com.ben.shoppinglist.ui.main.MainActivity;
import com.ben.shoppinglist.util.ImageUtil;

import java.util.List;

import javax.inject.Inject;

public class AddItemPresenterImpl implements AddItemPresenter<AddItemView>, ListPresenterCallback {

    private AddItemView view;
    private ShoppingItem item;

    @Inject
    public DataManager dataManager;

    public AddItemPresenterImpl() {
        this.item = new ShoppingItem();
        App.getAppInjector().inject(this);
    }

    @Override
    public void attachView(AddItemView mvpView) {
        this.view = mvpView;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void addNewItem(String name, String descr, Bitmap image) {

        if (name.equals("") && (image == null)) {
            view.showMessage("Please add name or image");
        }else {
            if (!name.isEmpty()) {
                item.setName(name);
            }

            if (!descr.isEmpty()) {
                item.setDescription(descr);
            }

            if (image != null) {
                item.setImage(ImageUtil.bitmapToByte(image));
            }

            dataManager.newShoppingItem(this, item);
        }
    }

    @Override
    public void itemIsAdded() {
        view.showMessage("Item is added");
        view.launchActivity(MainActivity.class);
    }

    @Override
    public void itemNotAdded() {
        view.showMessage("Item is not added");
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
