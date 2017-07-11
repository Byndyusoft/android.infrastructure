package com.byndyusoft.androidinfrastructure.ui.viewPager;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.byndyusoft.androidinfrastructure.R;
import com.byndyusoft.androidinfrastructure.ui.main.MainFragment;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 31.10.2016.
 */

public class ViewPagerFragment extends MainFragment {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_view_pager;
    }

    @Override
    protected void onViewInflated(@NonNull View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        getMainFragmentCallback().setToolbar(toolbar);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.pager_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    protected boolean isNavMenuEnabled() {
        return true;
    }

    @Override
    protected boolean isOptionsMenuEnabled() {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getMainFragmentCallback().showNavigationMenu();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
