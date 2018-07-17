package com.ben.shoppinglist.core;

import com.ben.shoppinglist.module.PresenterModule;
import com.ben.shoppinglist.ui.add_item.AddItemActivity;
import com.ben.shoppinglist.ui.main.MainActivity;
import com.ben.shoppinglist.ui.fragments.shopping_list.ShoppingListFragment;

import dagger.Component;

@ScreenScope
@Component(modules = PresenterModule.class)
public interface ScreenInjector {
    void inject(MainActivity activity);
    void inject(ShoppingListFragment activity);
    void inject(AddItemActivity activity);
}
