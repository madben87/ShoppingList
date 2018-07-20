package com.ben.shoppinglist.core;

import android.content.Context;

import com.ben.shoppinglist.module.ContextModule;
import com.ben.shoppinglist.module.DataModule;
import com.ben.shoppinglist.ui.add_item.AddItemPresenterImpl;
import com.ben.shoppinglist.ui.fragments.shopping_history.ShoppingHistoryPresenterImpl;
import com.ben.shoppinglist.ui.fragments.shopping_list.ShoppingListPresenterImpl;
import com.ben.shoppinglist.ui.main.MainPresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, DataModule.class})
public interface AppInjector {
    Context context();
    void inject(AddItemPresenterImpl presenter);
    void inject(ShoppingListPresenterImpl presenter);
    void inject(ShoppingHistoryPresenterImpl presenter);
    void inject(MainPresenterImpl presenter);
}
