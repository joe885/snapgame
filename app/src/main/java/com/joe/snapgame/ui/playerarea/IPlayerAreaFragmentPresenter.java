package com.joe.snapgame.ui.playerarea;

import com.joe.snapgame.ui.core.IBasePresenter;

/**
 * Created by Joseph Donegan.
 */
public interface IPlayerAreaFragmentPresenter extends IBasePresenter {
    void initialiseData(int playerIndex);

    void actionButtonPressed();

    void newGamePressed();
}
