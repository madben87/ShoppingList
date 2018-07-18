package com.ben.shoppinglist.ui.fragments.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ben.shoppinglist.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingListHolder extends RecyclerView.ViewHolder implements AdapterView.OnClickListener, View.OnLongClickListener {

    @BindView(R.id.cardListItem)
    CardView cardListItem;
    @BindView(R.id.cardBtn)
    LinearLayout cardBtn;
    @BindView(R.id.tvItem)
    TextView tvItem;
    @BindView(R.id.arrowUp)
    ImageView arrowUp;
    @BindView(R.id.arrowDown)
    ImageView arrowDown;
    @BindView(R.id.cbItem)
    CheckBox cbItem;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.itemImage)
    ImageView itemImage;

    private ItemClick itemClick;
    private LongItemClick itemLongClick;

    public ShoppingListHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        cardListItem.setPreventCornerOverlap(false);
        cardBtn.setOnClickListener(this);
        cbItem.setOnClickListener(this);
        itemImage.setOnClickListener(this);
        cardListItem.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClick.onItemClick(v, this.getLayoutPosition());
    }

    public void setOnItemClickListener(ItemClick listener) {
        this.itemClick = listener;
    }

    @Override
    public boolean onLongClick(View v) {
        itemLongClick.onLongItemClick(v, this.getLayoutPosition());
        return true;
    }

    public void setOnLongItemClickListener(LongItemClick listener) {
        this.itemLongClick = listener;
    }
}
