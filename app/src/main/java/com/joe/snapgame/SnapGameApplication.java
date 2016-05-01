package com.joe.snapgame;

import com.joe.snapgame.dagger.InjectableApplication;

/**
 * Created by doneg on 01/05/2016.
 */
public class SnapGameApplication extends InjectableApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent.inject(this);
    }
}
