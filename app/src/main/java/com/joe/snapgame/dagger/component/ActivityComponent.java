package com.joe.snapgame.dagger.component;

import com.joe.snapgame.MainActivity;
import com.joe.snapgame.dagger.module.ActivityModule;
import com.joe.snapgame.dagger.module.FragmentModule;
import com.joe.snapgame.dagger.scope.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Joseph Donegan.
 */
@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {

    FragmentComponent plus(FragmentModule fragmentModule);

    void inject(MainActivity mainActivity);
}
