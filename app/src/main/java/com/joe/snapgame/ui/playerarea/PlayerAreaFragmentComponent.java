package com.joe.snapgame.ui.playerarea;

import dagger.Subcomponent;

/**
 * Created by doneg on 01/05/2016.
 */
@Subcomponent(modules = PlayerAreaFragmentModule.class)
public interface PlayerAreaFragmentComponent {
    void inject(PlayerAreaFragment fragment);
}
