package com.ben.shoppinglist.ui.fragments.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ben.shoppinglist.R;
import com.ben.shoppinglist.data.room.model.HistoryItem;
import com.ben.shoppinglist.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

public class HistoryListAdapterImpl extends RecyclerView.Adapter<HistoryListHolder> implements HistoryListAdapter {

    private List<HistoryItem> historyItems;
    private boolean descriptionMode;

    public HistoryListAdapterImpl() {
        historyItems = new ArrayList<>();
    }

    @Override
    public void addList(List<HistoryItem> list) {
        this.historyItems = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list_item, parent, false);
        return new HistoryListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HistoryListHolder holder, int position) {
        final HistoryItem item = historyItems.get(position);
        holder.tvHistoryDescription.setVisibility(View.GONE);
        holder.itemHistoryImage.setVisibility(View.GONE);
        holder.arrowDownHistory.setVisibility(View.VISIBLE);
        holder.arrowUpHistory.setVisibility(View.GONE);

        descriptionMode = false;

        if (item != null) {
            if (item.getName() != null && !item.getName().isEmpty()) {
                holder.tvHistoryItem.setText(item.getName());
            }
            if (item.getDescription() != null && !item.getDescription().isEmpty()) {
                holder.tvHistoryDescription.setText(item.getDescription());
            }
            if (item.getImage() != null && item.getImage().length != 0) {
                holder.itemHistoryImage.setImageBitmap(ImageUtil.byteToBitmap(item.getImage()));
            }
        }

        holder.setOnItemClickListener(new ItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.cardHistoryBtn:
                        if (!descriptionMode) {
                            descriptionMode = true;
                            holder.arrowDownHistory.setVisibility(View.GONE);
                            holder.arrowUpHistory.setVisibility(View.VISIBLE);
                            if (item != null && item.getDescription() != null && !item.getDescription().isEmpty()) {
                                holder.tvHistoryDescription.setVisibility(View.VISIBLE);
                            }
                            if (item != null && item.getImage() != null && item.getImage().length != 0) {
                                holder.itemHistoryImage.setVisibility(View.VISIBLE);
                            }
                        } else {
                            descriptionMode = false;
                            holder.arrowDownHistory.setVisibility(View.VISIBLE);
                            holder.arrowUpHistory.setVisibility(View.GONE);
                            holder.tvHistoryDescription.setVisibility(View.GONE);
                            holder.itemHistoryImage.setVisibility(View.GONE);
                        }
                    case R.id.itemImage:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return historyItems == null ? 0 : historyItems.size();
    }
}
