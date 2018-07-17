package com.ben.shoppinglist.core;

import com.ben.shoppinglist.module.ContextModule;
import com.ben.shoppinglist.module.DataModule;
import com.ben.shoppinglist.ui.add_item.AddItemPresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, DataModule.class})
public interface AppInjector {
    void inject(AddItemPresenterImpl presenter);
}
