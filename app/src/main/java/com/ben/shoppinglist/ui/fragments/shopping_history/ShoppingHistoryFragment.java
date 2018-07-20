package com.ben.shoppinglist.ui.fragments.shopping_history;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ben.shoppinglist.R;
import com.ben.shoppinglist.core.App;
import com.ben.shoppinglist.data.room.model.ShoppingItem;
import com.ben.shoppinglist.ui.fragments.ListFragment;
import com.ben.shoppinglist.ui.fragments.adapter.HistoryListAdapterImpl;
import com.ben.shoppinglist.util.ImageUtil;
import com.ben.shoppinglist.util.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShoppingHistoryFragment extends ListFragment implements ShoppingHistoryView {

    @BindView(R.id.resViewHistory)
    RecyclerView resViewHistory;

    private Unbinder unbinder;

    @Inject
    ShoppingHistoryPresenterImpl presenter;
    @Inject
    HistoryListAdapterImpl listAdapter;

    public ShoppingHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_history, container, false);

        unbinder = ButterKnife.bind(this, view);
        App.getScreenInjector().inject(this);
        presenter.attachView(this);

        resViewHistory.setHasFixedSize(true);
        resViewHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        resViewHistory.setAdapter(listAdapter);

        presenter.updateList();

        EventBus.getDefault().register(this);

        return view;
    }



    @Override
    public void launchActivity(Class<? extends Activity> cls) {
        Intent intent = new Intent(getContext(), cls);
        startActivity(intent);
    }

    @Override
    public void launchActivity(Class<? extends Activity> cls, int flag) {
        Intent intent = new Intent(getContext(), cls);
        intent.addFlags(flag);
        startActivity(intent);
    }

    @Override
    public List<ShoppingItem> getList() {
        return null;
    }

    @Override
    public HistoryListAdapterImpl getListAdapter() {
        return listAdapter;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {

        switch (event.msg) {
            case MessageEvent.ITEMS_IS_PURCHASED:
                presenter.updateList();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.detachView();
        EventBus.getDefault().unregister(this);
    }
}
