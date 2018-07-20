package com.ben.shoppinglist.module;

import android.content.Context;

import com.ben.shoppinglist.core.ScreenScope;
import com.ben.shoppinglist.ui.fragments.adapter.HistoryListAdapterImpl;
import com.ben.shoppinglist.ui.fragments.adapter.ShoppingListAdapter;
import com.ben.shoppinglist.ui.fragments.adapter.ShoppingListAdapterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class AdapterModule {

    @ScreenScope
    @Provides
    public ShoppingListAdapterImpl providesShoppingListAdapter(Context context) {
        return new ShoppingListAdapterImpl(context);
    }

    @ScreenScope
    @Provides
    public HistoryListAdapterImpl providesHistoryListAdapter() {
        return new HistoryListAdapterImpl();
    }
}
