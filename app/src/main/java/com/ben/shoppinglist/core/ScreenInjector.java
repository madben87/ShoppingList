package com.ben.shoppinglist.core;

import android.content.Context;

import com.ben.shoppinglist.module.AdapterModule;
import com.ben.shoppinglist.module.PresenterModule;
import com.ben.shoppinglist.ui.add_item.AddItemActivity;
import com.ben.shoppinglist.ui.fragments.shopping_history.ShoppingHistoryFragment;
import com.ben.shoppinglist.ui.main.MainActivity;
import com.ben.shoppinglist.ui.fragments.shopping_list.ShoppingListFragment;

import dagger.Component;

@ScreenScope
@Component(dependencies = AppInjector.class, modules = {PresenterModule.class, AdapterModule.class})
public interface ScreenInjector {
    Context context();
    void inject(MainActivity activity);
    void inject(ShoppingListFragment activity);
    void inject(ShoppingHistoryFragment activity);
    void inject(AddItemActivity activity);
}
