package com.byndyusoft.androidinfrastructure.utils.view.recyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 26.10.2016.
 */

public abstract class GenericRecyclerViewAdapter<E>
        extends RecyclerView.Adapter<GenericRecyclerViewAdapter.ViewHolder<E>> {


    public List<E> list;

    public GenericRecyclerViewAdapter() {
        super();
    }

    @Override
    public final ViewHolder<E> onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = onCreateItemView(parent, viewType);
        ViewHolder<E> viewHolder = onCreateViewHolder(view, viewType);
        viewHolder.setOnHolderClickListener(getOnHolderClickListener(viewType));
        return viewHolder;
    }

    public abstract
    @NonNull
    ViewHolder<E> onCreateViewHolder(@NonNull final View itemView, int viewType);

    public abstract
    @LayoutRes
    int getLayoutResourceID(int viewType);

    @Nullable
    public abstract OnHolderClickListener<E> getOnHolderClickListener(final int viewType);

    public E getItem(int position) {
        return list.get(position);
    }

    public final View onCreateItemView(@NonNull final ViewGroup parent, final int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(getLayoutResourceID(viewType), parent, false);
    }

    @CallSuper
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder<E> holder, int position) {
        holder.fill(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public void setDataList(@Nullable List<E> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static class ViewHolder<E> extends RecyclerView.ViewHolder implements View.OnClickListener {

        public E data;
        public OnHolderClickListener<E> onHolderClickListener;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            this.itemView.setOnClickListener(this);
        }

        public void setOnHolderClickListener(@Nullable OnHolderClickListener<E> onHolderClickListener) {
            this.onHolderClickListener = onHolderClickListener;
        }

        @CallSuper
        public void fill(@NonNull final E data) {
            this.data = data;
        }


        @Override
        public void onClick(@NonNull View view) {
            if (onHolderClickListener != null) {
                onHolderClickListener.onHolderClick(this);
            }
        }

        protected
        @NonNull
        Resources getResources() {
            return itemView.getResources();
        }

        protected
        @NonNull
        Context getContext() {
            return itemView.getContext();
        }


        public void setSelected(boolean selected) {
            this.itemView.setSelected(selected);
        }

    }

    public interface OnHolderClickListener<E> {
        void onHolderClick(ViewHolder<E> holder);
    }

}
