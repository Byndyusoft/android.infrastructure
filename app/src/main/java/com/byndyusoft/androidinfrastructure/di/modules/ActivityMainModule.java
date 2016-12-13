package com.byndyusoft.androidinfrastructure.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.byndyusoft.androidinfrastructure.ui.main.MainActivity;
import com.byndyusoft.androidinfrastructure.ui.main.MainFragmentCallback;
import com.byndyusoft.androidinfrastructure.ui.main.MainRouter;
import com.byndyusoft.androidinfrastructure.ui.main.MainRouterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 26.10.2016.
 */

@Module
public class ActivityMainModule {

    private final MainActivity mainActivity;

    public ActivityMainModule(@NonNull final MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @Singleton
    @NonNull
    MainRouter provideMainRouter() {
        return new MainRouterImpl(mainActivity.getSupportFragmentManager(), MainActivity.FRAGMENT_CONTAINER_ID);
    }

    @Provides
    @Singleton
    @NonNull
    MainFragmentCallback provideMainFragmentCallback() {
        return mainActivity;
    }

}
