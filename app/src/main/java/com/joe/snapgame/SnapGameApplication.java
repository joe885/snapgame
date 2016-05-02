package com.joe.snapgame;

import com.joe.snapgame.dagger.InjectableApplication;

/**
 * Created by Joseph Donegan.
 */
public class SnapGameApplication extends InjectableApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent.inject(this);
    }
}
