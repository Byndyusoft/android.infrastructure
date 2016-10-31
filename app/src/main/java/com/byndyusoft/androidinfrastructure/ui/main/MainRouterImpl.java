package com.byndyusoft.androidinfrastructure.ui.main;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.byndyusoft.androidinfrastructure.ui.list.ListFragment;
import com.byndyusoft.androidinfrastructure.ui.viewPager.ViewPagerFragment;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 31.10.2016.
 */

@Singleton
public class MainRouterImpl implements MainRouter {

    private final FragmentManager fragmentManager;
    private final int fragmentContainerId;

    @Inject
    public MainRouterImpl(FragmentManager fragmentManager, int fragmentContainerId) {
        this.fragmentManager = fragmentManager;
        this.fragmentContainerId = fragmentContainerId;
    }

    @Override
    public void openListFragment() {
        changeFragment(new ListFragment(), true, false);
    }

    @Override
    public void openViewPagerFragment() {
        changeFragment(new ViewPagerFragment(), true, false);
    }

    private void changeFragment(@NonNull final MainFragment fragment, final boolean clearBackStack, final boolean addToBackStack) {
        Log.d("RootRouterImpl", "fragment: " + fragment.getClass().getName());
        if (clearBackStack) {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(fragment.getClass().getName());
        }

        transaction.replace(fragmentContainerId, fragment, fragment.getClass().getName());
        transaction.commit();
    }

    private void addFragment(@NonNull final MainFragment fragment, final boolean clearBackStack, final boolean addToBackStack){
        if (clearBackStack) {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(fragment.getClass().getName());
        }

        transaction.add(fragmentContainerId, fragment, fragment.getClass().getName());
        transaction.commit();
    }

}
