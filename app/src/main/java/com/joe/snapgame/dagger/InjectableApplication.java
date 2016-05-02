package com.joe.snapgame.dagger;

import android.app.Application;
import android.content.Context;

import com.joe.snapgame.dagger.component.ActivityComponent;
import com.joe.snapgame.dagger.component.ApplicationComponent;
import com.joe.snapgame.dagger.component.DaggerApplicationComponent;
import com.joe.snapgame.dagger.component.FragmentComponent;
import com.joe.snapgame.dagger.module.ActivityModule;
import com.joe.snapgame.dagger.module.ApplicationModule;
import com.joe.snapgame.dagger.module.FragmentModule;
import com.joe.snapgame.ui.core.InjectableActivity;
import com.joe.snapgame.ui.core.InjectableFragment;

/**
 * Created by Joseph Donegan.
 */
public class InjectableApplication extends Application {

    protected ApplicationModule applicationModule;
    protected ApplicationComponent applicationComponent;

    public static InjectableApplication get(Context context) {
        return (InjectableApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initialiseDependencyInjection();
    }

    protected void initialiseDependencyInjection() {
        applicationModule = new ApplicationModule(this);
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(applicationModule)
                .build();
    }

    public ActivityComponent createActivityComponent(Context context) {
        return applicationComponent.plus(new ActivityModule(context));
    }

    public FragmentComponent createFragmentComponent(InjectableActivity activity, InjectableFragment injectableFragment) {
        return activity.getActivityComponent().plus(new FragmentModule(injectableFragment));
    }
}
