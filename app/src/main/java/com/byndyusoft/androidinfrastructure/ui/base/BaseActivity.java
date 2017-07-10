package com.byndyusoft.androidinfrastructure.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.byndyusoft.androidinfrastructure.R;
import com.byndyusoft.androidinfrastructure.utils.keyboard.KeyboardVisibilityTracker;
import com.byndyusoft.androidinfrastructure.utils.permission.PermissionsManager;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 28.10.2016.
 */

public abstract class BaseActivity extends RxAppCompatActivity implements KeyboardVisibilityTracker.OnKeyboardVisibilityListener {

    protected final String TAG = getClass().getSimpleName();

    private KeyboardVisibilityTracker keyboardVisibilityTracker;
    private PermissionsManager permissionsManager = new PermissionsManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        keyboardVisibilityTracker = new KeyboardVisibilityTracker(this, this);

        int layoutId = getLayoutId();
        if (layoutId != View.NO_ID) {
            setContentView(layoutId);
        }
    }

    public Context getContext() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        keyboardVisibilityTracker.startTracking();
        adjustRootViewHeight();
    }

    @Override
    public void onSoftInputVisibilityChanged(boolean state) {
        adjustRootViewHeight();
    }

    protected void adjustRootViewHeight() {
        View contentView = findViewById(android.R.id.content);
        ViewGroup.LayoutParams params = contentView.getLayoutParams();
        params.height = keyboardVisibilityTracker.isKeyBoardOpened() ? keyboardVisibilityTracker.getUsableHeight() + getStatusBarHeight() : ViewGroup.LayoutParams.MATCH_PARENT;
        contentView.setLayoutParams(params);
    }

    protected int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    protected int getActionBarSize() {
        TypedValue tv = new TypedValue();

        if (getTheme().resolveAttribute(R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        return 0;
    }

    @Override
    protected void onPause() {
        super.onPause();
        keyboardVisibilityTracker.stopTracking();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    protected PermissionsManager getPermissionsManager(){
        return permissionsManager;
    }

    @LayoutRes
    protected abstract int getLayoutId();

}
