package com.byndyusoft.androidinfrastructure.ui.main;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.byndyusoft.androidinfrastructure.R;
import com.byndyusoft.androidinfrastructure.di.components.ActivityMainComponent;
import com.byndyusoft.androidinfrastructure.di.components.DaggerActivityMainComponent;
import com.byndyusoft.androidinfrastructure.di.modules.ActivityMainModule;
import com.byndyusoft.androidinfrastructure.ui.base.BaseActivity;

import javax.inject.Inject;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 28.10.2016.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener, MainFragmentCallback,
        NavigationView.OnNavigationItemSelectedListener {

    @Inject
    MainRouter router;

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private AppBarLayout appBarLayout;

    private static ActivityMainComponent component;

    public static ActivityMainComponent getComponent() {
        return component;
    }

    @IdRes
    public final static int FRAGMENT_CONTAINER_ID = R.id.main_container;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        component = DaggerActivityMainComponent.builder()
            .activityMainModule(new ActivityMainModule(this))
            .build();
        getComponent().inject(this);

        drawer = (DrawerLayout) findViewById(R.id.main_drawer);

        navigationView = (NavigationView) findViewById(R.id.main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        appBarLayout = (AppBarLayout) findViewById(R.id.main_appbar);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        router.openListFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawer.closeDrawer(GravityCompat.START);
        switch (item.getItemId()){
            case R.id.nav_list:
                router.openListFragment();
                return true;
            case R.id.nav_pager:
                router.openViewPagerFragment();
                return true;
        }
        return false;
    }

    @Override
    public void popBackStack() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void setToolbarTitle(@Nullable String text) {
        toolbar.setTitle(text);
        getSupportActionBar().setTitle(text);
    }

    @Override
    public void setToolbarColor(@ColorInt int color) {
        toolbar.setBackgroundColor(color);
        appBarLayout.setBackgroundColor(color);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && color == Color.TRANSPARENT) {
            appBarLayout.setElevation(0);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBarLayout.setElevation(getResources().getDisplayMetrics().density * 4);
        }
    }

    @Override
    public void setNavMenuEnabled(boolean isEnabled) {
        drawer.setDrawerLockMode(isEnabled ? DrawerLayout.LOCK_MODE_UNLOCKED : DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void setFragmentFullscreen(boolean isFullscreen) {
        View fragmentContainer = findViewById(FRAGMENT_CONTAINER_ID);
        //noinspection ConstantConditions
        final RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) fragmentContainer.getLayoutParams();
        if (isFullscreen) {
            lp.addRule(RelativeLayout.BELOW, 0);
        } else {
            lp.addRule(RelativeLayout.BELOW, R.id.main_appbar);
        }
        fragmentContainer.setLayoutParams(lp);
    }
}
