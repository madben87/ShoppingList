package com.ben.shoppinglist.ui.fragments.shopping_list;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ben.shoppinglist.R;
import com.ben.shoppinglist.core.App;
import com.ben.shoppinglist.ui.fragments.ListFragment;
import com.ben.shoppinglist.ui.fragments.adapter.ShoppingListAdapter;
import com.ben.shoppinglist.ui.fragments.adapter.ShoppingListAdapterImpl;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShoppingListFragment extends ListFragment implements ShoppingListView {

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

        presenter.updateList();

        return view;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
        unbinder.unbind();
    }
}
