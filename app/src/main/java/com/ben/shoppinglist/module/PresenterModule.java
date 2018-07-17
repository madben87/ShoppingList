package com.ben.shoppinglist.module;

import com.ben.shoppinglist.core.ScreenScope;
import com.ben.shoppinglist.ui.add_item.AddItemPresenterImpl;
import com.ben.shoppinglist.ui.main.MainPresenterImpl;
import com.ben.shoppinglist.ui.fragments.shopping_list.ShoppingListPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @ScreenScope
    @Provides
    public MainPresenterImpl providesPresenter() {
        return new MainPresenterImpl();
    }

    @ScreenScope
    @Provides
    public ShoppingListPresenterImpl providesShoppingListPresenter() {return new ShoppingListPresenterImpl();}

    @ScreenScope
    @Provides
    public AddItemPresenterImpl providesAddItemPresenter() {return new AddItemPresenterImpl();}
}
