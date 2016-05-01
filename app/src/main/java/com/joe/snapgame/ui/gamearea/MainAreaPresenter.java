package com.joe.snapgame.ui.gamearea;

import com.joe.snapgame.model.SnapGame;
import com.joe.snapgame.ui.core.BasePresenter;
import com.joe.snapgame.utils.DataError;

/**
 * Created by doneg on 01/05/2016.
 */
public class MainAreaPresenter extends BasePresenter<IMainAreaView> implements IMainAreaPresenter {

    private SnapGame game;

    public MainAreaPresenter(IMainAreaView view, SnapGame game) {
        super(view);
        this.game = game;
    }

    @Override
    protected void populateView(DataError error) {

    }

    @Override
    public void initialiseData() {
        onDataReady();
    }
}
