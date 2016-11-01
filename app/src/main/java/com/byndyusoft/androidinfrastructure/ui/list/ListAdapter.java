package com.byndyusoft.androidinfrastructure.ui.list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.byndyusoft.androidinfrastructure.R;
import com.byndyusoft.androidinfrastructure.binding.ListItemBinding;
import com.byndyusoft.androidinfrastructure.core.listItem.ListItem;
import com.byndyusoft.androidinfrastructure.utils.view.recyclerView.GenericRecyclerViewAdapter;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 01.11.2016.
 */

public class ListAdapter extends GenericRecyclerViewAdapter<ListItem> {
    @NonNull
    @Override
    public GenericRecyclerViewAdapter.ViewHolder<ListItem> onCreateViewHolder(@NonNull View itemView, int viewType) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutResourceID(int viewType) {
        return R.layout.list_item;
    }

    @Nullable
    @Override
    public OnHolderClickListener<ListItem> getOnHolderClickListener(int viewType) {
        return null;
    }

    private class ViewHolder extends GenericRecyclerViewAdapter.ViewHolder<ListItem> {

        private ListItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ListItemBinding.bind(itemView);
        }

        @Override
        public void fill(@NonNull ListItem data) {
            super.fill(data);
            binding.setItem(data);
        }
    }
}
