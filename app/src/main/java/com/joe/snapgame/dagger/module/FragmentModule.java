package com.joe.snapgame.dagger.module;

import android.support.v4.app.Fragment;

import com.joe.snapgame.dagger.scope.FragmentScope;
import com.joe.snapgame.model.SnapGame;
import com.joe.snapgame.ui.core.InjectableFragment;
import com.joe.snapgame.ui.playerarea.IPlayerAreaFragmentPresenter;
import com.joe.snapgame.ui.playerarea.IPlayerAreaView;
import com.joe.snapgame.ui.playerarea.PlayerAreaFragmentPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Joseph Donegan.
 */
@Module
public class FragmentModule {

    private InjectableFragment fragment;

    public FragmentModule(InjectableFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    Fragment provideFragment() {
        return fragment;
    }

    @Provides
    @FragmentScope
    public IPlayerAreaView provideIPlayerAreaView(Fragment fragment) {
        return (IPlayerAreaView) fragment;
    }

    @Provides
    @FragmentScope
    public IPlayerAreaFragmentPresenter provideIPlayerAreaFragmentPresenter(IPlayerAreaView view, SnapGame game) {
        return new PlayerAreaFragmentPresenter(view, game);
    }
}
