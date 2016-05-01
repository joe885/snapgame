package com.joe.snapgame.dagger.module;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.joe.snapgame.BuildConfig;
import com.joe.snapgame.dagger.scope.ActivityScope;
import com.joe.snapgame.ui.core.IFragmentController;

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
        return (Activity)activityContext;
    }

    @Provides
    @ActivityScope
    SharedPreferences provideSharedPreferences() {
        return activityContext.getSharedPreferences(BuildConfig.APPLICATION_ID, Activity.MODE_PRIVATE);
    }

    @Provides
    @ActivityScope
    IFragmentController provideIFragmentController() {
        return (IFragmentController)activityContext;
    }
}
