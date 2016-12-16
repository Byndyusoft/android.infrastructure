package com.byndyusoft.androidinfrastructure.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byndyusoft.androidinfrastructure.utils.keyboard.KeyboardVisibilityTracker;
import com.byndyusoft.androidinfrastructure.utils.permission.PermissionsManager;
import com.trello.rxlifecycle.components.support.RxFragment;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 28.10.2016.
 */

public abstract class BaseFragment extends RxFragment implements KeyboardVisibilityTracker.OnKeyboardVisibilityListener {

    protected final String TAG = getClass().getSimpleName();

    private KeyboardVisibilityTracker keyboardVisibilityTracker;
    private PermissionsManager permissionsManager = new PermissionsManager();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        keyboardVisibilityTracker = new KeyboardVisibilityTracker(getActivity(), this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        onViewInflated(view);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        keyboardVisibilityTracker.startTracking();
    }

    @Override
    public void onPause() {
        super.onPause();
        keyboardVisibilityTracker.stopTracking();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onSoftInputVisibilityChanged(boolean state) {

    }

    protected KeyboardVisibilityTracker getKeyboardVisibilityTracker() {
        return keyboardVisibilityTracker;
    }

    protected PermissionsManager getPermissionsHelper(){
        return permissionsManager;
    }

    protected void hideKeyBoard() {
        getKeyboardVisibilityTracker().hideKeyBoard(getActivity());
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void onViewInflated(@NonNull final View view);

}
