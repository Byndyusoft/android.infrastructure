package com.byndyusoft.androidinfrastructure.ui.viewPager;

import android.support.annotation.NonNull;
import android.view.View;

import com.byndyusoft.androidinfrastructure.R;
import com.byndyusoft.androidinfrastructure.ui.base.BaseFragment;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 02.11.2016.
 */

public class PageFragment extends BaseFragment {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_page;
    }

    @Override
    protected void onViewInflated(@NonNull View view) {

    }
}
