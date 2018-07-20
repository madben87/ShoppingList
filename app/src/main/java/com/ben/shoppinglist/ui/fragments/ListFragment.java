package com.ben.shoppinglist.ui.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;

import com.ben.shoppinglist.data.room.model.ShoppingItem;

import java.util.List;

public abstract class ListFragment extends Fragment {

    public abstract List<ShoppingItem> getList();
}
