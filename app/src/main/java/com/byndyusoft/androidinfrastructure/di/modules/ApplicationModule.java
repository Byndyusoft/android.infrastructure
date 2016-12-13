package com.byndyusoft.androidinfrastructure.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 13.12.2016.
 */

@Module
public class ApplicationModule {

    private final Context context;

    public ApplicationModule(@NotNull Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    @NonNull
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    @NonNull
    Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
                .create();
    }

}
