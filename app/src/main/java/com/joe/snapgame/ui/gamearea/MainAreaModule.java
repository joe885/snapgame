package com.joe.snapgame.ui.gamearea;

import android.support.v4.app.Fragment;

import com.joe.snapgame.dagger.scope.FragmentScope;
import com.joe.snapgame.model.SnapGame;

import dagger.Module;
import dagger.Provides;

/**
 * Created by doneg on 01/05/2016.
 */
@Module
public class MainAreaModule {
    @Provides
    @FragmentScope
    public IMainAreaView provideIMainAreaView(Fragment fragment) {
        return (IMainAreaView) fragment;
    }

    @Provides
    @FragmentScope
    public IMainAreaPresenter provideIMainAreaPresenter(IMainAreaView view, SnapGame game) {
        return new MainAreaPresenter(view, game);
    }
}
