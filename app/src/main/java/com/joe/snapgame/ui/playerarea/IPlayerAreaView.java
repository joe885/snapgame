package com.joe.snapgame.ui.playerarea;

import android.support.annotation.StringRes;

import com.joe.snapgame.model.Card;
import com.joe.snapgame.ui.core.IBaseView;

/**
 * Created by doneg on 01/05/2016.
 */
public interface IPlayerAreaView extends IBaseView {
    void setActionButtonTitle(@StringRes int titleRes);
    void setPlayerTitle(int playerIndex);
    void setDeckCount(int deckCount);
    void setFaceUpDeckCardImage(Card card);

    void setActionButtonEnabled(boolean enabled);
}
