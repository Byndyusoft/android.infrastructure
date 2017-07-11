package com.byndyusoft.androidinfrastructure.ui.main;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.byndyusoft.androidinfrastructure.R;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 31.10.2016.
 */

public interface MainFragmentCallback {
    void popBackStack();

    void setNavMenuEnabled(boolean isEnabled);

    void setToolbar(Toolbar toolbar);

    void showNavigationMenu();
}
