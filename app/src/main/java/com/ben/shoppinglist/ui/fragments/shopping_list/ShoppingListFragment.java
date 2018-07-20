package com.ben.shoppinglist.ui.fragments.shopping_list;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ben.shoppinglist.R;
import com.ben.shoppinglist.core.App;
import com.ben.shoppinglist.data.room.model.ShoppingItem;
import com.ben.shoppinglist.ui.fragments.ListFragment;
import com.ben.shoppinglist.ui.fragments.ListFragmentCallback;
import com.ben.shoppinglist.ui.fragments.adapter.ShoppingListAdapter;
import com.ben.shoppinglist.ui.fragments.adapter.ShoppingListAdapterImpl;
import com.ben.shoppinglist.util.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShoppingListFragment extends ListFragment implements ShoppingListView, ListFragmentCallback {

    @BindView(R.id.resView)
    protected RecyclerView resView;
    private Unbinder unbinder;

    @Inject
    ShoppingListPresenterImpl presenter;
    @Inject
    ShoppingListAdapterImpl listAdapter;

    public ShoppingListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        unbinder = ButterKnife.bind(this, view);
        App.getScreenInjector().inject(this);

        presenter.attachView(this);

        resView.setHasFixedSize(true);
        resView.setLayoutManager(new LinearLayoutManager(getContext()));
        resView.setAdapter(listAdapter);
        listAdapter.setCallback(this);

        presenter.updateList();

        EventBus.getDefault().register(this);

        return view;
    }

    @Override
    public List<ShoppingItem> getList() {
        return listAdapter.getSelectedList();
    }

    @Override
    public ShoppingListAdapterImpl getListAdapter() {
        return listAdapter;
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {

        switch (event.msg) {
            case MessageEvent.ON_BACK_PRESSED:
                listAdapter.setMultiSelectedMode(false);
                break;
            case MessageEvent.PAGE_CHANGE_STATE:
                listAdapter.setMultiSelectedMode(false);
                break;
            case MessageEvent.ITEMS_IS_PURCHASED:
                presenter.updateList();
                listAdapter.setMultiSelectedMode(false);
                break;
            case MessageEvent.SELECT_ALL_ITEMS:
                listAdapter.selectAllItems();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showImageDialog(Bitmap image) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.image_dialog, null);
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageBitmap(image);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
