package com.byndyusoft.androidinfrastructure.ui.main;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.byndyusoft.androidinfrastructure.R;
import com.byndyusoft.androidinfrastructure.ui.base.BaseFragment;

import javax.inject.Inject;

import hugo.weaving.DebugLog;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 31.10.2016.
 */

public abstract class MainFragment extends BaseFragment {

    @Inject
    MainFragmentCallback mainFragmentCallback;

    @Inject
    MainRouter mainRouter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).getComponent().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setDefaultNavOptions();
    }

    @DebugLog
    protected void setDefaultNavOptions() {
        getMainFragmentCallback().setNavMenuEnabled(isNavMenuEnabled());
        getMainFragmentCallback().setToolbarColor(getToolbarColor());
        getMainFragmentCallback().setToolbarTitle(getToolbarTitle());
        getMainFragmentCallback().setFragmentFullscreen(isFullscreen());
        getMainFragmentCallback().setNavigationButtonType(getNavButtonType());
    }

    protected MainFragmentCallback getMainFragmentCallback() {
        return mainFragmentCallback;
    }

    protected MainRouter getRouter() {
        return mainRouter;
    }

    protected void popBackStack() {
        getMainFragmentCallback().popBackStack();
    }

    protected boolean isNavMenuEnabled() {
        return true;
    }

    protected boolean isFullscreen() {
        return false;
    }

    protected MainFragmentCallback.NavButtonType getNavButtonType() {
        return MainFragmentCallback.NavButtonType.None;
    }

    protected
    @ColorInt
    int getToolbarColor() {
        return ContextCompat.getColor(getContext(), R.color.colorPrimary);
    }

    protected
    @Nullable
    String getToolbarTitle() {
        return null;
    }

}
