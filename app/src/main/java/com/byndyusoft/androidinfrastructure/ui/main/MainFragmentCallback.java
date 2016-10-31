package com.byndyusoft.androidinfrastructure.ui.main;

import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 31.10.2016.
 */

public interface MainFragmentCallback {
    void popBackStack();

    void setToolbarTitle(@Nullable String text);

    void setToolbarColor(@ColorInt int color);

    void setNavMenuEnabled(boolean isEnabled);

    void setFragmentFullscreen(boolean isFullscreen);
}
