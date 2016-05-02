package com.joe.snapgame.dagger.component;

import com.joe.snapgame.dagger.module.FragmentModule;
import com.joe.snapgame.dagger.scope.FragmentScope;
import com.joe.snapgame.ui.playerarea.PlayerAreaFragment;

import dagger.Subcomponent;

/**
 * Created by Joseph Donegan.
 */
@FragmentScope
@Subcomponent(modules = {FragmentModule.class,})
public interface FragmentComponent {
    void inject(PlayerAreaFragment fragment);
}
