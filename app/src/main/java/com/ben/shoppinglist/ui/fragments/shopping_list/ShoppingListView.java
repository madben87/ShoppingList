package com.ben.shoppinglist.ui.fragments.shopping_list;

import com.ben.shoppinglist.core.MVPView;
import com.ben.shoppinglist.ui.fragments.adapter.ShoppingListAdapter;
import com.ben.shoppinglist.ui.fragments.adapter.ShoppingListAdapterImpl;

public interface ShoppingListView extends MVPView {

    ShoppingListAdapterImpl getListAdapter();
}
