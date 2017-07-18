package com.byndyusoft.androidinfrastructure.ui.list;

import com.byndyusoft.androidinfrastructure.core.listItem.ListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 18.07.2017.
 */
public class ListPresenter {

    public final ListView view;

    public ListPresenter(ListView view) {
        this.view = view;
    }

    public void getListData() {
        List<ListItem> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(new ListItem("John", "Doe", 25 + i));
        }

        view.setData(list);
    }
}
