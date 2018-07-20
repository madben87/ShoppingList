package com.ben.shoppinglist.ui.fragments.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ben.shoppinglist.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryListHolder extends RecyclerView.ViewHolder implements AdapterView.OnClickListener {

    @BindView(R.id.cardHistoryListItem)
    CardView cardHistoryListItem;
    @BindView(R.id.cardHistoryBtn)
    LinearLayout cardHistoryBtn;
    @BindView(R.id.tvHistoryItem)
    TextView tvHistoryItem;
    @BindView(R.id.arrowUpHistory)
    ImageView arrowUpHistory;
    @BindView(R.id.arrowDownHistory)
    ImageView arrowDownHistory;
    @BindView(R.id.tvHistoryDescription)
    TextView tvHistoryDescription;
    @BindView(R.id.itemHistoryImage)
    ImageView itemHistoryImage;

    private ItemClick itemClick;

    public HistoryListHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        cardHistoryListItem.setPreventCornerOverlap(false);
        cardHistoryBtn.setOnClickListener(this);
        itemHistoryImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClick.onItemClick(v, this.getLayoutPosition());
    }

    public void setOnItemClickListener(ItemClick listener) {
        this.itemClick = listener;
    }
}
