package com.byndyusoft.androidinfrastructure.ui.main;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.byndyusoft.androidinfrastructure.R;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 31.10.2016.
 */

public interface MainFragmentCallback {
    enum NavButtonType {
        None(0), Menu(R.drawable.ic_menu_white), Arrow(R.drawable.ic_arrow_back_white);
        @DrawableRes
        final int iconRes;

        NavButtonType(final int iconRes) {
            this.iconRes = iconRes;
        }
    }

    void popBackStack();

    void setToolbarTitle(@Nullable String text);

    void setToolbarColor(@ColorInt int color);

    void setNavMenuEnabled(boolean isEnabled);

    void setFragmentFullscreen(boolean isFullscreen);

    void setNavigationButtonType(@NonNull final NavButtonType navigationButtonType);
}
