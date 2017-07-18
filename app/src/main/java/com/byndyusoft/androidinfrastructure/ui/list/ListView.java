package com.byndyusoft.androidinfrastructure.ui.list;

import com.byndyusoft.androidinfrastructure.core.listItem.ListItem;

import java.util.List;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 18.07.2017.
 */
public interface ListView {
    void setData(List<ListItem> items);
}
