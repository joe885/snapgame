package com.joe.snapgame.ui.playerarea;

import android.support.v4.app.Fragment;

import com.joe.snapgame.dagger.scope.FragmentScope;
import com.joe.snapgame.model.SnapGame;

import dagger.Module;
import dagger.Provides;

/**
 * Created by doneg on 01/05/2016.
 */
@Module
public class PlayerAreaFragmentModule {

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
