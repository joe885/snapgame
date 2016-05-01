package com.joe.snapgame.ui.playerarea;

import com.joe.snapgame.model.SnapGame;
import com.joe.snapgame.ui.core.BasePresenter;
import com.joe.snapgame.utils.DataError;

/**
 * Created by doneg on 01/05/2016.
 */
public class PlayerAreaFragmentPresenter extends BasePresenter<IPlayerAreaView> implements IPlayerAreaFragmentPresenter {

    private SnapGame game;

    public PlayerAreaFragmentPresenter(IPlayerAreaView view, SnapGame game) {
        super(view);
        this.game = game;
    }

    @Override
    protected void populateView(DataError error) {

    }

    @Override
    public void initialiseData(int playerIndex) {

    }
}
