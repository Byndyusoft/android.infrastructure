package com.byndyusoft.androidinfrastructure.utils.keyboard;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 26.10.2016.
 */

public class KeyboardVisibilityTracker {

    private final View activityView;
    private final OnKeyboardVisibilityListener listener;

    private final float keyboardHeight;

    private final Rect r = new Rect();

    private boolean wasOpened;

    public KeyboardVisibilityTracker(Activity activity, OnKeyboardVisibilityListener listener) {
        this.listener = listener;

        this.activityView = activity.getWindow().getDecorView().getRootView();
        float keyBoardDP = (130 + (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? 48 : 0));
        this.keyboardHeight = (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, keyBoardDP, activityView.getResources().getDisplayMetrics());
    }

    public void startTracking() {
        activityView.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
    }

    @SuppressWarnings("deprecation")
    public void stopTracking() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            activityView.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
        } else {
            activityView.getViewTreeObserver().removeGlobalOnLayoutListener(onGlobalLayoutListener);
        }
    }

    public int getUsableHeight() {
        Rect r = new Rect();
        activityView.getWindowVisibleDisplayFrame(r);
        return (r.bottom - r.top);
    }

    public boolean isKeyBoardOpened() {
        return wasOpened;
    }

    public float getKeyboardHeight() {
        return keyboardHeight;
    }

    public void hideKeyBoard(@NonNull final Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        View focusedView = activity.getCurrentFocus();

        if (focusedView != null) {
            inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            focusedView.clearFocus();
        } else {
            inputManager.hideSoftInputFromInputMethod(activity.getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {

            activityView.getWindowVisibleDisplayFrame(r);
            int heightDiff = activityView.getRootView().getHeight() - (r.bottom - r.top);
            boolean isShown = heightDiff >= keyboardHeight;

            if (isShown == wasOpened) {
                return;
            }

            wasOpened = isShown;
            listener.onSoftInputVisibilityChanged(isShown);
        }
    };

    public interface OnKeyboardVisibilityListener {
        void onSoftInputVisibilityChanged(boolean state);
    }

}
