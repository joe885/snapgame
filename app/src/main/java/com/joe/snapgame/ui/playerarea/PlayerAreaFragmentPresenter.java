package com.joe.snapgame.ui.playerarea;

import com.joe.snapgame.R;
import com.joe.snapgame.model.Card;
import com.joe.snapgame.model.SnapGame;
import com.joe.snapgame.ui.core.BasePresenter;
import com.joe.snapgame.utils.DataError;

/**
 * Created by Joseph Donegan.
 */
public class PlayerAreaFragmentPresenter extends BasePresenter<IPlayerAreaView>
        implements IPlayerAreaFragmentPresenter, SnapGame.IGameObserver {

    private SnapGame game;
    private int playerIndex;

    private enum ActionButtonMode {
        DISABLED,
        SNAP,
        DEAL
    }

    private ActionButtonMode actionButtonMode = ActionButtonMode.DISABLED;

    public PlayerAreaFragmentPresenter(IPlayerAreaView view, SnapGame game) {
        super(view);
        this.game = game;
    }

    @Override
    protected void populateView(DataError error) {
        if (error != null) {
            view.showError(error);
        } else {
            view.setPlayerTitle(playerIndex);
            onPlayerDeckChanged(this.playerIndex);
            int currentPlayerIndex = game.getCurrentPlayerIndex();
            if (currentPlayerIndex > -1) {
                onNewTurnStarted(currentPlayerIndex);
            } else {
                setActionButtonMode(ActionButtonMode.DISABLED);
            }
        }
    }

    @Override
    public void initialiseData(int playerIndex) {
        this.playerIndex = playerIndex;
        game.registerObserver(this);
        onDataReady();
    }

    @Override
    public void actionButtonPressed() {
        switch (actionButtonMode) {
            case DEAL:
                game.dealCard(playerIndex);
                setActionButtonMode(ActionButtonMode.SNAP);
                break;
            case SNAP:
                game.callSnap(playerIndex);
                setActionButtonMode(ActionButtonMode.DISABLED);
                break;
        }
    }

    @Override
    public void newGamePressed() {
        game.initialiseNewGame();
    }

    private void setActionButtonMode(ActionButtonMode mode) {
        actionButtonMode = mode;
        switch (actionButtonMode) {
            case DISABLED:
                view.setActionButtonTitle(R.string.button_snap_title);
                view.setActionButtonEnabled(false);
                break;
            case DEAL:
                view.setActionButtonTitle(R.string.button_deal_title);
                view.setActionButtonEnabled(true);
                break;
            case SNAP:
                view.setActionButtonTitle(R.string.button_snap_title);
                view.setActionButtonEnabled(true);
                break;
        }
    }

    @Override
    public void onNewTurnStarted(int playerIndex) {
        if (playerIndex == this.playerIndex) {
            setActionButtonMode(ActionButtonMode.DEAL);
        } else {
            setActionButtonMode(ActionButtonMode.DISABLED);
        }
    }

    @Override
    public void onPlayerDealt(int playerIndex) {
        setActionButtonMode(ActionButtonMode.SNAP);
    }

    @Override
    public void onPlayerDeckChanged(int playerIndex) {
        if (playerIndex == this.playerIndex) {
            view.setDeckCount(game.getPlayer(playerIndex).getFaceDownDeckSize());
            Card topCard = game.getPlayer(playerIndex).getTopFaceUpCard();
            view.setFaceUpDeckCardImage(topCard);
        }
    }

    @Override
    public void onSnapCalledInError(int playerIndex) {
        if (playerIndex == this.playerIndex) {
            view.showSnapFailed();
        }
    }

    @Override
    public void onSnapCalledSuccessfully(int playerIndex) {
        if (playerIndex == this.playerIndex) {
            view.showSnapSuccess();
        }
    }

    @Override
    public void onGameEnded(int winningPlayerIndex) {
        if (winningPlayerIndex == this.playerIndex) {
            view.showGameOver(winningPlayerIndex);
        }
    }
}
