package com.ben.shoppinglist.ui.fragments.shopping_history;

import com.ben.shoppinglist.core.MVPView;
import com.ben.shoppinglist.ui.fragments.adapter.HistoryListAdapterImpl;

public interface ShoppingHistoryView extends MVPView {

    HistoryListAdapterImpl getListAdapter();
}
