package com.joe.snapgame.dagger.module;

import android.app.Activity;
import android.content.Context;

import com.joe.snapgame.dagger.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Joseph Donegan.
 */
@Module
public class ActivityModule {

    private Context activityContext;

    public ActivityModule(Context context) {
        this.activityContext = context;
    }

    @Provides
    @ActivityScope
    Activity provideActivity() {
        return (Activity) activityContext;
    }
}
