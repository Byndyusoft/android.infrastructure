package com.byndyusoft.androidinfrastructure.utils.permission;

import hugo.weaving.DebugLog;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 26.10.2016.
 */

public interface OnPermissionsCancelled {
    @DebugLog
    void onCancelled();
}
