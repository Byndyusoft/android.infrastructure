package com.byndyusoft.androidinfrastructure.di.components;

import com.byndyusoft.androidinfrastructure.di.modules.ActivityMainModule;
import com.byndyusoft.androidinfrastructure.ui.main.MainActivity;
import com.byndyusoft.androidinfrastructure.ui.main.MainFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 26.10.2016.
 */

@Singleton
@Component(modules = {ActivityMainModule.class})
public interface ActivityMainComponent {
    void inject(MainActivity mainActivity);

    void inject(MainFragment mainFragment);
}
