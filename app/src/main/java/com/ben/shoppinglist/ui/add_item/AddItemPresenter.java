package com.ben.shoppinglist.ui.add_item;

import android.graphics.Bitmap;

import com.ben.shoppinglist.core.Presenter;

public interface AddItemPresenter<V extends AddItemView> extends Presenter<V> {

    void addNewItem(String name, String descr, Bitmap image);
}
