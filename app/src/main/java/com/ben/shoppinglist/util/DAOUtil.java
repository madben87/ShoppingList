package com.ben.shoppinglist.util;

import com.ben.shoppinglist.data.room.model.HistoryItem;
import com.ben.shoppinglist.data.room.model.ShoppingItem;

import java.util.ArrayList;
import java.util.List;

public class DAOUtil {

    public static List<HistoryItem> createHistoryList(List<ShoppingItem> list) {
        List<HistoryItem> result = new ArrayList<>();
        for (ShoppingItem elem : list) {
            HistoryItem item = new HistoryItem();
            if (elem.getName() != null && !elem.getName().isEmpty()) {
                item.setName(elem.getName());
            }
            if (elem.getDescription() != null && !elem.getDescription().isEmpty()) {
                item.setDescription(elem.getDescription());
            }
            if (elem.getImage() != null && elem.getImage().length > 0) {
                item.setImage(elem.getImage());
            }

            result.add(item);
        }

        return result;
    }

    public static HistoryItem createHistoryItem(ShoppingItem item) {
        HistoryItem res = new HistoryItem();
        if (item.getName() != null && !item.getName().isEmpty()) {
            res.setName(item.getName());
        }
        if (item.getDescription() != null && !item.getDescription().isEmpty()) {
            res.setDescription(item.getDescription());
        }
        if (item.getImage() != null && item.getImage().length > 0) {
            res.setImage(item.getImage());
        }

        return res;
    }
}
