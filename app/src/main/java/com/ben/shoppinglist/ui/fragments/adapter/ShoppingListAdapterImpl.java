package com.ben.shoppinglist.ui.fragments.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ben.shoppinglist.R;
import com.ben.shoppinglist.data.room.model.ShoppingItem;
import com.ben.shoppinglist.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListAdapterImpl extends RecyclerView.Adapter<ShoppingListHolder> implements ShoppingListAdapter {

    private List<ShoppingItem>  shoppingItems;
    private boolean descriptionMode;

    public ShoppingListAdapterImpl() {
        shoppingItems = new ArrayList<>();
    }

    @Override
    public void addList(List<ShoppingItem> list) {
        shoppingItems = list;
    }

    @Override
    public void addItem(ShoppingItem item) {
        shoppingItems.add(item);
    }

    @Override
    public void removeList(List<ShoppingItem> list) {
        shoppingItems.removeAll(list);
    }

    @Override
    public void removeItem(ShoppingItem item) {
        shoppingItems.remove(item);
    }

    @NonNull
    @Override
    public ShoppingListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_list_item, parent, false);
        return new ShoppingListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShoppingListHolder holder, int position) {

        final ShoppingItem item = shoppingItems.get(position);
        holder.tvDescription.setVisibility(View.GONE);
        holder.itemImage.setVisibility(View.GONE);
        holder.arrowDown.setVisibility(View.VISIBLE);
        holder.arrowUp.setVisibility(View.GONE);
        descriptionMode = false;

        if (item != null) {
            if (item.getName() != null && !item.getName().isEmpty()) {
                holder.tvItem.setText(item.getName());
            }
            if (item.getDescription() != null && !item.getDescription().isEmpty()) {
                holder.tvDescription.setText(item.getDescription());
            }
            if (item.getImage() != null && item.getImage().length != 0) {
                holder.itemImage.setImageBitmap(ImageUtil.byteToBitmap(item.getImage()));
            }
        }

        holder.setOnItemClickListener(new ItemClick() {
            @Override
            public void onItemClick(View view, int position) {

                switch (view.getId()) {
                    case R.id.cardBtn:
                        if (!descriptionMode) {
                            descriptionMode = true;
                            holder.arrowDown.setVisibility(View.GONE);
                            holder.arrowUp.setVisibility(View.VISIBLE);
                            if (item != null && item.getDescription() != null && !item.getDescription().isEmpty()) {
                                holder.tvDescription.setVisibility(View.VISIBLE);
                            }
                            if (item != null && item.getImage() != null && item.getImage().length != 0) {
                                holder.itemImage.setVisibility(View.VISIBLE);
                            }
                        }else {
                            descriptionMode = false;
                            holder.arrowDown.setVisibility(View.VISIBLE);
                            holder.arrowUp.setVisibility(View.GONE);
                            holder.tvDescription.setVisibility(View.GONE);
                            holder.itemImage.setVisibility(View.GONE);
                        }
                        break;
                    case R.id.cbItem:
                        break;
                    case R.id.itemImage:
                        break;
                }
            }
        });

        holder.setOnLongItemClickListener(new LongItemClick() {
            @Override
            public void onLongItemClick(View view, int position) {
                if (view.getId() == R.id.cardListItem) {

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoppingItems.size();
    }
}
