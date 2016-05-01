package com.joe.snapgame.ui.gamearea;

import dagger.Subcomponent;

/**
 * Created by doneg on 01/05/2016.
 */
@Subcomponent(modules = MainAreaModule.class)
public interface MainAreaComponent {
    void inject(MainAreaFragment fragment);
}
