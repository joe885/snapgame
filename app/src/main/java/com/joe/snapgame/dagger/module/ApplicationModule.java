package com.joe.snapgame.dagger.module;

import android.app.Application;
import android.content.Context;

import com.joe.snapgame.SnapGameApplication;
import com.joe.snapgame.model.SnapGame;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Joseph Donegan.
 */
@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    SnapGameApplication provideToDoListApplication() {
        return (SnapGameApplication) application;
    }

    @Provides
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    SnapGame provideSnapGame() {
        return new SnapGame();
    }
}
