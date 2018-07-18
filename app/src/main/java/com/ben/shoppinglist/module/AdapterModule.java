package com.ben.shoppinglist.module;

import com.ben.shoppinglist.core.ScreenScope;
import com.ben.shoppinglist.ui.fragments.adapter.ShoppingListAdapter;
import com.ben.shoppinglist.ui.fragments.adapter.ShoppingListAdapterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class AdapterModule {

    @ScreenScope
    @Provides
    public ShoppingListAdapterImpl providesShoppingListAdapter() {
        return new ShoppingListAdapterImpl();
    }
}
