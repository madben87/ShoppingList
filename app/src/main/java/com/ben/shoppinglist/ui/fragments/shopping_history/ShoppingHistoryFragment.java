package com.ben.shoppinglist.ui.fragments.shopping_history;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ben.shoppinglist.R;
import com.ben.shoppinglist.ui.fragments.ListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShoppingHistoryFragment extends ListFragment implements ShoppingHistoryView {

    @BindView(R.id.resViewHistory)
    RecyclerView resViewHistory;

    private Unbinder unbinder;

    public ShoppingHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_history, container, false);

        unbinder = ButterKnife.bind(this, view);

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
}
