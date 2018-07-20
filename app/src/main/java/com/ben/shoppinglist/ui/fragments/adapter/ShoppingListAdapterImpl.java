package com.ben.shoppinglist.ui.fragments.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ben.shoppinglist.R;
import com.ben.shoppinglist.data.room.model.ShoppingItem;
import com.ben.shoppinglist.ui.fragments.ListFragmentCallback;
import com.ben.shoppinglist.util.ImageUtil;
import com.ben.shoppinglist.util.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListAdapterImpl extends RecyclerView.Adapter<ShoppingListHolder> implements ShoppingListAdapter {

    private List<ShoppingItem>  shoppingItems;
    private List<ShoppingItem>  selectedList;
    private boolean descriptionMode;
    private boolean multiSelectedMode;
    private boolean selectAllMode;

    private Context context;
    private ListFragmentCallback callback;

    public void setCallback(ListFragmentCallback callback) {
        this.callback = callback;
    }

    public boolean isMultiSelectedMode() {
        return multiSelectedMode;
    }

    public void setMultiSelectedMode(boolean multiSelectedMode) {
        if (multiSelectedMode) {
            this.multiSelectedMode = multiSelectedMode;
            EventBus.getDefault().post(new MessageEvent(MessageEvent.MULTI_SELECTED_MODE_ON));
            notifyDataSetChanged();
        }else {
            this.multiSelectedMode = multiSelectedMode;
            selectAllMode = false;
            selectedList.clear();
            EventBus.getDefault().post(new MessageEvent(MessageEvent.MULTI_SELECTED_MODE_OFF));
            notifyDataSetChanged();
        }
    }

    public void selectAllItems() {
        if (selectedList.size() < shoppingItems.size()) {
            selectedList.addAll(shoppingItems);
            selectAllMode = true;
            notifyDataSetChanged();
        }else {
            selectedList.clear();
            selectAllMode = false;
            notifyDataSetChanged();
        }
    }

    public List<ShoppingItem> getSelectedList() {
        return selectedList;
    }

    public ShoppingListAdapterImpl(Context context) {
        shoppingItems = new ArrayList<>();
        selectedList = new ArrayList<>();
        this.context = context;
    }

    @Override
    public void addList(List<ShoppingItem> list) {
        shoppingItems = list;
        notifyDataSetChanged();
    }

    @Override
    public void addItem(ShoppingItem item) {
        shoppingItems.add(item);
        notifyDataSetChanged();
    }

    @Override
    public void removeList(List<ShoppingItem> list) {
        shoppingItems.removeAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void removeItem(ShoppingItem item) {
        shoppingItems.remove(item);
        notifyDataSetChanged();
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
        holder.cbItem.setChecked(false);
        if (multiSelectedMode) {
            holder.cbLayout.setVisibility(View.VISIBLE);
            if (selectAllMode) {
                holder.cbItem.setChecked(true);
            }else {
                holder.cbItem.setChecked(false);
            }
        }else {
            holder.cbLayout.setVisibility(View.GONE);
        }
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
                        if (!multiSelectedMode) {
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
                            } else {
                                descriptionMode = false;
                                holder.arrowDown.setVisibility(View.VISIBLE);
                                holder.arrowUp.setVisibility(View.GONE);
                                holder.tvDescription.setVisibility(View.GONE);
                                holder.itemImage.setVisibility(View.GONE);
                            }
                        }else {
                            if (!holder.cbItem.isChecked()) {
                                holder.cbItem.setChecked(!holder.cbItem.isChecked());
                                selectedList.add(item);
                            }else {
                                holder.cbItem.setChecked(!holder.cbItem.isChecked());
                                selectedList.remove(item);
                            }
                        }
                        break;
                    case R.id.cbItem:
                        if (holder.cbItem.isChecked()) {
                            selectedList.add(item);
                        }else {
                            selectedList.remove(item);
                        }
                        break;
                    case R.id.itemImage:
                        //callback.showImageDialog(ImageUtil.byteToBitmap(item.getImage()));
                        break;
                }
            }
        });

        holder.setOnLongItemClickListener(new LongItemClick() {
            @Override
            public void onLongItemClick(View view, int position) {
                if (view.getId() == R.id.cardBtn) {
                    setMultiSelectedMode(!multiSelectedMode);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoppingItems == null ? 0 : shoppingItems.size();
    }
}
