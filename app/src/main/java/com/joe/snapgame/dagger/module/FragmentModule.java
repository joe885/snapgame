package com.joe.snapgame.dagger.module;

import android.support.v4.app.Fragment;

import com.joe.snapgame.ui.core.InjectableFragment;
import com.joe.snapgame.ui.gamearea.MainAreaModule;
import com.joe.snapgame.ui.playerarea.PlayerAreaFragmentModule;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Joseph Donegan.
 */
@Module(includes = {
        PlayerAreaFragmentModule.class,
        MainAreaModule.class
})
public class FragmentModule {

    private InjectableFragment fragment;

    public FragmentModule(InjectableFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    Fragment provideFragment() {
        return fragment;
    }
}
