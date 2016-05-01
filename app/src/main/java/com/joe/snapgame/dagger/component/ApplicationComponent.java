package com.joe.snapgame.dagger.component;

import com.joe.snapgame.SnapGameApplication;
import com.joe.snapgame.dagger.module.ActivityModule;
import com.joe.snapgame.dagger.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Joseph Donegan.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    ActivityComponent plus(ActivityModule activityModule);

    void inject(SnapGameApplication application);
}
